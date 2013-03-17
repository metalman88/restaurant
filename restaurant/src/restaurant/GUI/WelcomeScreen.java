package restaurant.GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JSplitPane;
import javax.swing.JScrollPane;
import java.awt.Color;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import java.awt.Font;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.Canvas;
import javax.swing.JTabbedPane;
import java.awt.GridLayout;
//import net.miginfocom.swing.MigLayout; 
import java.awt.CardLayout;
import java.awt.FlowLayout;
import javax.swing.JComboBox;
import javax.swing.JButton;

import restaurant.system.RestaurantSystem;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class WelcomeScreen extends JFrame {

	private JPanel contentPane;	
	private JPanel customerContentPane;
	private static RestaurantSystem restaurantSystem;
	private static final int SCREEN_SIZE_X = 1024;
	private static final int SCREEN_SIZE_Y = 720;
	JTabbedPane tabbedPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					restaurantSystem = null;// new RestaurantSystem();
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
		
		customerContentPane = new CustomerAfterLoginPanel(restaurantSystem,this);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, SCREEN_SIZE_X, SCREEN_SIZE_Y);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, SCREEN_SIZE_X-10, SCREEN_SIZE_Y-10);
		contentPane.add(tabbedPane);
		
		

		tabbedPane.addTab("Customer", null, new CustomerLoginPanel(restaurantSystem,this), null);
		tabbedPane.addTab("Check In", null, new CheckInPanel(restaurantSystem), null);
		tabbedPane.addTab("Server", null, new ServerPanel(restaurantSystem), null);
		tabbedPane.addTab("Kitchen", null, new KitchenPanel(restaurantSystem), null);

		
	}
	
	public void switchToFullScreen()
	{
	    getContentPane().setPreferredSize( Toolkit.getDefaultToolkit().getScreenSize());
	    pack();
	    setResizable(false);
	    show();

	    SwingUtilities.invokeLater(new Runnable() {
	      public void run()
	      {
	        Point p = new Point(0, 0);
	        SwingUtilities.convertPointToScreen(p, getContentPane());
	        Point l = getLocation();
	        l.x -= p.x;
	        l.y -= p.y;
	        setLocation(l);
	      }
	    });
		
	}
	
	public void switchToRegularSizeScreen()
	{
		setResizable(true);
		getContentPane().setPreferredSize(new Dimension(SCREEN_SIZE_X, SCREEN_SIZE_Y));
		pack();
		show();
		
	    SwingUtilities.invokeLater(new Runnable() {
		      public void run()
		      {
		        Point p = new Point(0, 0);
		        SwingUtilities.convertPointToScreen(p, getContentPane());
		        Point l = getLocation();
		        l.x += p.x;
		        l.y += p.y;
		        setLocation(l);
		      }
		    });
		
	}
	
	public void swapLoginCustomerPanel()
	{
		if(getContentPane() == contentPane)
		{
			setContentPane(customerContentPane);
		}
		else if(getContentPane() == customerContentPane)
		{
			setContentPane(contentPane);
		}
		
	}
}
