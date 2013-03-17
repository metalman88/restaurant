package restaurant.GUI;

import javax.swing.JPanel;

import restaurant.system.RestaurantSystem;

//Jesse working on this class
public class CustomerAfterLoginPanel extends JPanel{
	
	private RestaurantSystem restaurantSystem;
	private WelcomeScreen welcomeScreen;
	
	public CustomerAfterLoginPanel(RestaurantSystem restaurantSystem,WelcomeScreen welcomeScreen)
	{
		this.restaurantSystem = restaurantSystem;
		this.welcomeScreen = welcomeScreen;
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
