import javax.swing.*;
import javax.swing.table.AbstractTableModel;

import java.awt.*;
import java.awt.event.*;

public class CheckInInterface {
	
	 static JTextField customerName = new JTextField(10);
     static JTextField partySize = new JTextField(10);
	
	public static void main (String[] args){  
	  JFrame frame = new JFrame("Check-In Desk");
	  frame.setVisible(true);
	  frame.setSize(200,200);
	  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	  JPanel panel = new JPanel();
	  frame.add(panel);
	  JButton button = new JButton("Waitlist for Tables");
	  panel.add(button);
	  button.addActionListener(new showOrderList());

	  JButton button2 = new JButton("Chart of Tables");
	  panel.add(button2);
	  button2.addActionListener(new showTableChart()); 
	 
	}
	

	static class showOrderList implements ActionListener {        
	  public void actionPerformed (ActionEvent e) {     
	    JFrame frame2 = new JFrame("Waitlist for Tables");
	    frame2.setVisible(true);
	    frame2.setSize(500,600);
		
        final JTable orders = new JTable(new waitlist());
        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(orders);
        scrollPane.setSize(200,200);
        JPanel topPanel = new JPanel();
        
        JLabel CustomerName = new JLabel("Customer Name");
        JLabel PartySize = new JLabel("Party Size");
        
        JButton addCustomer = new JButton("Add Customer");
        addCustomer.addActionListener(new ActionListener() {
        	 
            public void actionPerformed(ActionEvent e)
            {
            	//TODO: Send to waitlist table
                //Execute when button is pressed
            	
            	int nextRowId = orders.getRowCount();
            	int nextColumnId = orders.getColumnCount();
            	orders.setValueAt(new Object[] {customerName.getText(), partySize.getText(), new Boolean(false)}, 
            			nextRowId, nextColumnId);
            			
      
                System.out.println("Customer Name:" + customerName.getText());
                System.out.println("Party Size:" + partySize.getText());
            }
        });      
        
        /*
        JButton refresh = new JButton("Refresh");
        addCustomer.addActionListener(new ActionListener() {
       	 
            public void actionPerformed(ActionEvent e)
            {
                //Execute when button is pressed
                System.out.println("Refresh button clicked");
            }
        });      
        */
       
        //Add the scroll pane to this panel.
        frame2.add(topPanel);
        topPanel.add(scrollPane);
        topPanel.add(CustomerName);
        topPanel.add(customerName);
        topPanel.add(PartySize);
        topPanel.add(partySize);
        topPanel.add(addCustomer);
        //topPanel.add(refresh);
        
	  }
	}   
	
	static class waitlist extends AbstractTableModel {
		 String[] columnNames = {"Name", "Size of Party", "Seated"};

			//Dummy data, SQL query database to get table number and order
			Object[][] data = {
				    {"Kevin", "5", new Boolean(false)},
				    {"John", "10", new Boolean(false)},
				    {"Francis", "2", new Boolean(true)},
				    {"Will", "4", new Boolean(true)},
				};

			public int getColumnCount() {
	            return columnNames.length;
	        }
	 
	        public int getRowCount() {
	            return data.length;
	        }
	 
	        public String getColumnName(int col) {
	            return columnNames[col];
	        }
	 
	        public Object getValueAt(int row, int col) {
	            return data[row][col];
	        }
	        
	        public Class getColumnClass(int c) {
	            return getValueAt(0, c).getClass();
	        }
	        public boolean isCellEditable(int row, int col) {
	            //Note that the data/cell address is constant,
	            //no matter where the cell appears onscreen.
	            if (col < 2) {
	                return false;
	            } else {
	                return true;
	            }
	        }
	        public void setValueAt(Object value, int row, int col) {
	            data[row][col] = value;
	            fireTableCellUpdated(row, col);
	        }
	 
	 }
	
	static class showTableChart implements ActionListener {        
	  public void actionPerformed (ActionEvent e) {     
	    JFrame frame3 = new JFrame("Tables");
	    frame3.setVisible(true);
	    frame3.setSize(400,300);

	    JLabel label = new JLabel("Table Chart");
	    
	    //Creates JTable, with AbstractTableModel
	    JTable orders = new JTable(new tableChart());
        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(orders);

        //Add the scroll pane to this panel.
        frame3.add(scrollPane);
        scrollPane.add(label);
	  }
	}   
	
	static class tableChart extends AbstractTableModel {
		 String[] columnNames = {"Table Number", "Seating Capacity", "Occupied"};

			//Dummy data, SQL query database to get table number and order
			Object[][] data = {
				    {"1", "5", new Boolean(false)},
				    {"2", "10", new Boolean(false)},
				    {"3", "2", new Boolean(true)},
				    {"4", "4", new Boolean(true)},
				    {"5", "4", new Boolean(false)},
				};

			public int getColumnCount() {
	            return columnNames.length;
	        }
	 
	        public int getRowCount() {
	            return data.length;
	        }
	 
	        public String getColumnName(int col) {
	            return columnNames[col];
	        }
	 
	        public Object getValueAt(int row, int col) {
	            return data[row][col];
	        }
	        
	        public Class getColumnClass(int c) {
	            return getValueAt(0, c).getClass();
	        }
	        public boolean isCellEditable(int row, int col) {
	            //Note that the data/cell address is constant,
	            //no matter where the cell appears onscreen.
	            if (col < 2) {
	                return false;
	            } else {
	                return true;
	            }
	        }
	        public void setValueAt(Object value, int row, int col) {
	            data[row][col] = value;
	            fireTableCellUpdated(row, col);
	        }
	 
	 }
	
}
