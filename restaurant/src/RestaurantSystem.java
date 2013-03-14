import java.util.ArrayList;
import java.util.HashMap;


public class RestaurantSystem {

	public HashMap<Integer,TableInfo> tableHash;
	public DatabaseInteractor DBInteractor;
	public Menu menu;
	public ArrayList<Party> waitList;
	
	public RestaurantSystem()
	{
		DBInteractor = new DatabaseInteractor();
		
	}
	
	public Menu getMenuFromDB()
	{
		//use db command
		return null;
	}
	
	public void getTablesFromDB()
	{
		tableHash = DBInteractor.getTables();
	}
	
	public void getTableStatusFromDatabase()
	{
		//will update the tableHash, occupied Unoccupied exter
	}
	
}
