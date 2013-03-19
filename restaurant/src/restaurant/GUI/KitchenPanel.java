package restaurant.GUI;

import javax.swing.JPanel;

import restaurant.system.RestaurantSystem;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

public class KitchenPanel extends JPanel{
	private RestaurantSystem restaurantSystem;
	private JTable kitchenTable;
	
	public KitchenPanel(RestaurantSystem restaurantSystem)
	{
		this.restaurantSystem= restaurantSystem;
		setLayout(null);
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
		
		JLabel lblKitchenInterface = new JLabel("Kitchen Interface");
		lblKitchenInterface.setBounds(169, 5, 118, 20);
		lblKitchenInterface.setFont(new Font("Tahoma", Font.PLAIN, 16));
		add(lblKitchenInterface);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 63, 325, 226);
		add(scrollPane);
		
		kitchenTable = new JTable();
		kitchenTable.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
			},
			new String[] {
				"Order ID", "Order Item", "Status"
			}
		));
		kitchenTable.setToolTipText("");
		scrollPane.setViewportView(kitchenTable);
	}
}
