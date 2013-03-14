import java.util.ArrayList;
import java.util.HashMap;


public class RestaurantSystem {

	public HashMap<Integer,TableInfo> tableHash;
	public DatabaseInteractor DBInteractor;
	public Menu menu;
	//public ArrayList<Party> waitList;
	
	public RestaurantSystem()
	{
		DBInteractor = new DatabaseInteractor();
		
	}
	
	public Menu getMenuFromDB()
	{
		//DBInteractor.getMenu();
		return null;
	}
	
	public void getTablesFromDB()
	{
		tableHash = DBInteractor.getTables();
		
	}
	
}
