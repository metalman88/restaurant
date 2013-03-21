package restaurant.GUI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.text.TabExpander;

import restaurant.system.Party;
import restaurant.system.RestaurantSystem;
import restaurant.system.TableInfo;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author tai
 */
public class CheckInPanel extends javax.swing.JPanel 
{

	
	public CheckInPanel(RestaurantSystem restaurantSystem) 
	{
		this.restaurantSytem = restaurantSystem;
		setUpPanel();
		configureTableList();
		configureSetOccupiedBut();
		configureUnOccupiedBut();
		configureAddBut();
		configureRemoveBut();
		configureWaitList();
		configureAssignTableBut();
		configurePartySizeSpinner();
	}
	
	private void configurePartySizeSpinner()
	{
		partySizeSpinner.setName(PARTY_SIZE_SPINNER);
	}
	
	private void configureAssignTableBut()
	{
		assignTableBut.setEnabled(false);
		assignTableBut.setName(ASSIGN_TABLE_BUT);
		assignTableBut.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e) 
			{
				assignPartyToTable();
			}
			
			private void assignPartyToTable()
			{
				restaurantTables.getModel().setValueAt
				(RestaurantTableModel.OCCUPIED, 
					restaurantTables.getSelectedRow(),
					RestaurantTableModel.STATUS_COL);
				
				restaurantSytem.getWaitList().remove(waitList.getSelectedRow());
				refreshWaitListTable();
			}
		
		});
	}
	
	private void refreshWaitListTable()
	{
		AbstractTableModel model = (AbstractTableModel) waitList.getModel();
		model.fireTableDataChanged();
	}
	

	private void configureRemoveBut()
	{
		remBut.setName(REM_BUT);
		remBut.setEnabled(false);
		remBut.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e) 
			{
				int selectedRow = waitList.getSelectedRow();
				restaurantSytem.getWaitList().remove(selectedRow);
				AbstractTableModel model = (AbstractTableModel)
						waitList.getModel();
				model.fireTableDataChanged();
			}
			
		});
	}
	
	private void configureAddBut()
	{
		addBut.setName(ADD_BUT);
		addBut.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				String name = nameTextField.getText();
				int partySize =  Integer.parseInt(partySizeSpinner.getValue().toString());
				restaurantSytem.getWaitList().add(new Party(name, partySize));
				AbstractTableModel model = (AbstractTableModel) 
						waitList.getModel();
				model.fireTableDataChanged();
			}
			
		});
	}
	
	private void configureWaitList()
	{
		waitList.setName(WAIT_LIST_TABLE);
		waitList.setColumnSelectionAllowed(false);
		waitList.getSelectionModel()
			.addListSelectionListener(new ListSelectionListener()
			{

				@Override
				public void valueChanged(ListSelectionEvent e) 
				{
					if (selectedAParty())
					{
						remBut.setEnabled(true);
						if (selectedATable())
							assignTableBut.setEnabled(true);
					}
					else
					{
						remBut.setEnabled(false);
						assignTableBut.setEnabled(false);
					}
				}
				
			});
		waitList.setModel(new AbstractTableModel()
		{
			private static final int NAME_COL = 0;
			private static final int SIZE_COL = 1;
			
			ArrayList<Party> partyList = restaurantSytem.getWaitList();
			
			@Override
			public int getRowCount() 
			{
				// TODO Auto-generated method stub
				return partyList.size();
			}

			@Override
			public int getColumnCount() 
			{
				// TODO Auto-generated method stub
				return 2;
			}
			
			@Override
			public String getColumnName(int columnIndex)
			{
				switch (columnIndex)
				{
				case 0:
					return "Name";
				case 1:
					return "Party Size";
				}
				
				return null;
			}

			@Override
			public Object getValueAt(int rowIndex, int columnIndex) 
			{
				// TODO Auto-generated method stub
				Party party = partyList.get(rowIndex);
				switch (columnIndex)
				{
				case NAME_COL:
					return party.getPartyName();
				case SIZE_COL:
					return party.getPartySize();
				default:
					return null;
				}
			}
			
		});
	}
	
	private void configureUnOccupiedBut()
	{
		setUnoccupiedJBut.setEnabled(false);
		setUnoccupiedJBut.setName(SET_UNOCCUPIED_BUT);
		setUnoccupiedJBut.setText(SET_UNOCCUPIED_BUT);
		setUnoccupiedJBut.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				int selectedRow = restaurantTables.getSelectedRow();
				restaurantTables.getModel().setValueAt
				(RestaurantTableModel.UNOCCUPIED, selectedRow, 
						RestaurantTableModel.STATUS_COL);
				
				
			}
			
		});
	}

	private void configureTableList()
	{
		
		restaurantTables.setModel
			(new RestaurantTableModel(restaurantSytem.tableHash.values()));
		restaurantTables.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		restaurantTables.setColumnSelectionAllowed(false);
		restaurantTables.getSelectionModel()
			.addListSelectionListener(new ListSelectionListener()
			{
				
				@Override
				public void valueChanged(ListSelectionEvent e) 
				{
					if (selectedATable())
					{
						CheckInPanel.this.setOccupiiedJBut.setEnabled(true);
						CheckInPanel.this.setUnoccupiedJBut.setEnabled(true);
						if (selectedAParty())
							assignTableBut.setEnabled(true);
					}
					else
					{
						CheckInPanel.this.setOccupiiedJBut.setEnabled(false);
						CheckInPanel.this.setUnoccupiedJBut.setEnabled(false);
						assignTableBut.setEnabled(false);
					}
				}	
			});
	}
	
	private boolean selectedAParty()
	{
		return waitList.getSelectedRow() != -1;
	}
	private boolean selectedATable()
	{
		return restaurantTables.getSelectedRow() != -1;
	}
	
	private void configureSetOccupiedBut()
	{
		setOccupiiedJBut.setEnabled(false);
		this.setOccupiiedJBut.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e) 
			{
				int selectedRow = restaurantTables.getSelectedRow();
				restaurantTables.getModel().setValueAt
				(RestaurantTableModel.OCCUPIED, selectedRow, 
						RestaurantTableModel.STATUS_COL);
				
			}
			
		});
	}
	



	public static class RestaurantTableModel extends AbstractTableModel
	{
		public static final int TABLE_NUM_COL = 0;
		public static final int NAME_COL = 1;
		public static final int ZONE_COL = 2;
		public static final int CAP_COL = 3;
		public static final int STATUS_COL = 4;
		public static final String OCCUPIED = "Occupied";
		public static final String UNOCCUPIED = "Unoccupied";
		
		private List<TableInfo> tables;
		
		public RestaurantTableModel(Collection<TableInfo> tables)
		{
			initTablesFromCollection(tables);
		}
		
		private void initTablesFromCollection(Collection<TableInfo> tables)
		{
			this.tables = new ArrayList<TableInfo>();

			for (TableInfo table : tables)
				this.tables.add(table);
		}
		


		@Override
		public int getRowCount() 
		{
			// TODO Auto-generated method stub
			return tables.size();
		}

		@Override
		public int getColumnCount() 
		{
			return 5;
		}


		@Override
		public Object getValueAt(int rowIndex, int columnIndex) 
		{
			TableInfo table = tables.get(rowIndex);


			switch(columnIndex)
			{
			case TABLE_NUM_COL:
				return table.getTableNumber();
			case NAME_COL:
				return table.getTableName();
			case ZONE_COL:
				return table.getZone().name();
			case CAP_COL:
				return table.maxOcc;
			case STATUS_COL:
				return table.isTableOccupied() ? OCCUPIED : UNOCCUPIED;
			}
			return null;
		}
		
		@Override
		public void setValueAt(Object value, int rowIndex, int columnIndex)
		{
			TableInfo table = tables.get(rowIndex);
			switch (columnIndex)
			{
				case STATUS_COL:
					if (value.toString().equals(OCCUPIED))
						table.setTableToOccupied();
					else
						table.setTableToEmpty();
				break;
				default:
					break;
			}
			fireTableCellUpdated(rowIndex, columnIndex);
		}
		
		@Override
		public String getColumnName(int columnIndex)
		{
			switch(columnIndex)
			{
			case TABLE_NUM_COL:
				return "Table number";
			case NAME_COL:
				return "Name";
			case ZONE_COL:
				return "Zone";
			case CAP_COL:
				return "Capacity";
			case STATUS_COL:
				return "Status";
			}
			return null;
		}
		
		
		
	
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
	private void setUpPanel() 
	{

		tableInfo = new javax.swing.JPanel();
		tabelListPanel = new java.awt.Panel();
		tabelListPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				restaurantTables.clearSelection();
			}
		});
		tableControlPanel = new java.awt.Panel();
		tableListScrollPane = new javax.swing.JScrollPane();
		setOccupiiedJBut = new javax.swing.JButton();
		setUnoccupiedJBut = new javax.swing.JButton();
		waitListLabel = new java.awt.Label();
		waitListPanel = new java.awt.Panel();
		waitListPanel.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				waitList.clearSelection();
			}
		});
		waitListControlPanel = new java.awt.Panel();
		nameTextField = new javax.swing.JTextField();
		partySizeSpinner = new javax.swing.JSpinner();
		paryNameLabel = new javax.swing.JLabel();
		partySizeLabel = new javax.swing.JLabel();
		assignTableBut = new javax.swing.JButton();
		addBut = new javax.swing.JButton();
		remBut = new javax.swing.JButton();
		waitListScrollPane = new javax.swing.JScrollPane();
		waitList = new javax.swing.JTable();
		tableInfo.setForeground(java.awt.SystemColor.windowBorder);

		javax.swing.GroupLayout tableControlPanelLayout = new javax.swing.GroupLayout(tableControlPanel);
		tableControlPanel.setLayout(tableControlPanelLayout);
		tableControlPanelLayout.setHorizontalGroup(
				tableControlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
				.addGap(0, 12, Short.MAX_VALUE)
				);
		tableControlPanelLayout.setVerticalGroup(
				tableControlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 87, Short.MAX_VALUE)
				);

		setOccupiiedJBut.setName(SET_OCCUPIED_BUT);
		setOccupiiedJBut.setText(SET_OCCUPIED_BUT);
		

		javax.swing.GroupLayout tabelListPanelLayout = new javax.swing.GroupLayout(tabelListPanel);
		tabelListPanelLayout.setHorizontalGroup(
				tabelListPanelLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(tabelListPanelLayout.createSequentialGroup()
						.addContainerGap()
						.addGroup(tabelListPanelLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(tabelListPanelLayout.createSequentialGroup()
										.addComponent(tableListScrollPane, GroupLayout.DEFAULT_SIZE, 387, Short.MAX_VALUE)
										.addGap(16))
										.addGroup(tabelListPanelLayout.createSequentialGroup()
												.addComponent(setOccupiiedJBut, GroupLayout.PREFERRED_SIZE, 169, GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(setUnoccupiedJBut, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(tableControlPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
												.addGap(10))
				);
		tabelListPanelLayout.setVerticalGroup(
				tabelListPanelLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(tabelListPanelLayout.createSequentialGroup()
						.addContainerGap()
						.addComponent(tableListScrollPane, GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)
						.addGap(10)
						.addGroup(tabelListPanelLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(tableControlPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addGroup(tabelListPanelLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(setOccupiiedJBut)
										.addComponent(setUnoccupiedJBut)))
										.addGap(195))
				);
		tabelListPanel.setLayout(tabelListPanelLayout);

		restaurantTables = new JTable();
		restaurantTables.setName(RES_TABLES);
		
		tableListScrollPane.setViewportView(restaurantTables);

		javax.swing.GroupLayout tableInfoLayout = new javax.swing.GroupLayout(tableInfo);
		tableInfoLayout.setHorizontalGroup(
				tableInfoLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(tableInfoLayout.createSequentialGroup()
						.addContainerGap()
						.addComponent(tabelListPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(10, Short.MAX_VALUE))
				);
		tableInfoLayout.setVerticalGroup(
				tableInfoLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(tableInfoLayout.createSequentialGroup()
						.addGap(29)
						.addComponent(tabelListPanel, GroupLayout.DEFAULT_SIZE, 660, Short.MAX_VALUE)
						.addGap(18))
				);
		tableInfo.setLayout(tableInfoLayout);

		waitListLabel.setText("Wait List");

		nameTextField.setText("name");
		

		paryNameLabel.setText("Name:");

		partySizeLabel.setText("Party size:");

		assignTableBut.setText("Assign Table");
		addBut.setText("Add");
	

		remBut.setText("Remove");

		javax.swing.GroupLayout waitListControlPanelLayout = new javax.swing.GroupLayout(waitListControlPanel);
		waitListControlPanel.setLayout(waitListControlPanelLayout);
		waitListControlPanelLayout.setHorizontalGroup(
				waitListControlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(waitListControlPanelLayout.createSequentialGroup()
						.addGroup(waitListControlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
								.addGroup(waitListControlPanelLayout.createSequentialGroup()
										.addComponent(paryNameLabel)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(nameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(partySizeLabel)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(partySizeSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGroup(waitListControlPanelLayout.createSequentialGroup()
												.addComponent(assignTableBut, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
												.addComponent(addBut, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
												.addComponent(remBut, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
												.addContainerGap(67, Short.MAX_VALUE))
				);
		waitListControlPanelLayout.setVerticalGroup(
				waitListControlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(waitListControlPanelLayout.createSequentialGroup()
						.addContainerGap()
						.addGroup(waitListControlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(nameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(paryNameLabel)
								.addComponent(partySizeLabel)
								.addComponent(partySizeSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGap(14, 14, 14)
								.addGroup(waitListControlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(assignTableBut)
										.addComponent(addBut)
										.addComponent(remBut))
										.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				);

		

		waitListScrollPane.setViewportView(waitList);

		javax.swing.GroupLayout waitListPanelLayout = new javax.swing.GroupLayout(waitListPanel);
		waitListPanel.setLayout(waitListPanelLayout);
		waitListPanelLayout.setHorizontalGroup(
				waitListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(waitListPanelLayout.createSequentialGroup()
						.addContainerGap()
						.addGroup(waitListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(waitListControlPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addGroup(waitListPanelLayout.createSequentialGroup()
										.addComponent(waitListScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(0, 0, Short.MAX_VALUE))))
				);
		waitListPanelLayout.setVerticalGroup(
				waitListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(waitListPanelLayout.createSequentialGroup()
						.addGap(22, 22, 22)
						.addComponent(waitListScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(waitListControlPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap(31, Short.MAX_VALUE))
				);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addContainerGap()
						.addComponent(tableInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 424, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(layout.createSequentialGroup()
										.addGap(116, 116, 116)
										.addComponent(waitListLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGroup(layout.createSequentialGroup()
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
												.addComponent(waitListPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
												.addContainerGap(355, Short.MAX_VALUE))
				);
		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(tableInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addGroup(layout.createSequentialGroup()
						.addContainerGap()
						.addComponent(waitListLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(waitListPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				);

		waitListLabel.getAccessibleContext().setAccessibleName("WaitList");


	}// </editor-fold>//GEN-END:initComponents

	
	
	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
		/* Set the Nimbus look and feel */
		//<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
		/* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
		 * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
		 */
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(CheckInPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(CheckInPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(CheckInPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(CheckInPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		//</editor-fold>


	}
	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JButton addBut;
	private javax.swing.JButton assignTableBut;
	private javax.swing.JTextField nameTextField;
	private javax.swing.JLabel partySizeLabel;
	private javax.swing.JSpinner partySizeSpinner;
	private javax.swing.JLabel paryNameLabel;
	private javax.swing.JButton remBut;
	private javax.swing.JButton setOccupiiedJBut;
	private javax.swing.JButton setUnoccupiedJBut;
	private java.awt.Panel tabelListPanel;
	private java.awt.Panel tableControlPanel;
	private javax.swing.JPanel tableInfo;
	private javax.swing.JScrollPane tableListScrollPane;
	private JTable waitList;
	private java.awt.Panel waitListControlPanel;
	private java.awt.Label waitListLabel;
	private java.awt.Panel waitListPanel;
	private javax.swing.JScrollPane waitListScrollPane;

	private RestaurantSystem restaurantSytem;
	private JTable restaurantTables;
	
	// public names for unit testing
	public static final String RES_TABLES = "Restaurant tables";
	public static final String SET_OCCUPIED_BUT = "Set occupied";
	public static final String SET_UNOCCUPIED_BUT = "Set unoccupied";
	public static final String WAIT_LIST_TABLE = "Wait List";
	public static final String NAME_TEXT_FIELD = "Name";
	public static final String PARTY_SIZE_SPINNER = "Party size";
	public static final String ADD_BUT = "Add";
	public static final String REM_BUT = "Remove";
	public static final String ASSIGN_TABLE_BUT = "Assign Table";

}
////////