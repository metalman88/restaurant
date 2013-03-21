package restaurant.system;
import java.util.ArrayList;

import restaurant.database.DatabaseInteractor;
import restuarant.enums.ZONEENUMS;


public class TableInfo {

	
	public TableInfo(String tableName,int tableNumber,int maxOcc, ZONEENUMS zone,
					 DatabaseInteractor DBInteractor, Menu menu, boolean isTaken)
	{
		this.tablename = tableName;
		this.tableNumber = tableNumber;
		this.maxOcc = maxOcc;
		this.zone = zone;
		this.DBInteractor = DBInteractor;
		this.menu = menu;
		isOccupied = isTaken;
		customerTable = new CustomerTable(menu,DBInteractor, tableNumber);
	}
	public CustomerTable getCustomerTable()
	{
		return customerTable;
	}
	
	
	public void resetCustomerTable()
	{
		customerTable = new CustomerTable(menu,DBInteractor, tableNumber);
	}
	
	public ZONEENUMS getZone()
	{
		return zone;
	}
	
	public int getTableNumber()
	{
		return tableNumber;
	}
	
	public String getTableName()
	{
		return tablename;
	}
	
	public void setTableToOccupied()
	{
		isOccupied = true;
		DBInteractor.updateTableStatus(tableNumber, "1");
		
	}
	
	public void setTableToEmpty()
	{
		isOccupied = false;
		DBInteractor.updateTableStatus(tableNumber, "0");
	}
	
	public boolean isTableOccupied()
	{
		return isOccupied;
	}
	
	public boolean getServiceStatus()
	{
		if(servStatus) System.out.println("This is true");
		else System.out.println("FALSE");
		return servStatus;
	}
	
	public void setServiceStatus(boolean stat)
	{
		if(stat) System.out.println("This thing is true");
		else System.out.println("WTF");
		this.servStatus = stat;
		if(servStatus) System.out.println("This thing is true");
		else System.out.println("WTF");
	}
	
	public String tablename;
	public int tableNumber;
	public int maxOcc;
	public ZONEENUMS zone;
	private DatabaseInteractor DBInteractor;
	private Menu menu;
	private boolean isOccupied;
	private boolean servStatus;
	private CustomerTable customerTable;
}
