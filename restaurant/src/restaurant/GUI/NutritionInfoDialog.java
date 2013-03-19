package restaurant.GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.JSplitPane;

import restaurant.system.NutritionInfo;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class NutritionInfoDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();


	public NutritionInfoDialog(NutritionInfo nutrition) 
	{

		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
			JLabel lblNewLabel = new JLabel("Calories:");
			lblNewLabel.setBounds(108, 10, 83, 14);
			contentPanel.add(lblNewLabel);
		
		
			JLabel caloriesLabel = new JLabel("New label");
			caloriesLabel.setBounds(201, 10, 46, 14);
			contentPanel.add(caloriesLabel);
		
		
			JLabel lblNewLabel_2 = new JLabel("Total Fat:");
			lblNewLabel_2.setBounds(108, 35, 83, 14);
			contentPanel.add(lblNewLabel_2);
		
		
			JLabel totalFatLabel = new JLabel("New label");
			totalFatLabel.setBounds(201, 35, 46, 14);
			contentPanel.add(totalFatLabel);
		
		
			JLabel lblNewLabel_4 = new JLabel("Saturated Fat:");
			lblNewLabel_4.setBounds(108, 60, 83, 14);
			contentPanel.add(lblNewLabel_4);
		
		
			JLabel saturatedFatLabel = new JLabel("New label");
			saturatedFatLabel.setBounds(201, 60, 46, 14);
			contentPanel.add(saturatedFatLabel);
		
		
			JLabel lblNewLabel_6 = new JLabel("Cholesterol:");
			lblNewLabel_6.setBounds(108, 85, 83, 14);
			contentPanel.add(lblNewLabel_6);
		
		
			JLabel cholesterolLabel = new JLabel("New label");
			cholesterolLabel.setBounds(201, 85, 46, 14);
			contentPanel.add(cholesterolLabel);
		
		
			JLabel lblNewLabel_8 = new JLabel("Sodium:");
			lblNewLabel_8.setBounds(108, 110, 83, 14);
			contentPanel.add(lblNewLabel_8);
		
		
			JLabel sodiumLabel = new JLabel("New label");
			sodiumLabel.setBounds(201, 110, 46, 14);
			contentPanel.add(sodiumLabel);
		
		
			JLabel lblNewLabel_10 = new JLabel("Carbohydrates:");
			lblNewLabel_10.setBounds(108, 135, 83, 14);
			contentPanel.add(lblNewLabel_10);
		
		
			JLabel carbohydratesLabel = new JLabel("New label");
			carbohydratesLabel.setBounds(201, 135, 46, 14);
			contentPanel.add(carbohydratesLabel);
		
		
			JLabel lblNewLabel_12 = new JLabel("Protein:");
			lblNewLabel_12.setBounds(108, 160, 83, 14);
			contentPanel.add(lblNewLabel_12);
		
		
			JLabel proteinLabel = new JLabel("New label");
			proteinLabel.setBounds(201, 160, 46, 14);
			contentPanel.add(proteinLabel);
		
		
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			buttonPane.setLayout(new BorderLayout(0, 0));
			
			JButton okButton = new JButton("OK");
			okButton.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					dispose();
				}
			});
			okButton.setActionCommand("OK");
			buttonPane.add(okButton);
			getRootPane().setDefaultButton(okButton);
			
		
	
		caloriesLabel.setText(nutrition.getCalories());
		totalFatLabel.setText(nutrition.getTotalFat()+"g");
		saturatedFatLabel.setText(nutrition.getSaturatedFat()+"g");
		cholesterolLabel.setText(nutrition.getCholesterol()+"g");
		sodiumLabel.setText(nutrition.getSodium()+"g");
		carbohydratesLabel.setText(nutrition.getCarbohydrates()+"g");
		proteinLabel.setText(nutrition.getProtein()+"g");
		
	}

}
