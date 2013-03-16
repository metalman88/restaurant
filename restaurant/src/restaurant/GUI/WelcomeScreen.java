package restaurant.GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JSplitPane;
import javax.swing.JScrollPane;
import java.awt.Color;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.Canvas;
import javax.swing.JTabbedPane;
import java.awt.GridLayout;
import net.miginfocom.swing.MigLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class WelcomeScreen extends JFrame {

	private JPanel contentPane;
	private JTextField tableNumberField;
	private JTextField tableNameField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WelcomeScreen frame = new WelcomeScreen();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public WelcomeScreen() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1024, 720);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 1008, 682);
		contentPane.add(tabbedPane);
		
		JPanel customerPanel = new JPanel();
		tabbedPane.addTab("Customer", null, customerPanel, null);
		customerPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Table Number:                          -or-                          Table Name:");
		lblNewLabel.setBounds(346, 234, 310, 14);
		customerPanel.add(lblNewLabel);
		
		tableNameField = new JTextField();
		tableNameField.setColumns(10);
		tableNameField.setBounds(573, 259, 93, 20);
		customerPanel.add(tableNameField);
		
		tableNumberField = new JTextField();
		tableNumberField.setBounds(339, 259, 93, 20);
		customerPanel.add(tableNumberField);
		tableNumberField.setColumns(10);
		
		JButton loginButton = new JButton("Login");
		loginButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				loginCustomer(tableNumber);
			}
		});
		loginButton.setBounds(457, 328, 89, 23);
		customerPanel.add(loginButton);
		
		JLabel lblNewLabel_1 = new JLabel("Error Label, No Such Table");
		lblNewLabel_1.setForeground(Color.RED);
		lblNewLabel_1.setBounds(435, 303, 133, 14);
		customerPanel.add(lblNewLabel_1);
		
		String[] intList= new String[200];
		for(int i = 0 ; i < 200;i++){
			intList[i] = String.valueOf(i+1);
			
		}
		
		JPanel checkInPanel = new JPanel();
		tabbedPane.addTab("Check In", null, checkInPanel, null);
		
		JPanel serverPanel = new JPanel();
		tabbedPane.addTab("Server", null, serverPanel, null);
		
		JPanel kitchPanel = new JPanel();
		tabbedPane.addTab("Kitchen", null, kitchPanel, null);
		
	}

	protected boolean loginCustomer(String tableNumber) {
		
		try
		{
			Integer.parseInt(tableNumber.trim());
			//this will create objects based on tableNumber
		}
		catch(NumberFormatException e)
		{
			//should be a table name and not number
		}
	}
}
