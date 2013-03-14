import java.util.ArrayList;


public class OrderChunk 
{
	private Menu menu;
	private ArrayList<SingleItemWithNote> items;
	private ORDERSTATUSENUMS orderStatus;
	
	public OrderChunk()
	{
		orderStatus = ORDERSTATUSENUMS.PREPPING;
	}
	
	public ArrayList<SingleItemWithNote> getItems()
	{
		return items;
	}
	
	public void removeItem(SingleItemWithNote toRemove)
	{
		items.remove(toRemove);
	}
	
	public void addItem(SingleItemWithNote toAdd)
	{
		items.add(toAdd);
	}
	
	public void updateOrderStatus(ORDERSTATUSENUMS status)
	{
		orderStatus = status;
	}
	
	public ORDERSTATUSENUMS getOrderStatus()
	{
		return orderStatus;
	}
}

