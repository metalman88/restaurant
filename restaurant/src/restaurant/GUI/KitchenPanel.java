package restaurant.GUI;

import javax.swing.JPanel;

import restaurant.system.RestaurantSystem;

public class KitchenPanel extends JPanel{
	private RestaurantSystem restaurantSystem;
	
	public KitchenPanel(RestaurantSystem restaurantSystem)
	{
		this.restaurantSystem= restaurantSystem;
		setUpPanel();
	}
	
	public void  setUpPanel()
	{
		//Do everything here (look at CustomerLoginPanel.java as example)
		//Set up your JPanel, and interact with the restuarantSystem accordingling
		//this class is a JPanel class so do stuff like add(new JTextField())
		//or right click on this class and open with WindowBuilder if installed.
		//
		//User the restaurantSystem variable to interact with the class
	}

}