package restaurant.GUI.tests;

import java.util.HashMap;

import junit.framework.TestCase;

import org.junit.Test;
import org.uispec4j.Button;
import org.uispec4j.Panel;
import org.uispec4j.assertion.Assertion;

import restaurant.GUI.CheckInPanel;
import restaurant.system.RestaurantSystem;
import restaurant.system.TableInfo;
import restuarant.enums.ZONEENUMS;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Provider;

public class CheckInPanelTest extends TestCase
{



	private Panel checkinPanel;
	protected void setUp() throws Exception
	{

	}

	@Test
	public void test()
	{
		Injector injector = Guice.createInjector(new CheckinPanelModule());

		RestaurantSystem restaurantSystem = 
				injector.getInstance(RestaurantSystem.class);
		restaurantSystem.updateTablesFromDB();	// call this to do the trick
		// of initialiing tablesHash
		assertTrue(restaurantSystem.tableHash.values().size() == 1);

		checkinPanel = new Panel(new CheckInPanel(restaurantSystem));
		assertFalse(checkinPanel == null);
		Button setOccupiedBut = checkinPanel.getButton("Set Occupied But");
		assertFalse(setOccupiedBut == null);
		assertEquals("Set Occupied But", setOccupiedBut.getName());
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

			TableInfo tableInfo = new TableInfo("test table", 1, 12, 
					ZONEENUMS.BLUE, null, null, false);
			tableMap.put(tableInfo.tableNumber,tableInfo);

			return new RestaurantSystem()
			{
				@Override
				public void updateTablesFromDB()
				{
					tableHash = tableMap;
				}

			};
		}
	}

}
