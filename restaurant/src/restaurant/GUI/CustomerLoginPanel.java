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
	
	public CustomerLoginPanel(RestaurantSystem restaurantSystem)
	{
		this.restaurantSystem = restaurantSystem;
		setUpPanel();
	}
	
	public void setUpPanel()
	{
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Table Number:                          -or-                          Table Name:");
		lblNewLabel.setBounds(346, 234, 310, 14);
		add(lblNewLabel);
		
		final JTextField tableNameField = new JTextField();
		tableNameField.setColumns(10);
		tableNameField.setBounds(573, 259, 93, 20);
		add(tableNameField);
		
		final JTextField tableNumberField = new JTextField();
		tableNumberField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				tableNameField.setEditable(false);
				
			}
		});

		tableNumberField.setBounds(339, 259, 93, 20);
		add(tableNumberField);
		tableNumberField.setColumns(10);
		
		final JLabel errorLabel = new JLabel("No Such Customer Tabel Exist");
		errorLabel.setForeground(Color.RED);
		errorLabel.setBounds(427, 303, 148, 14);
		add(errorLabel);
		
		JButton loginButton = new JButton("Login");
		loginButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if(!tableNameField.getText().isEmpty()&&tableNumberField.getText().isEmpty())
				{
					//must be checking by table name
					if(restaurantSystem.loginUser(tableNameField.getText().trim()))
					{
						errorLabel.setText("");
						//need to change the panel
					}
					else
					{
						errorLabel.setText("No Such Customer Tabel Exist");
					}
				}
				else if(!tableNumberField.getText().isEmpty()&&tableNameField.getText().isEmpty())
				{
					//must be checking by table number
					if(restaurantSystem.loginUser(tableNumberField.getText().trim()))
					{
						errorLabel.setText("");
						//need to change the panel
					}
					else
					{
						errorLabel.setText("No Such Customer Tabel Exist");
					}
				}
				else
				{
					errorLabel.setText("No Such Customer Tabel Exist");
				}
			}
		});
		loginButton.setBounds(457, 328, 89, 23);
		add(loginButton);
	}

}
