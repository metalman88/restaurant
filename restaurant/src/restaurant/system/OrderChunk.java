package restaurant.system;
import java.util.ArrayList;

import restuarant.enums.ORDERSTATUSENUMS;


public class OrderChunk 
{
	private Menu menu;
	private ArrayList<SingleItemWithNote> items;
	private ORDERSTATUSENUMS orderStatus;
	
	public OrderChunk()
	{
		orderStatus = ORDERSTATUSENUMS.PREPPING;
		items = new ArrayList<SingleItemWithNote>();
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
		if(toAdd != null)
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

