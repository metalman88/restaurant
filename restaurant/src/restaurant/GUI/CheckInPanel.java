package restaurant.GUI;

import javax.swing.JPanel;

import restaurant.system.RestaurantSystem;

// I'll work on this class. Please let me know if this would conflict with 
// any one's work. Tai Bo
public class CheckInPanel extends JPanel {
	
	private RestaurantSystem restaurantSystem;

	public CheckInPanel(RestaurantSystem restaurantSystem)
	{
		this.restaurantSystem = restaurantSystem;
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
