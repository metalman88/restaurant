package restaurant.system;

public class SingleItemWithNote 
{
	private MenuItem item;
	private int menuID;
	private String note;
	 
	public SingleItemWithNote(int menuID, String note)
	{
		this.menuID = menuID;
		//this.item = item;
		this.note = note;
	}
	
	public SingleItemWithNote(MenuItem menuItem, String note)
	{
		this.item = menuItem;
		this.note = note;
	}
	
	public MenuItem getItem()
	{
		return item;
	}
	
	public int getID()
	{
		return menuID;
	}
	
	public String getNote()
	{
		return note;
	}
	
}
