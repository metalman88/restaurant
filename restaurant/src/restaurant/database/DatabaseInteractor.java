package restaurant.database;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import restaurant.system.Menu;
import restaurant.system.MenuItem;
import restaurant.system.OrderChunk;
import restaurant.system.SingleItemWithNote;
import restaurant.system.TableInfo;
import restuarant.enums.ZONEENUMS;


/*
 * 
 * 

These commands based off of examples at http://www.faqs.org/docs/ppbook/x20921.htm

Thanks to them, it was incredibly easy to write this

CREATE TABLE kitchen (
    order_id    integer CONSTRAINT firstkey1 PRIMARY KEY,
    kitchen_id	integer,
    status	integer
);

CREATE TABLE orderInfo (
    order_id    integer CONSTRAINT firstkey2 PRIMARY KEY,
    notes	varchar(700),
    menuitem_id	integer
);

CREATE TABLE menuItem (
    menu_id    integer CONSTRAINT firstkey3 PRIMARY KEY,
    name	varchar(250),
    description	varchar(700),
    price	real,
    cookTime    integer
);

CREATE TABLE nutritionInfo (
    nutrition_id    integer CONSTRAINT firstkey4 PRIMARY KEY,
    calories	integer,
    totalFat	integer,
    satFat	integer,
    cholesterol	integer,
    sodium	integer,
    carbs	integer,
    protein	integer
);

CREATE TABLE tableInfo (
    table_id	integer CONSTRAINT firstkey5 PRIMARY KEY,
    name	varchar(250),
    maxOccup	integer,
    tabletTake	integer,
    status	integer,
    zone	integer
);

 */

public class DatabaseInteractor {
	public DatabaseInteractor() {
	
		
	}
  public static void main(String[] argv) {
	  testDB();
  }
  

  
  public boolean connect() {
	  try {
		    // The second and third arguments are the username and password,
		    // respectively. They should be whatever is necessary to connect
		    // to the database.
		    databaseConnection = DriverManager.getConnection("jdbc:postgresql://localhost/restaurant",
		                                    "postgres", "ounhuoead");
		    return true;
		    
		    // My home database: ounhuoead
		    // Laptop database: sflhdl
		  } catch (SQLException se) {
		    System.out.println("Couldn't connect: print out a stack trace and exit.");
		    se.printStackTrace();
		   	return false;
		  }
  }
  
  public void setServerInfo(String host, String name, String pass) {
	this.host = host;
	this.name = name;
	this.pass = pass;
  }
  
  public static void testDB() {
  System.out.println("Checking if Driver is registered with DriverManager.");
  
  try {
    Class.forName("org.postgresql.Driver");
  } catch (ClassNotFoundException cnfe) {
    System.out.println("Couldn't find the driver!");
    System.out.println("Let's print a stack trace, and exit.");
    cnfe.printStackTrace();
    System.exit(1);
  }
  
  System.out.println("Registered the driver ok, so let's make a connection.");
  
  Connection c = null;
  
  try {
    // The second and third arguments are the username and password,
    // respectively. They should be whatever is necessary to connect
    // to the database.
    c = DriverManager.getConnection("jdbc:postgresql://localhost/",
                                    "postgres", "sflhdl");
  } catch (SQLException se) {
    System.out.println("Couldn't connect: print out a stack trace and exit.");
    se.printStackTrace();
    System.exit(1);
  }
  
  if (c != null)
    System.out.println("Hooray! We connected to the database!");
  else
    System.out.println("We should never get here.");
  }
  
  ResultSet selectCommand(String What, String Where) {
	  Statement s = null;
	  try {
	    s = databaseConnection.createStatement();
	  } catch (SQLException se) {
	    System.out.println("We got an exception while creating a statement:" +
	                       "that probably means we're no longer connected.");
	    se.printStackTrace();
	    System.exit(1);
	  }
	  ResultSet rs = null;
	  try {
	    rs = s.executeQuery("SELECT "+What+" FROM "+Where+" ;");
	  } catch (SQLException se) {
	    System.out.println("We got an exception while executing our query:" +
	                       "that probably means our SQL is invalid");
	    se.printStackTrace();
	    System.exit(1);
	  }
	  return rs;
	  
	  
  }
  
