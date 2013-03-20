package restaurant.GUI;

import javax.swing.JPanel;

import restaurant.system.MenuItem;
import restaurant.system.NutritionInfo;
import restaurant.system.OrderChunk;
import restaurant.system.RestaurantSystem;
import restaurant.system.SingleItemWithNote;
import restuarant.enums.CATEGORYENUMS;
import restuarant.enums.ORDERSTATUSENUMS;

import javax.swing.JTextField;
import javax.swing.JLabel;

import java.awt.EventQueue;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class KitchenPanel extends JPanel{
	private static RestaurantSystem restaurantSystem;
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
		scrollPane.setBounds(10, 63, 448, 233);
		add(scrollPane);
		
		
		//Set up kitchenTable
		kitchenTable = new JTable();
		populateKitchenTable(restaurantSystem.DBInteractor.getAllUnfinishedOrders());
		kitchenTable.setToolTipText("");
		scrollPane.setViewportView(kitchenTable);
		
		JButton btnUpdateOrderStatus = new JButton("Update Order Status");
		btnUpdateOrderStatus.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				updateOrderIDStatus(kitchenTable);
			}


		
		});
		btnUpdateOrderStatus.setBounds(169, 319, 171, 23);
		add(btnUpdateOrderStatus);
	}
	
	private void populateKitchenTable(ArrayList<OrderChunk> orderChunks)
	{
		
		DefaultTableModel defaultKitchen = new DefaultTableModel(new Object[][][][]{},new String[]{"Order ID","Dish Name",
				"Notes","Order Status"});
		
		
		for(OrderChunk orderChunk: orderChunks)
		{
			String chunkID = orderChunk.getOrderID();
			String chunkStatus = orderChunk.getOrderStatus().toString();
			
			for (SingleItemWithNote si: orderChunk.getItems())
			{
				defaultKitchen.addRow(new Object[]{chunkID,si.getItem().getName(), si.getNote(), chunkStatus});
			}		
		}
		
		kitchenTable.setModel(defaultKitchen);
	}
	
	private void updateOrderIDStatus(JTable focusedTable)
	{
		String orderID = (String) focusedTable.getValueAt(focusedTable.getSelectedRow(), 0);
		String status = (String) focusedTable.getValueAt(focusedTable.getSelectedRow(), 3);
		
		if (status.equals(ORDERSTATUSENUMS.COOKING.toString()))
		{
			restaurantSystem.DBInteractor.updateOrderStatusInDB(orderID, ORDERSTATUSENUMS.FINISHED);
		}
		
		if (status.equals(ORDERSTATUSENUMS.PREPPING.toString()))
		{
			restaurantSystem.DBInteractor.updateOrderStatusInDB(orderID, ORDERSTATUSENUMS.COOKING);
		}
	}
}
