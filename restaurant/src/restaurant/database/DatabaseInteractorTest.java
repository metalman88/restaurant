package restaurant.database;
import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map.Entry;

import org.junit.Test;

import restaurant.system.OrderChunk;
import restaurant.system.SingleItemWithNote;
import restaurant.system.TableInfo;


public class DatabaseInteractorTest {
 
	@Test
	public void testDB() {
		DatabaseInteractor toTest = new DatabaseInteractor();
		
		toTest.setServerInfo("localhost/restaurant", "postgres", "ounhuoead");
		toTest.connect();
		
		ArrayList<OrderChunk> result = toTest.getAllUnfinishedOrders();
		/*
		result.getItems();
		
		for(SingleItemWithNote test : result.getItems()) 
		{
			System.out.println(test.getNote());
		}
		*/
		
	}
	
	@Test
	public void test() {
		DatabaseInteractor toTest = new DatabaseInteractor();
		toTest.setServerInfo("localhost/restaurant", "postgres", "ounhuoead");
		toTest.connect();
		ResultSet rs = toTest.selectCommand("*", "tableInfo");
		int index = 0;

		  try {
		    while (rs.next()) {
		        //System.out.println("Here's the result of row1 " + index++ + ":");
		        //System.out.println(rs.getString(1));
		    }
		  } catch (SQLException se) {
		    System.out.println("We got an exception while getting a result:this " +
		                       "shouldn't happen: we've done something really bad.");
		    se.printStackTrace();
		    System.exit(1);
		  }
		  
		  for(Entry<Integer, TableInfo> tables : toTest.getTables(null).entrySet()) {
			  System.out.println("Table #"+tables.getValue().getTableNumber()+", \""+tables.getValue().tablename+"\"");
		  }
	}

}
