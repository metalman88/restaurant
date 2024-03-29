package restaurant.system;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import restuarant.enums.CATEGORYENUMS;


public class Menu {

	private HashMap<Integer,MenuItem> menuList;
	
	public Menu(HashMap<Integer,MenuItem> menuList)
	{
		this.menuList = menuList;
	}
	
	public ArrayList<MenuItem>  getCategoryItems(CATEGORYENUMS categoryEnum)
	{
		ArrayList<MenuItem> returnList = new ArrayList<MenuItem>();
		Set<Integer> keySet = menuList.keySet();
		Iterator keySetIterator = keySet.iterator();
		
		while(keySetIterator.hasNext())
		{
			MenuItem curItem = menuList.get(keySetIterator.next());
			if(curItem.getCategory().equals(categoryEnum))
			{
				System.out.println("Testing in getcategory"+curItem.getName()+curItem.getID());
				returnList.add(curItem);
			}
		}
		
		return returnList;
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
