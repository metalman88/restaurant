package restaurant.GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import java.awt.CardLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PaymentVerificationDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;


	public PaymentVerificationDialog() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblCardnumber = new JLabel("Card Number:");
			lblCardnumber.setBounds(46, 11, 90, 14);
			contentPanel.add(lblCardnumber);
		}
		{
			textField = new JTextField();
			textField.setBounds(142, 8, 199, 20);
			contentPanel.add(textField);
			textField.setColumns(10);
		}
		{
			JLabel lblVerficationCode = new JLabel("Verfication Code:");
			lblVerficationCode.setBounds(23, 36, 118, 14);
			contentPanel.add(lblVerficationCode);
		}
		{
			textField_1 = new JTextField();
			textField_1.setBounds(142, 33, 49, 20);
			contentPanel.add(textField_1);
			textField_1.setColumns(10);
		}
		{
			textField_2 = new JTextField();
			textField_2.setBounds(142, 61, 86, 20);
			contentPanel.add(textField_2);
			textField_2.setColumns(10);
		}
		{
			textField_3 = new JTextField();
			textField_3.setBounds(142, 92, 86, 20);
			contentPanel.add(textField_3);
			textField_3.setColumns(10);
		}
		{
			textField_4 = new JTextField();
			textField_4.setBounds(142, 121, 86, 20);
			contentPanel.add(textField_4);
			textField_4.setColumns(10);
		}
		{
			textField_5 = new JTextField();
			textField_5.setBounds(142, 152, 86, 20);
			contentPanel.add(textField_5);
			textField_5.setColumns(10);
		}
		{
			JLabel lblFirstName = new JLabel("First Name:");
			lblFirstName.setBounds(46, 64, 86, 14);
			contentPanel.add(lblFirstName);
		}
		{
			JLabel lblLastName = new JLabel("Last Name:");
			lblLastName.setBounds(51, 95, 90, 14);
			contentPanel.add(lblLastName);
		}
		{
			JLabel lblBillingAddress = new JLabel("Billing Address:");
			lblBillingAddress.setBounds(37, 124, 114, 14);
			contentPanel.add(lblBillingAddress);
		}
		{
			JLabel lblCity = new JLabel("City:");
			lblCity.setBounds(105, 155, 46, 14);
			contentPanel.add(lblCity);
		}
		{
			JLabel lblState = new JLabel("State:");
			lblState.setBounds(252, 155, 46, 14);
			contentPanel.add(lblState);
		}
		{
			JLabel lblZip = new JLabel("Zip:");
			lblZip.setBounds(105, 189, 46, 14);
			contentPanel.add(lblZip);
		}
		{
			textField_6 = new JTextField();
			textField_6.setBounds(289, 152, 86, 20);
			contentPanel.add(textField_6);
			textField_6.setColumns(10);
		}
		{
			textField_7 = new JTextField();
			textField_7.setBounds(142, 186, 86, 20);
			contentPanel.add(textField_7);
			textField_7.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			buttonPane.setLayout(new BorderLayout(0, 0));
			{
				JButton payButton = new JButton("Pay Button");
				payButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						setVisible(false);
						dispose();
					}
				});
				payButton.setActionCommand("OK");
				buttonPane.add(payButton);
				getRootPane().setDefaultButton(payButton);
			}
		}
	}

}
