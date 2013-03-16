package restaurant.system;
import java.util.ArrayList;
import java.util.HashMap;

import restaurant.database.DatabaseInteractor;


public class RestaurantSystem {

	public HashMap<Integer,TableInfo> tableHash;
	public DatabaseInteractor DBInteractor;
	public Menu menu;
	public ArrayList<Party> waitList;
	
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
	
	public void getOrderStatusFromDatabase()
	{
		// will update the order chunks, calls d
	}
	
}
