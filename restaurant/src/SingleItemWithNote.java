
public class SingleItemWithNote 
{
	private MenuItem item;
	private String note;
	
	public SingleItemWithNote(MenuItem item, String note)
	{
		this.item = item;
		this.note = note;
	}
	
	public MenuItem getItem()
	{
		return item;
	}
	
	public String getNote()
	{
		return note;
	}
}
