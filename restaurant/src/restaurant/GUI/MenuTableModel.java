package restaurant.GUI;

import java.awt.MenuItem;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class MenuTableModel extends AbstractTableModel{

	ArrayList<MenuItem>	menuItems;
	
	public MenuTableModel(ArrayList<MenuItem> menuItems)
	{
		this.menuItems = menuItems;
	}
	
	
	public int getColumnCount() {
		// column will always be equal to 2
		//item name and then the price
		return 2;
	}

	
	public int getRowCount() {
		return menuItems.size();
	}

	
	public Object getValueAt(int row, int column) {
		
		return menuItems.get(row);
	}
	
	public void addToList(ArrayList<MenuItem> menuItemsToAdd)
	{
		menuItems.addAll(menuItemsToAdd);
	}
	
	public void addToList(MenuItem menuItemToAdd)
	{
		menuItems.add(menuItemToAdd);
	}
	
	public void removeItem(MenuItem menuItemToRemove)
	{
		menuItems.remove(menuItemToRemove);		
	}
	
	public void menuItemToRemove(Integer indexToRemove)
	{
		menuItems.remove(indexToRemove);
	}
	
	public void clearList()
	{
		menuItems.clear();
	}

}
