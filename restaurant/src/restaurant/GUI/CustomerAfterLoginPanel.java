package restaurant.GUI;

import javax.swing.JPanel;

import restaurant.system.RestaurantSystem;
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

//Jesse working on this class
public class CustomerAfterLoginPanel extends JPanel{
	
	private RestaurantSystem restaurantSystem;
	private WelcomeScreen welcomeScreen;
	private JTable drinksTable;
	private JTable appetizerTable;
	private JTable entreeTable;
	private JTable dessertTable;
	private JTable otherTable;
	private JTable orderTable;
	
	public CustomerAfterLoginPanel(RestaurantSystem restaurantSystem,WelcomeScreen welcomeScreen)
	{
		this.restaurantSystem = restaurantSystem;
		this.welcomeScreen = welcomeScreen;
		setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(59, 116, 240, 490);
		add(tabbedPane);
		
		JPanel drinksTab = new JPanel();
		tabbedPane.addTab("Drinks", null, drinksTab, null);
		drinksTab.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		drinksTab.add(scrollPane_1, BorderLayout.CENTER);
		
		drinksTable = new JTable();
		drinksTable.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
			},
			new String[] {
				"New column", "New column"
			}
		));
		scrollPane_1.setViewportView(drinksTable);
		
		JPanel appetizerTab = new JPanel();
		tabbedPane.addTab("Appetizer", null, appetizerTab, null);
		appetizerTab.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane_2 = new JScrollPane();
		appetizerTab.add(scrollPane_2, BorderLayout.CENTER);
		
		appetizerTable = new JTable();
		appetizerTable.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
			},
			new String[] {
				"New column", "New column"
			}
		));
		scrollPane_2.setViewportView(appetizerTable);
		
		JPanel entreeTab = new JPanel();
		tabbedPane.addTab("Entree", null, entreeTab, null);
		entreeTab.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane_3 = new JScrollPane();
		entreeTab.add(scrollPane_3, BorderLayout.CENTER);
		
		entreeTable = new JTable();
		entreeTable.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null},
				{null, null},
			},
			new String[] {
				"New column", "New column"
			}
		));
		scrollPane_3.setViewportView(entreeTable);
		
		JPanel dessertTab = new JPanel();
		tabbedPane.addTab("Dessert", null, dessertTab, null);
		dessertTab.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane_4 = new JScrollPane();
		dessertTab.add(scrollPane_4, BorderLayout.CENTER);
		
		dessertTable = new JTable();
		dessertTable.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null},
				{null, null},
				{null, null},
			},
			new String[] {
				"New column", "New column"
			}
		));
		scrollPane_4.setViewportView(dessertTable);
		
		JPanel otherTab = new JPanel();
		tabbedPane.addTab("Other", null, otherTab, null);
		otherTab.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane_5 = new JScrollPane();
		otherTab.add(scrollPane_5, BorderLayout.CENTER);
		
		otherTable = new JTable();
		otherTable.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null},
				{null, null},
				{null, null},
			},
			new String[] {
				"New column", "New column"
			}
		));
		scrollPane_5.setViewportView(otherTable);
		
		JLabel billLabel = new JLabel("Bill");
		billLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		billLabel.setBounds(808, 74, 33, 14);
		add(billLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(711, 138, 221, 468);
		add(scrollPane);
		
		JTextPane txtpnAssadf = new JTextPane();
		scrollPane.setViewportView(txtpnAssadf);
		txtpnAssadf.setText("assadf");
		
		JLabel menuLabel = new JLabel("Menu");
		menuLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		menuLabel.setBounds(162, 74, 46, 14);
		add(menuLabel);
		
		JButton paybillButton = new JButton("Pay Bill");
		paybillButton.setBounds(773, 617, 89, 23);
		add(paybillButton);
		
		JButton logoutButton = new JButton("Logout");
		logoutButton.setBounds(911, 0, 89, 23);
		add(logoutButton);
		
		JButton addButton = new JButton("Add >>");
		addButton.setBounds(309, 336, 81, 23);
		add(addButton);
		
		JPanel panel = new JPanel();
		panel.setBounds(400, 132, 203, 474);
		add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane_6 = new JScrollPane();
		panel.add(scrollPane_6, BorderLayout.CENTER);
		
		orderTable = new JTable();
		orderTable.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null},
				{null, null},
			},
			new String[] {
				"Item", "Price"
			}
		) {
			Class[] columnTypes = new Class[] {
				Object.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		orderTable.getColumnModel().getColumn(0).setResizable(false);
		orderTable.getColumnModel().getColumn(1).setResizable(false);
		orderTable.getColumnModel().getColumn(1).setPreferredWidth(45);
		scrollPane_6.setViewportView(orderTable);
		
		JButton confirmButton = new JButton("Confirm >>");
		confirmButton.setBounds(613, 336, 89, 23);
		add(confirmButton);
		
		JButton requestServiceButton = new JButton("Request Service");
		requestServiceButton.setBounds(439, 60, 119, 47);
		add(requestServiceButton);
		
		JButton nutritionInfoButton = new JButton("Nutrition Info");
		nutritionInfoButton.setBounds(124, 617, 107, 23);
		add(nutritionInfoButton);
		setUpPanel();
	}
	
	public void  setUpPanel()
	{
		//keep in mind that this panel will exist inside full screen panel
		
		//Do everything here (look at CustomerLoginPanel.java as example)
		//Set up your JPanel, and interact with the restuarantSystem accordingling
		//this class is a JPanel class so do stuff like add(new JTextField())
		//or right click on this class and open with WindowBuilder if installed.
		//
		//User the restaurantSystem variable to interact with the class
	}
}
