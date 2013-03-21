package restaurant.GUI;

import javax.swing.JPanel;

import restaurant.system.CustomerTable;
import restaurant.system.OrderChunk;
import restaurant.system.RestaurantSystem;
import restaurant.system.SingleItemWithNote;
import restaurant.system.TableInfo;
import restuarant.enums.ORDERSTATUSENUMS;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
		
		setUpPanel();
	}
	
	public void setUpPanel()
	{
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 35, 323, 215);
		add(scrollPane);
		
		tablesInZone = new JTable();
		populateTablesInZone(restaurantSystem.DBInteractor.getTablesInZone(restaurantSystem.getTabletToZone()));
		scrollPane.setViewportView(tablesInZone);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(368, 35, 311, 215);
		add(scrollPane_1);
		
		zoneOrdersOut = new JTable();
		populateZoneOrdersOut(restaurantSystem.DBInteractor.getTablesInZone(restaurantSystem.getTabletToZone()));
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
		btnTableServiced.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				updateTableServiced(tablesInZone);
			}
		});
		btnTableServiced.setBounds(47, 272, 107, 23);
		add(btnTableServiced);
		
		JButton btnSetUnoccupied = new JButton("Set Unoccupied");
		btnSetUnoccupied.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				setTableUnoccupied(tablesInZone);
			}
		});
		btnSetUnoccupied.setBounds(183, 272, 107, 23);
		add(btnSetUnoccupied);
		
		JButton btnLogoutOfZone = new JButton("Logout of Zone");
		btnLogoutOfZone.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				welcomeScreen.swapLoginWaiterPanelLogout();
				welcomeScreen.switchToFullScreen();
			}
		});
		btnLogoutOfZone.setBounds(286, 310, 116, 23);
		add(btnLogoutOfZone);
		
		JButton btnUpdateOrderStatus = new JButton("Update Order Status");
		btnUpdateOrderStatus.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				updateOrderToFinished(zoneOrdersOut);
			}
		});
		btnUpdateOrderStatus.setBounds(468, 272, 148, 23);
		add(btnUpdateOrderStatus);
	}
	
	private void populateTablesInZone(ArrayList<TableInfo> tables)
	{
		DefaultTableModel defaultTableZone = new DefaultTableModel(new Object[][][][]{},new String[]{"Table#","Table Name",
				"Occupied","Service Req."});
		
		for(TableInfo table: tables)
		{
			defaultTableZone.addRow(new Object[]{table.getTableNumber(),table.getTableName(), table.isTableOccupied(), null});		
		}
		
		tablesInZone.setModel(defaultTableZone);
	}
	
	//Go through every table in the zone see if they have an orderChunk with the status "OUT"
	//if so add it to the table
	private void populateZoneOrdersOut(ArrayList<TableInfo> tables)
	{
		DefaultTableModel defaultZoneOrder = new DefaultTableModel(new Object[][][][][]{},new String[]{"Table#","Table Name",
				"Order ID", "Dishes","Status"});
		
		for (TableInfo table: tables)
		{
			ArrayList<OrderChunk> tablesOrderChunks = table.getCustomerTable().getListOfOrderChunks();
			
			for (OrderChunk oc: tablesOrderChunks)
			{
				if (oc.getOrderStatus().equals(ORDERSTATUSENUMS.OUT))
				{
					String orderID = oc.getOrderID();
					String dishes="";
					for (SingleItemWithNote si: oc.getItems())
					{
						dishes += si.getItem().getName();
					}
					defaultZoneOrder.addRow(new Object[]{table.getTableNumber(),table.getTableName(), orderID,
							dishes, "Out"});
				}
			}
		}
		
		zoneOrdersOut.setModel(defaultZoneOrder);
	}
	
	private void setTableUnoccupied(JTable selectedTable)
	{
		int tableID = (Integer) selectedTable.getValueAt(selectedTable.getSelectedRow(), 0);
		restaurantSystem.DBInteractor.updateTableStatus(tableID, "0");
	}
	
	private void updateOrderToFinished(JTable selectedTable)
	{
		String orderID = (String) selectedTable.getValueAt(selectedTable.getSelectedRow(), 3);
		restaurantSystem.DBInteractor.updateOrderStatus(orderID, ORDERSTATUSENUMS.FINISHED);
	}
	
	private void updateTableServiced(JTable selectedTable)
	{
		String tableID = (String) selectedTable.getValueAt(selectedTable.getSelectedRow(), 0);
		restaurantSystem.DBInteractor.setTableRequestServiceToNone(Integer.parseInt(tableID));
	}

}
