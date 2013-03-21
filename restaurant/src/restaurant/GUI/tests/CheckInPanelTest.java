package restaurant.GUI.tests;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JSpinner;
import javax.swing.JTable;

import junit.framework.TestCase;

import org.junit.Test;
import org.uispec4j.Button;
import org.uispec4j.Panel;
import org.uispec4j.TextBox;

import restaurant.GUI.CheckInPanel;
import restaurant.database.DatabaseInteractor;
import restaurant.system.Menu;
import restaurant.system.Party;
import restaurant.system.RestaurantSystem;
import restaurant.system.TableInfo;
import restuarant.enums.ZONEENUMS;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Provider;

/**
 * @author tbo
 *
 */
// need to comment out the codes in RestaurantSystem's construcotr for the tests
// to run as those codes attempt connecting to remote database 
public class CheckInPanelTest extends TestCase
{
	
	  
	private Panel checkinPanel;
	private JTable restaurantTables;
	private RestaurantSystem fakeRestaurantSystem;
	private Button unoccupiedBut;
	private Button occupiedBut;
	private JTable waitList;
	private TextBox nameTextBox;
	private Button addBut;
	private Button remBut;
	private Button assignTableBut;
	private JSpinner partySizeSpinner;
	
	protected void setUp() throws Exception
	{
		
		  
		Injector injector = Guice.createInjector(new CheckinPanelModule());

		fakeRestaurantSystem = 
				injector.getInstance(RestaurantSystem.class);

		checkinPanel = new Panel(new CheckInPanel(fakeRestaurantSystem));

		restaurantTables = checkinPanel.getTable(CheckInPanel.RES_TABLES)
				.getJTable();
		waitList = checkinPanel.getTable(CheckInPanel.WAIT_LIST_TABLE).getJTable();
		nameTextBox = checkinPanel.getTextBox(CheckInPanel.NAME_TEXT_FIELD);
		partySizeSpinner = checkinPanel.getSpinner(CheckInPanel.PARTY_SIZE_SPINNER)
				.getAwtComponent();
		
		addBut = checkinPanel.getButton(CheckInPanel.ADD_BUT);
		remBut = checkinPanel.getButton(CheckInPanel.REM_BUT);
		assignTableBut = checkinPanel.getButton(CheckInPanel.ASSIGN_TABLE_BUT);
		occupiedBut = checkinPanel.getButton(CheckInPanel.SET_OCCUPIED_BUT);
		unoccupiedBut = checkinPanel.getButton(CheckInPanel.SET_UNOCCUPIED_BUT);
	}

	@Override
	protected void tearDown() throws Exception
	{
		fakeRestaurantSystem = null;
		checkinPanel = null;
		restaurantTables = null;
		occupiedBut = null;
		unoccupiedBut = null;
		waitList = null;
		addBut = null;
		remBut = null;
		assignTableBut = null;
	}
	
	private void addPartyToWaitList(String name, int partySize)
	{
		nameTextBox.setText(name);
		partySizeSpinner.setValue(partySize);
		addBut.click();
	}
	
	public void testAssignPartyToTable()
	{
		addPartyToWaitList("tai", 20);
		restaurantTables.setRowSelectionInterval(0, 0);
		waitList.setRowSelectionInterval(0, 0);
		assignTableBut.click();
		assertEquals(0, waitList.getModel().getRowCount());
		assertEquals(CheckInPanel.RestaurantTableModel.OCCUPIED,
				restaurantTables.getModel()
				.getValueAt(restaurantTables.getSelectedRow(), 
					CheckInPanel.RestaurantTableModel.STATUS_COL));
	}
	
	public void testRestaurantTables()
	{
		assertFalse(restaurantTables == null);
		assertEquals(CheckInPanel.RES_TABLES, restaurantTables.getName());
		assertTrue(restaurantTables.getBackground() == Color.ORANGE);
		assertEquals(CheckInPanel.RestaurantTableModel.class.getName(),
				restaurantTables.getModel().getClass().getName());
	}

	@Test
	public void testFakeRestaurantSystemHas1Table()
	{
		assertEquals(1, fakeRestaurantSystem.tableHash.size());
	}
	
