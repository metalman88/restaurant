package restaurant.GUI;

import javax.swing.JPanel;

import restaurant.system.RestaurantSystem;

public class CheckInPanel extends JPanel {
	
	private RestaurantSystem restaurantSystem;

	public CheckInPanel(RestaurantSystem restaurantSystem)
	{
		this.restaurantSystem = restaurantSystem;
	}

}
