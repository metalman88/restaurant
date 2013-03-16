package restaurant.GUI;

import javax.swing.JPanel;

import restaurant.system.RestaurantSystem;

public class KitchenPanel extends JPanel{
	private RestaurantSystem restaurantSystem;
	
	public KitchenPanel(RestaurantSystem restaurantSystem)
	{
		this.restaurantSystem= restaurantSystem;
	}

}
