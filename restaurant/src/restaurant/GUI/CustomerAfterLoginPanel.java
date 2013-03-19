package restaurant.GUI;

import javax.swing.JPanel;

import restaurant.system.Bill;
import restaurant.system.CustomerTable;
import restaurant.system.MenuItem;
import restaurant.system.NutritionInfo;
import restaurant.system.RestaurantSystem;
import restaurant.system.SingleItemWithNote;
import restuarant.enums.CATEGORYENUMS;

import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JSplitPane;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JProgressBar;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JTextField;

//Jesse working on this class
public class CustomerAfterLoginPanel extends JPanel{
	
	private RestaurantSystem restaurantSystem;
	private WelcomeScreen welcomeScreen;
	private JTable drinksTable,appetizerTable,entreeTable,dessertTable,otherTable,orderTable;
	private DefaultTableModel drinksTableModel,appetizerTableModel,entreeTableModel,
							  dessertTableModel,otherTableModel,orderTableModel;
	JTabbedPane tabbedPane;
	CustomerTable customerTable;
	JTextPane notesTextPane;
	JTextPane receiptTextPane;
	
	
	
	public CustomerAfterLoginPanel(RestaurantSystem restaurantSystem,WelcomeScreen welcomeScreen,CustomerTable customerTable)
	{
		this.restaurantSystem = restaurantSystem;
		this.welcomeScreen = welcomeScreen;
		this.customerTable = customerTable;
		setLayout(null);
		//create the table objects
		orderTable = new JTable();
		drinksTable = new JTable();
		appetizerTable = new JTable();
		otherTable = new JTable();
		entreeTable = new JTable();
		dessertTable = new JTable();
		//create the table model objects
		drinksTableModel = createDefaultTableModel(restaurantSystem.menu.getCategoryItems(CATEGORYENUMS.DRINK),drinksTable);
		appetizerTableModel = createDefaultTableModel(restaurantSystem.menu.getCategoryItems(CATEGORYENUMS.APPETIZER),appetizerTable);
		entreeTableModel = createDefaultTableModel(restaurantSystem.menu.getCategoryItems(CATEGORYENUMS.ENTREE),entreeTable);
		dessertTableModel = createDefaultTableModel(restaurantSystem.menu.getCategoryItems(CATEGORYENUMS.DESSERT),dessertTable);
		otherTableModel = createDefaultTableModel(restaurantSystem.menu.getCategoryItems(CATEGORYENUMS.OTHER),otherTable);
		orderTableModel = createDefaultTableModel(new ArrayList<MenuItem>(),orderTable);
//		//attach table models to tables
		orderTable.setModel(orderTableModel);
		appetizerTable.setModel(appetizerTableModel);
		otherTable.setModel(otherTableModel);
		entreeTable.setModel(entreeTableModel);
		dessertTable.setModel(dessertTableModel);
		//setup widths for price
		//drinksTable.getColumnModel().getColumn(1).setMaxWidth(45);
		setUpPanel();
		
		
	
	}
	
