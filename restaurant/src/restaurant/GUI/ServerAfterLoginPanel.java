package restaurant.GUI;

import javax.swing.JPanel;

import restaurant.system.RestaurantSystem;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;

public class ServerAfterLoginPanel extends JPanel 
{
	private RestaurantSystem restaurantSystem;
	private WelcomeScreen welcomeScreen;
	private JTable tablesInZone;
	private JTable zoneOrdersOut;
	
	public ServerAfterLoginPanel(RestaurantSystem restaurantSystem, WelcomeScreen welcomeScreen)
	{
		this.restaurantSystem = restaurantSystem;
		this.welcomeScreen = welcomeScreen;
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 35, 323, 215);
		add(scrollPane);
		
		tablesInZone = new JTable();
		tablesInZone.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
			},
			new String[] {
				"Table #", "Table Name", "Occupied", "Service Req."
			}
		));
		scrollPane.setViewportView(tablesInZone);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(368, 35, 311, 215);
		add(scrollPane_1);
		
		zoneOrdersOut = new JTable();
		zoneOrdersOut.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
			},
			new String[] {
				"Table #", "Table Name", "Dishes", "Status"
			}
		));
		scrollPane_1.setViewportView(zoneOrdersOut);
		
		JLabel lblTables = new JLabel("Tables");
		lblTables.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTables.setBounds(144, 10, 46, 14);
		add(lblTables);
		
		JLabel lblOrdersOut = new JLabel("Orders Out");
		lblOrdersOut.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblOrdersOut.setBounds(483, 10, 69, 14);
		add(lblOrdersOut);
		
		JButton btnTableServiced = new JButton("Table Serviced");
		btnTableServiced.setBounds(47, 272, 107, 23);
		add(btnTableServiced);
		
		JButton btnSetUnoccupied = new JButton("Set Unoccupied");
		btnSetUnoccupied.setBounds(183, 272, 107, 23);
		add(btnSetUnoccupied);
		
		JButton btnLogoutOfZone = new JButton("Logout of Zone");
		btnLogoutOfZone.setBounds(286, 310, 116, 23);
		add(btnLogoutOfZone);
		
		JButton btnUpdateOrderStatus = new JButton("Update Order Status");
		btnUpdateOrderStatus.setBounds(468, 272, 148, 23);
		add(btnUpdateOrderStatus);
	}
	
	public void setUpPanel()
	{
		
	}
}
