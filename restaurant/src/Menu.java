import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;


public class Menu {

	private HashMap<Integer,MenuItem> menuList;
	
	public Menu(HashMap<Integer,MenuItem> menuList)
	{
		this.menuList = menuList;
	}
	
	public ArrayList<MenuItem>  getCategoryItems(String CATEGORYENUM)
	{
		ArrayList<MenuItem> returnList = new ArrayList<MenuItem>();
		Set<Integer> keySet = menuList.keySet();
		Iterator keySetIterator = keySet.iterator();
		
		while(keySetIterator.hasNext())
		{
			MenuItem curItem = (MenuItem) keySetIterator.next();
			if(curItem.)
		}
	}
	
	public HashMap<Integer,MenuItem> getAllItems()
	{
		return menuList;
	}
	
	public ArrayList<MenuItem> getItem(String name)
	{
		
		ArrayList<MenuItem> returnList = new ArrayList<MenuItem>();
		Set<Integer> keySet = menuList.keySet();
		Iterator keySetIterator = keySet.iterator();
		
		while(keySetIterator.hasNext())
		{
			MenuItem curItem = (MenuItem) keySetIterator.next();
			if(curItem.getName().equals(name))
			{
				returnList.add(curItem);
			}
		}
		
		return returnList;
	}
	
	public MenuItem getItem(int ItemID)
	{
		return menuList.get(ItemID);
	}
}