	public void  setUpPanel()
	{

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(59, 116, 240, 490);
		add(tabbedPane);
		
		JPanel drinksTab = new JPanel();
		tabbedPane.addTab("Drinks", null, drinksTab, null);
		drinksTab.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		drinksTab.add(scrollPane_1, BorderLayout.CENTER);
		
		notesTextPane = new JTextPane();
		notesTextPane.setBounds(272, 642, 152, 104);
		add(notesTextPane);
		
		receiptTextPane = new JTextPane();
		receiptTextPane.setEditable(false);
		
		
//		orderTable.setModel(new DefaultTableModel(
//			new Object[][] {
//				{null, null},
//				{null, null},
//			},
//			new String[] {
//				"Item", "Price"
//			}
//		) {
//			Class[] columnTypes = new Class[] {
//				Object.class, String.class
//			};
//			public Class getColumnClass(int columnIndex) {
//				return columnTypes[columnIndex];
//			}
//			boolean[] columnEditables = new boolean[] {
//				false, true
//			};
//			public boolean isCellEditable(int row, int column) {
//				return columnEditables[column];
//			}
//		});
		
		
		scrollPane_1.setViewportView(drinksTable);
		
		JPanel appetizerTab = new JPanel();
		tabbedPane.addTab("Appetizer", null, appetizerTab, null);
		appetizerTab.setLayout(new BorderLayout(0, 0));
		JScrollPane scrollPane_2 = new JScrollPane();
		appetizerTab.add(scrollPane_2, BorderLayout.CENTER);
		scrollPane_2.setViewportView(appetizerTable);
		
		JPanel entreeTab = new JPanel();
		tabbedPane.addTab("Entree", null, entreeTab, null);
		entreeTab.setLayout(new BorderLayout(0, 0));		
		JScrollPane scrollPane_3 = new JScrollPane();
		entreeTab.add(scrollPane_3, BorderLayout.CENTER);
		scrollPane_3.setViewportView(entreeTable);
		
		JPanel dessertTab = new JPanel();
		tabbedPane.addTab("Dessert", null, dessertTab, null);
		dessertTab.setLayout(new BorderLayout(0, 0));
		JScrollPane scrollPane_4 = new JScrollPane();
		dessertTab.add(scrollPane_4, BorderLayout.CENTER);
		scrollPane_4.setViewportView(dessertTable);
		
		JPanel otherTab = new JPanel();
		tabbedPane.addTab("Other", null, otherTab, null);
		otherTab.setLayout(new BorderLayout(0, 0));		
		JScrollPane scrollPane_5 = new JScrollPane();
		otherTab.add(scrollPane_5, BorderLayout.CENTER);
		scrollPane_5.setViewportView(otherTable);
		
		JLabel billLabel = new JLabel("Bill");
		billLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		billLabel.setBounds(808, 74, 33, 14);
		add(billLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(711, 138, 221, 468);
		add(scrollPane);
		
		scrollPane.setViewportView(receiptTextPane);
		
		JLabel menuLabel = new JLabel("Menu");
		menuLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		menuLabel.setBounds(162, 74, 46, 14);
		add(menuLabel);
		
		JButton paybillButton = new JButton("Pay Bill");
		paybillButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				PaymentVerificationDialog paymentDialog = new PaymentVerificationDialog();
				paymentDialog.setVisible(true);
				
			}
		});
		paybillButton.setBounds(773, 617, 89, 23);
		add(paybillButton);
		
		JButton logoutButton = new JButton("Logout");
		logoutButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
								
				restaurantSystem.logoutTablet();
				welcomeScreen.swapLoginCustomerPanel();
				welcomeScreen.switchToRegularSizeScreen();
			}
		});
		logoutButton.setBounds(911, 0, 89, 23);
		add(logoutButton);
		
		JButton addButton = new JButton("Add >>");
		addButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				addSelectedRowToOrderTable();
			}
		});
		addButton.setBounds(309, 336, 81, 23);
		add(addButton);
		
		JPanel panel = new JPanel();
		panel.setBounds(400, 132, 203, 474);
		add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane_6 = new JScrollPane();
		panel.add(scrollPane_6, BorderLayout.CENTER);
		
		scrollPane_6.setViewportView(orderTable);
		
		JButton confirmButton = new JButton("Confirm >>");
		confirmButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				orderTable.removeAll();
				customerTable.submitOrder();
				receiptTextPane.setText("");
				receiptTextPane.setText(new Bill(customerTable.getListOfOrderChunks()).billInfo());
				
			}
		});
		confirmButton.setBounds(613, 336, 99, 23);
		add(confirmButton);
		
		JButton requestServiceButton = new JButton("Request Service");
		requestServiceButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				restaurantSystem.DBInteractor.serviceRequested(restaurantSystem.getCurTable().tableNumber);
				//test//testt
				
			}
		});
		requestServiceButton.setBounds(432, 60, 137, 47);
		add(requestServiceButton);
		
		JButton nutritionInfoButton = new JButton("Nutrition Info");
		nutritionInfoButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				NutritionInfo nutrition = getNutritionInfoForSelectedItem();
				
			}
		});
		nutritionInfoButton.setBounds(124, 617, 107, 23);
		add(nutritionInfoButton);
		
		JLabel lblNotesForChef = new JLabel("Notes for Chef");
		lblNotesForChef.setBounds(309, 621, 110, 14);
		add(lblNotesForChef);
		
		
	}
	
	public DefaultTableModel createDefaultTableModel(ArrayList<MenuItem> menuItems,JTable tableToAttachTo)
	{
		
		DefaultTableModel tableModel = new DefaultTableModel(new Object[][][]{},new String[]{"Item","Price"});
		tableToAttachTo.setModel(tableModel);
		tableToAttachTo.getColumnModel().getColumn(1).setMaxWidth(45);
		
		for(int i = 0 ; i < menuItems.size();i++)
		{
			tableModel.addRow(new Object[]{menuItems.get(i).getName(),formatPrice(menuItems.get(i).getPrice()),menuItems.get(i)});
		}
		
		return tableModel;
	}
	
	private NutritionInfo getNutritionInfoForSelectedItem()
	{
		String currentTabName = tabbedPane.getTitleAt(tabbedPane.getSelectedIndex());
		
		if(currentTabName.equals("Appetizer"))
		{
			buttonClickTableMethodThing(appetizerTable);
		}
		else if(currentTabName.equals("Entree"))
		{
			buttonClickTableMethodThing(entreeTable);
		}
		else if(currentTabName.equals("Dessert"))
		{
			buttonClickTableMethodThing(dessertTable);
		}
		else if(currentTabName.equals("Other"))
		{
			buttonClickTableMethodThing(otherTable);
		}
		else if(currentTabName.equals("Drinks"))
		{
			buttonClickTableMethodThing(drinksTable);
		}
		
		return new NutritionInfo("-1","-1", "-1","-1","-1","-1","-1");
	}
	private void addSelectedRowToOrderTable()
	{
		
		String currentTabName = tabbedPane.getTitleAt(tabbedPane.getSelectedIndex());
		
		if(currentTabName.equals("Appetizer"))
		{
			returnNutritionInfo(appetizerTable);
		}
		else if(currentTabName.equals("Entree"))
		{
			returnNutritionInfo(entreeTable);
		}
		else if(currentTabName.equals("Dessert"))
		{
			returnNutritionInfo(dessertTable);
		}
		else if(currentTabName.equals("Other"))
		{
			returnNutritionInfo(otherTable);
		}
		else if(currentTabName.equals("Drinks"))
		{
			returnNutritionInfo(drinksTable);
		}
		
	}

	private NutritionInfo returnNutritionInfo(JTable focusedTable)
	{
		NutritionInfo itemGotten = ((MenuItem)focusedTable.getValueAt(focusedTable.getSelectedRow(), 2)).getNutritionInfo();
		if(itemGotten==null)
		{
			return new NutritionInfo("-1","-1", "-1","-1","-1","-1","-1");
		}
		else
		{
			return itemGotten;
		}
	}
	
	private void buttonClickTableMethodThing(JTable focusedTable)
	{
		String name = (String) focusedTable.getValueAt(focusedTable.getSelectedRow(), 0);
		String price = (String) focusedTable.getValueAt(focusedTable.getSelectedRow(), 1);
		orderTableModel.addRow(new Object[]{name,price});
		SingleItemWithNote singleItemWithNote = new SingleItemWithNote((MenuItem)focusedTable.getValueAt(focusedTable.getSelectedRow(),2),notesTextPane.getText());
		customerTable.getCurrentOrderChunk().addItem(singleItemWithNote);
		notesTextPane.setText("");
	}
	
	private String formatPrice(Double price)
	{
		String priceString = price.toString();
		
			String centsOnly = priceString.substring(priceString.length()-3, priceString.length());
	
		while(!centsOnly.substring(0,1).equals("."))
		{
			priceString += "0";
			centsOnly = priceString.substring(priceString.length()-3, priceString.length());
		}
		
		return "$"+priceString;
		
		
	}
}