	public void testRemovePartyFromWaitList()
	{
		nameTextBox.setText("test party name");
		partySizeSpinner.setValue(12);
		addBut.click();
		assertEquals(1, waitList.getModel().getRowCount());
		waitList.setRowSelectionInterval(0, 0);
		remBut.click();
		assertEquals(0, waitList.getModel().getRowCount());
	}
	
	public void testAddParyToWaitList()
	{
		nameTextBox.setText("test party");
		partySizeSpinner.setValue(12);
		addBut.click();
		assertEquals(1 ,fakeRestaurantSystem.getWaitList().size());
	}

	public void testSetOccupiedBut()
	{
		// select the first row
		restaurantTables.setRowSelectionInterval(0, 0);

		// initially status should be Unoccupied
		String status = (String) restaurantTables.getValueAt
				(restaurantTables.getSelectedRow(), 
						CheckInPanel.RestaurantTableModel.STATUS_COL);
		assertEquals(CheckInPanel.RestaurantTableModel.UNOCCUPIED, status);
		// click occupied 
		occupiedBut.click();
		// ensure status is updated
		status = (String) restaurantTables.getValueAt
				(restaurantTables.getSelectedRow(), 
						CheckInPanel.RestaurantTableModel.STATUS_COL);
		assertEquals(CheckInPanel.RestaurantTableModel.OCCUPIED, status);
	}

	public void testSetUnOccupiedBut()
	{		
		// select the first row
		restaurantTables.setRowSelectionInterval(0, 0);
		// simulate clicking occupied but
		occupiedBut.click();
		String status;
		// ensure status is updated
		status = (String) restaurantTables.getValueAt
				(restaurantTables.getSelectedRow(), 
						CheckInPanel.RestaurantTableModel.STATUS_COL);
		assertEquals(CheckInPanel.RestaurantTableModel.OCCUPIED, status);

		// simulate clicking unoccupied but
		unoccupiedBut.click();
		status = (String) restaurantTables.getValueAt
				(restaurantTables.getSelectedRow(), 
						CheckInPanel.RestaurantTableModel.STATUS_COL);
		assertEquals(CheckInPanel.RestaurantTableModel.UNOCCUPIED, status);

	}




	public void testTableModel()
	{
		assertFalse(restaurantTables == null);
		assertEquals(1, restaurantTables.getModel().getRowCount());

		assertFalse(checkinPanel == null);

	}


	private class CheckinPanelModule extends AbstractModule
	{

		@Override
		protected void configure() 
		{
			bind(RestaurantSystem.class)
			.toProvider(RestaurantSystemProvider.class);

		}
	}

	private static class RestaurantSystemProvider implements Provider<RestaurantSystem>
	{	

		private class MockRestaurantSystem extends RestaurantSystem
		{
			public MockRestaurantSystem(HashMap<Integer, TableInfo> tablesMap)
			{
				tableHash = tablesMap;
				waitList = new ArrayList<Party>();
			}

		}

		private class MockTableInfo extends TableInfo
		{

			public MockTableInfo(String tableName, int tableNumber, int maxOcc,
					ZONEENUMS zone, DatabaseInteractor DBInteractor, Menu menu,
					boolean isTaken) 
			{
				super(tableName, tableNumber, maxOcc, zone, DBInteractor, menu, isTaken);
				// TODO Auto-generated constructor stub
			}

			@Override
			public void setTableToOccupied()
			{
				try
				{
					super.setTableToOccupied();
				}
				catch (Exception e)
				{
					System.out.println("ignoring exceptions while testing");
					e.printStackTrace();
				}
			}


			@Override
			public void setTableToEmpty()
			{
				try
				{
					super.setTableToEmpty();
				}
				catch (Exception e)
				{
					System.out.println("ignoring exceptions while testing \n");
					e.printStackTrace();
				}
			}

		}

		@Override
		public RestaurantSystem get() 
		{
			// TODO Auto-generated method stub
			return getFackeRestaurantSystem();
		}

		public RestaurantSystem getFackeRestaurantSystem()
		{
			final HashMap<Integer, TableInfo> tableMap = 
					new HashMap<Integer, TableInfo>();

			TableInfo tableInfo = new MockTableInfo("test table", 1, 12, 
					ZONEENUMS.BLUE, null, null, false);
			tableMap.put(tableInfo.tableNumber,tableInfo);

			return new MockRestaurantSystem(tableMap);

		};
	}
}



