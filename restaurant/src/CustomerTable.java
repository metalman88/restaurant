import java.util.ArrayList;


public class CustomerTable 
{
	private Menu menu;
	private DatabaseInteractor db;
	private OrderChunk curChunk;
	private ArrayList<OrderChunk> listOfOrders;
	
	public CustomerTable(Menu menu, DatabaseInteractor db)
	{
		this.menu = menu;
		this.db = db;
		curChunk = new OrderChunk();
		listOfOrders = new ArrayList<OrderChunk>();
	}
	
	public void submitOrder()
	{
		db.addOrderToDB(curChunk);
		listOfOrders.add(curChunk);
		curChunk = new OrderChunk();
	}
	
}
