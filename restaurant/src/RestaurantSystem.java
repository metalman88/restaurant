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
		tableHash = DBInteractor.getTables();
		
	}
	
	public void getMenuFromDB()
	{
		menu = DBInteractor.getMenuFromDB();
	}
	
	public void updateTablesFromDB()
	{
		//some functionality to just update the tables
	}
	
	public void getTableStatusFromDatabase()
	{
		//will update the tableHash, occupied Unoccupied exter
	}
	
	public void getOrderStatusFromDatabase()
	{
		// will update the order chunks, calls d
	}
	
}