  Connection databaseConnection = null;

public HashMap<Integer, Integer> getAllUnfinishedOrders() {
	OrderChunk result = new OrderChunk();
	// SELECT * FROM orderInfo, kitchen WHERE kitchen.order_id=orderInfo.order_id AND kitchen.status=0;
	ResultSet rs = selectCommand("*", "orderInfo, kitchen WHERE kitchen.order_id=orderInfo.order_id AND kitchen.status=0" );
	 try {
		    while (rs.next()) {
		       // System.out.println("Here's the result of row " + index++ + ":");
		       // System.out.println(rs.getString(1));
		    	// ZONEENUMS.valueOf(rs.getString(6))
		        result.addItem(new SingleItemWithNote(new MenuItem(0, host, null, host, null, null, null), host));
		    }
		  } catch (SQLException se) {
		    System.out.println("We got an exception while getting a result:this " +
		                       "shouldn't happen: we've done something really bad.");
		    se.printStackTrace();
		    System.exit(1);
		  }
	return null;
}
  
public HashMap<Integer, TableInfo> getTables(Menu menu) {
	ResultSet rs = selectCommand("*", "tableInfo");
	HashMap<Integer, TableInfo> result = new HashMap<Integer, TableInfo>();
	int index = 0;

	  try {
	    while (rs.next()) {
	       // System.out.println("Here's the result of row " + index++ + ":");
	       // System.out.println(rs.getString(1));
	    	// ZONEENUMS.valueOf(rs.getString(6))
	    	boolean isTaken = false;
	    	if(Integer.parseInt(rs.getString(4)) == 1) isTaken = true;
	        result.put(Integer.parseInt(rs.getString(1)), new TableInfo(rs.getString(2), Integer.parseInt(rs.getString(1)), Integer.parseInt(rs.getString(3)), ZONEENUMS.BLUE, this, menu, isTaken));
	    }
	  } catch (SQLException se) {
	    System.out.println("We got an exception while getting a result:this " +
	                       "shouldn't happen: we've done something really bad.");
	    se.printStackTrace();
	    System.exit(1);
	  }
	return result;
}



 
public void updateTableStatus(int tableNumber, String occupied)
{	
	int newStatus = Integer.parseInt(occupied);
	Statement s = null;
	try {
	  s = databaseConnection.createStatement();
	} catch (SQLException se) {
	  System.out.println("We got an exception while creating a statement:" +
	                     "that probably means we're no longer connected.");
	  se.printStackTrace();
	  System.exit(1);
	}

	int m = 0;

	try {
	  m = s.executeUpdate("UPDATE tableInfo SET " +
	                      "tabletTake="+newStatus+" WHERE table_id="+tableNumber+";");
	} catch (SQLException se) {
	  System.out.println("We got an exception while executing our query:" +
	                     "that probably means our SQL is invalid");
	  se.printStackTrace();
	  System.exit(1);
	}

	System.out.println("Successfully modified " + m + " rows.\n");
}

public void serviceRequested(int tableNumber)
{	
	Statement s = null;
	try {
	  s = databaseConnection.createStatement();
	} catch (SQLException se) {
	  System.out.println("We got an exception while creating a statement:" +
	                     "that probably means we're no longer connected.");
	  se.printStackTrace();
	  System.exit(1);
	}

	int m = 0;

	try {
	  m = s.executeUpdate("UPDATE tableInfo SET " +
	                      "status=1 WHERE table_id="+tableNumber+";");
	} catch (SQLException se) {
	  System.out.println("We got an exception while executing our query:" +
	                     "that probably means our SQL is invalid");
	  se.printStackTrace();
	  System.exit(1);
	}

	System.out.println("Successfully modified " + m + " rows.\n");
}

public void addOrderToDB(OrderChunk curOrder)
{
	
}

public Menu getMenuFromDB()
{
	// if you create get all the information and store it in variables i can create the objects.
	// whatever you want to do, i know database interaction stuff too. so no worries
	
	//menu needs many menuItems, and each menuItem needs nutrition info.
	return null;
}

public HashMap<Integer,Boolean> getTableStatusIfUpdated()
{
	//HashMap<TableNumber,true if occupied>
	//returns a hashmap of true or false (occupied, unoccupied) 
	return null;
}



public boolean loginTablet(int tableNumber,String tableName)
{
	if(tableNumber == -1)
	{
		//query by tableName
		//if the table name exists, and DB.tableInfo.tablettake is false return true
		// then set tablettake to true
		ResultSet rs = selectCommand("tabletTake, table_id", "tableInfo WHERE name='"+tableName+"'");
		String tableId = "0";
		boolean isTaken = true;
		try {
		    while (rs.next()) {
		       // System.out.println("Here's the result of row " + index++ + ":");
		       // System.out.println(rs.getString(1));
		    	// ZONEENUMS.valueOf(rs.getString(6))
		    	isTaken = rs.getBoolean(1);
		    	tableId = rs.getString(2);
		    	System.out.println("The tablet taken status is"+rs.getString(1));
		    }
		  } catch (SQLException se) {
		    System.out.println("We got an exception while getting a result:this " +
		                       "shouldn't happen: we've done something really bad.");
		    se.printStackTrace();
		    System.exit(1);
		  }
		
		if(!isTaken)
		{
			
			updateTableStatus(tableNumber, tableId);
		}
		//query by tableNumber
		//if the table number exists, and DB.tableInfo.tablettake is false, return true
		// then set tablettake to true
		return isTaken;
	}
	else if(tableName.isEmpty())
	{
		ResultSet rs = selectCommand("tabletTake", "tableInfo WHERE table_id="+tableNumber);
		boolean isTaken = true;
		try {
		    while (rs.next()) {
		       // System.out.println("Here's the result of row " + index++ + ":");
		       // System.out.println(rs.getString(1));
		    	// ZONEENUMS.valueOf(rs.getString(6))
		    	isTaken = rs.getBoolean(1);
		    	System.out.println("The tablet taken status is"+rs.getString(1));
		    }
		  } catch (SQLException se) {
		    System.out.println("We got an exception while getting a result:this " +
		                       "shouldn't happen: we've done something really bad.");
		    se.printStackTrace();
		    System.exit(1);
		  }
		
		if(!isTaken)
		{
			
			updateTableStatus(tableNumber, "1");
		}
		//query by tableNumber
		//if the table number exists, and DB.tableInfo.tablettake is false, return true
		// then set tablettake to true
		return isTaken;
	}
	
	return false;
	
}



 private String host;
 private String name;
 private String pass;
}
