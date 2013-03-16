package restaurant.GUI;

import javax.swing.JPanel;

import restaurant.system.RestaurantSystem;


public class ServerPanel extends JPanel{

	private RestaurantSystem restaurantSystem;
	
	public ServerPanel(RestaurantSystem restaurantSystem)
	{
		this.restaurantSystem = restaurantSystem;
	}
}
