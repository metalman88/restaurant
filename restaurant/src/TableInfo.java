
public class TableInfo {

	
	public TableInfo(String tableName,int tableNumber,int maxOcc, String zone,
					 DatabaseInteractor DBInteractor, Menu menu)
	{
		this.tablename = tableName;
		this.tableNumber = tableNumber;
		this.maxOcc = maxOcc;
		this.zone = zone;
		this.DBInteractor = DBInteractor;
		this.menu = menu;
		}
	
	public String tablename;
	public int tableNumber;
	public int maxOcc;
	public String zone;
	private DatabaseInteractor DBInteractor;
	private Menu menu;
}
