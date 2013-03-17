package restaurant.system;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import restaurant.database.DatabaseInteractor;


public class RestaurantSystem {

	public HashMap<Integer,TableInfo> tableHash;
	public DatabaseInteractor DBInteractor;
	public Menu menu;
	public ArrayList<Party> waitList;
	public int curTableNumber;
	
	public RestaurantSystem()
	{
		
		DBInteractor = new DatabaseInteractor();
		menu = DBInteractor.getMenuFromDB();
		tableHash = DBInteractor.getTables(menu);
		
	}
	
	public void getMenuFromDB()
	{
		menu = DBInteractor.getMenuFromDB();
	}
	
	public void updateTablesFromDB()
	{
		tableHash = DBInteractor.getTables(menu);
		//switch to commented code once database has functionality for tableStatusTriggering
		//above code query's the all tables and their info.
		//updateTablesStatusFromDB();
	}
	
	public void getTableStatusFromDatabase(int key)
	{
		//will update the tableHash, occupied Unoccupied exter
		updateTablesFromDB();
		if(tableHash.get(key).isTableOccupied())
			System.out.println("Table occupied");
		else
			System.out.println("Table not occupied");
		
	}
	
	/**
	 * Updates the states of each table in the tableHash
	 * this is to be used by updateTablesFromDB if we ever decided to
	 * only query the updated tables instead of all tables at once.
	 */
	private void updateTableStatusFromDB()
	{
		HashMap<Integer,Boolean> occupiedHash =  DBInteractor.getTableStatusIfUpdated();
		Set<Integer> occupiedHashSet = occupiedHash.keySet();
		Iterator occupiedHashIterator = occupiedHashSet.iterator();
		
		while(occupiedHashIterator.hasNext())
		{
			int curKey = (int) occupiedHashIterator.next();
			if(tableHash.get(curKey).isTableOccupied() != occupiedHash.get(curKey).booleanValue())
			{
				//time to update local table status
				if(occupiedHash.get(curKey).booleanValue())
				{
					tableHash.get(curKey).setTableToOccupied();
				}
				else
				{
					tableHash.get(curKey).setTableToEmpty();
				}
				
			}
		}
		
	}
	
	/**
	 * this will log a tablet into the database,
	 * returns true if login was successful
	 */
	public boolean loginTablet(String tableNumberOrName)
	{
		//the exception catch tells whether tableNumberOrName is a string or an int
		//this helps me query the database accordingly
		try
		{
			int tableNumber = Integer.parseInt(tableNumberOrName);
			return DBInteractor.loginTablet(tableNumber, "") ;
			
		}
		catch(NumberFormatException e)
		{
			return DBInteractor.loginTablet(-1, tableNumberOrName);
		}
	}
	
	public void getOrderStatusFromDatabase()
	{
		
	}
	
	public TableInfo getCurTable()
	{
		return tableHash.get(curTableNumber);
	}
	
}
