import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map.Entry;

import org.junit.Test;


public class DatabaseInteractorTest {

	@Test
	public void test() {
		DatabaseInteractor toTest = new DatabaseInteractor();
		ResultSet rs = toTest.selectCommand("*", "tableInfo");
		int index = 0;

		  try {
		    while (rs.next()) {
		        System.out.println("Here's the result of row " + index++ + ":");
		        System.out.println(rs.getString(1));
		    }
		  } catch (SQLException se) {
		    System.out.println("We got an exception while getting a result:this " +
		                       "shouldn't happen: we've done something really bad.");
		    se.printStackTrace();
		    System.exit(1);
		  }
		  
		  for(Entry<Integer, TableInfo> tables : toTest.getTables().entrySet()) {
			  System.out.println(tables.getKey());
		  }
		fail("Not yet implemented");
	}

}
