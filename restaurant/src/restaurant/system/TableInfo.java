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
		DBInteractor.updateTableStatus(tableNumber, "True");
		
	}
	
	public void setTableToEmpty()
	{
		isOccupied = false;
		DBInteractor.updateTableStatus(tableNumber, "False");
	}
	
	public boolean isTableOccupied()
	{
		return isOccupied;
	}
	
	public String tablename;
	public int tableNumber;
	public int maxOcc;
	public ZONEENUMS zone;
	private DatabaseInteractor DBInteractor;
	private Menu menu;
	private boolean isOccupied;
}
