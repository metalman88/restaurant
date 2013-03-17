package restaurant.GUI;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import restaurant.system.RestaurantSystem;

//Jesse working on this class
public class CustomerLoginPanel extends JPanel {
	
	RestaurantSystem restaurantSystem;
	WelcomeScreen welcomeScreen;
	
	public CustomerLoginPanel(RestaurantSystem restaurantSystem,WelcomeScreen welcomeScreen)
	{
		this.restaurantSystem = restaurantSystem;
		this.welcomeScreen = welcomeScreen;
		setUpPanel();
	}
	
	public void setUpPanel()
	{
		setLayout(null);
		
		final JButton loginButton = new JButton("Login");
		
		JLabel lblNewLabel = new JLabel("Table Number:                          -or-                          Table Name:");
		lblNewLabel.setBounds(346, 234, 410, 14);
		add(lblNewLabel);
		
		final JTextField tableNameField = new JTextField();
		tableNameField.setColumns(10);
		tableNameField.setBounds(589, 259, 93, 20);
		add(tableNameField);

		final JTextField tableNumberField = new JTextField();
		tableNumberField.setBounds(339, 259, 93, 20);
		add(tableNumberField);
		tableNumberField.setColumns(10);
		
		tableNameField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(!tableNameField.getText().isEmpty())
				{
					tableNumberField.setEditable(false);
				}
				else if(e.getKeyCode()==e.VK_ENTER)
				{
					loginButton.doClick();
				}
				else
				{
					tableNumberField.setEditable(true);
				}
			}
		});
		
		tableNumberField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(!tableNumberField.getText().isEmpty())
				{
					tableNameField.setEditable(false);
				}
				else if(e.getKeyCode() == e.VK_ENTER)
				{
					loginButton.doClick();
				}
				else
				{
					tableNameField.setEditable(true);
				}
				
			}
		});
		
		final JLabel errorLabel = new JLabel("");
		errorLabel.setForeground(Color.RED);
		errorLabel.setBounds(427, 303, 309, 14);
		add(errorLabel);
		
		//mouse adapter for button click on login button
		MouseAdapter mouseAdapter = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(!tableNameField.getText().isEmpty()&&tableNumberField.getText().isEmpty())
				{
					//must be checking by table name
					if(restaurantSystem.loginUser(tableNameField.getText().trim()))
					{
						errorLabel.setText("");
						welcomeScreen.swapLoginCustomerPanel();
						welcomeScreen.switchToFullScreen();
					}
					else
					{
						errorLabel.setText("No Such Customer Table Exist");
					}
				}
				else if(!tableNumberField.getText().isEmpty()&&tableNameField.getText().isEmpty())
				{
					//must be checking by table number
					if(restaurantSystem.loginUser(tableNumberField.getText().trim()))
					{
						errorLabel.setText("");
						welcomeScreen.swapLoginCustomerPanel();
						welcomeScreen.switchToFullScreen();
					}
					else
					{
						errorLabel.setText("");
					}
				}
				else
				{
					errorLabel.setText("Please Enter Table Number Or Name");
				}
			}
		};
		loginButton.addMouseListener(mouseAdapter);
		loginButton.setBounds(457, 328, 89, 23);
		add(loginButton);
	}

}
