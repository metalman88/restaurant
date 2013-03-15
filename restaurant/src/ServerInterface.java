import javax.swing.*;
import javax.swing.table.AbstractTableModel;

import java.awt.*;
import java.awt.event.*;

public class ServerInterface {
	
	public static void main (String[] args){  
	  JFrame frame = new JFrame("ServerInterface");
	  frame.setVisible(true);
	  frame.setSize(200,200);
	  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	  JPanel panel = new JPanel();
	  frame.add(panel);
	  JButton button = new JButton("List of Orders");
	  panel.add(button);
	  button.addActionListener(new showOrderList());

	  JButton button2 = new JButton("Chart of Tables");
	  panel.add(button2);
	  button2.addActionListener (new showTableChart()); 
	}

	static class showOrderList implements ActionListener {        
	  public void actionPerformed (ActionEvent e) {     
	    JFrame frame2 = new JFrame("Orders");
	    frame2.setVisible(true);
	    frame2.setSize(400,300);
	    JLabel label = new JLabel("List of Orders");
		
        JTable orders = new JTable(new orderTable());
        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(orders);

        //Add the scroll pane to this panel.
        frame2.add(scrollPane);
        scrollPane.add(label);
	  }
	}   
	
	static class orderTable extends AbstractTableModel {
		 String[] columnNames = {"Table Number",
	        "Order"};

			//Dummy data, SQL query database to get table number and order
			Object[][] data = {
				    {"1", "Ham & Cheese Croissant"},
				    {"5", "Clam chowder"},
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
	 
	 }
	
	static class showTableChart implements ActionListener {        
	  public void actionPerformed (ActionEvent e) {     
	    JFrame frame3 = new JFrame("Table Chart");
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
		  	String[] columnNames = {"Table Number",
			        "Status", "Service Requested"};

			//Dummy data, SQL query database to get table number and order
			Object[][] data = {
				    {"1", "Occupied", new Boolean(false)},
				    {"2", "Unoccupied", new Boolean(true)},
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
