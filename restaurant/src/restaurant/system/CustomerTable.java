package restaurant.system;
import java.util.ArrayList;

import restaurant.database.DatabaseInteractor;


public class CustomerTable 
{
	private Menu menu;
	private DatabaseInteractor db;
	private OrderChunk curChunk;
	private ArrayList<OrderChunk> listOfOrders;
	private int tableID = 200;
	
	public CustomerTable(Menu menu, DatabaseInteractor db, int tableID)
	{
		this.tableID = tableID;
		this.menu = menu;
		this.db = db;
		curChunk = new OrderChunk();
		listOfOrders = new ArrayList<OrderChunk>();
	}
	
	public void submitOrder()
	{
		curChunk.setTable(""+this.tableID);
		db.addOrderToDB(curChunk);
		listOfOrders.add(curChunk);
		curChunk = new OrderChunk();
	}
	
	public ArrayList<OrderChunk> getListOfOrderChunks()
	{
		return listOfOrders;
	}
	
	public OrderChunk getCurrentOrderChunk()
	{
		return curChunk;
	}
	
	
}
