import java.util.ArrayList;


public class TableInfo {

	
	public TableInfo(String tableName,int tableNumber,int maxOcc, ZONEENUMS zone,
					 DatabaseInteractor DBInteractor, Menu menu)
	{
		this.tablename = tableName;
		this.tableNumber = tableNumber;
		this.maxOcc = maxOcc;
		this.zone = zone;
		this.DBInteractor = DBInteractor;
		this.menu = menu;
		isOccupied = false;
	}
	
	public ZONEENUMS getZone()
	{
		return zone;
	}
	
	public int getTableNumber()
	{
		return tableNumber;
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
