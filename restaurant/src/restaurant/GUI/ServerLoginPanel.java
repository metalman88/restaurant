package restaurant.GUI;

import java.awt.Component;

import javax.swing.JPanel;

import restaurant.system.RestaurantSystem;
import restuarant.enums.ZONEENUMS;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ServerLoginPanel extends JPanel {

	private RestaurantSystem restaurantSystem;
	private JTable zoneTable;
	private WelcomeScreen welcomeScreen;
	
	public ServerLoginPanel(RestaurantSystem restaurantSystem, WelcomeScreen welcomeScreen)
	{
		this.restaurantSystem = restaurantSystem;
		this.welcomeScreen = welcomeScreen;
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
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(167, 52, 119, 92);
		add(scrollPane);
		
		zoneTable = new JTable();
		zoneTable.setModel(new DefaultTableModel(
			new Object[][] {
				{"Blue"},
				{"Gold"},
				{"Yellow"},
				{"Tiger"},
			},
			new String[] {
				"Restaurant Zones"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		zoneTable.getColumnModel().getColumn(0).setPreferredWidth(106);
		scrollPane.setViewportView(zoneTable);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) 
			{
				zoneLogin(zoneTable);
				welcomeScreen.swapLoginWaiterPanel();
				welcomeScreen.switchToFullScreen();
				
			}
		});
		btnLogin.setBounds(180, 190, 89, 23);
		add(btnLogin);
	}
	
	private void zoneLogin(JTable focusedTable)
	{
		String zone = (String) focusedTable.getValueAt(focusedTable.getSelectedRow(), 0);
		
		if (zone.equalsIgnoreCase(ZONEENUMS.BLUE.toString()))
		{
			restaurantSystem.setTabletToZone(ZONEENUMS.BLUE);
		}
		
		else if (zone.equalsIgnoreCase(ZONEENUMS.GOLD.toString()))
		{
			restaurantSystem.setTabletToZone(ZONEENUMS.GOLD);
		}
		
		else if (zone.equalsIgnoreCase(ZONEENUMS.YELLOW.toString()))
		{
			restaurantSystem.setTabletToZone(ZONEENUMS.YELLOW);
		}
		
		else if (zone.equalsIgnoreCase(ZONEENUMS.TIGER.toString()))
		{
			restaurantSystem.setTabletToZone(ZONEENUMS.TIGER);
		}
	}
}
