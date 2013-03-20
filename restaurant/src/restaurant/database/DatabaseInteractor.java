package restaurant.database;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import restaurant.system.Menu;
import restaurant.system.MenuItem;
import restaurant.system.NutritionInfo;
import restaurant.system.OrderChunk;
import restaurant.system.SingleItemWithNote;
import restaurant.system.TableInfo;
import restuarant.enums.CATEGORYENUMS;
import restuarant.enums.ZONEENUMS;


/*
 * 
 * 

These commands based off of examples at http://www.faqs.org/docs/ppbook/x20921.htm

Thanks to them, it was incredibly easy to write this

This is the database layout, run the SQL commands in your offline postgresql database to use them.

CREATE TABLE kitchen (
    order_id    integer CONSTRAINT firstkey1 PRIMARY KEY,
    kitchen_id	integer,
    status	integer,
    table_id	integer
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


INSERT INTO menuITEM VALUES (1, 'Banana', 'It is a banana.', 4, 0), (2, 'Steak', 'Mmmm, meat.', 29, 40), (3, 'Milkshake', 'The sort of thing you eat after a meal.', 3, 10);
INSERT INTO kitchen VALUES (2, 1, 0, 2);
INSERT INTO orderInfo VALUES (2, 'I have notes for this!', 2);
INSERT INTO nutritionInfo VALUES (1, 2, 3, 4, 5, 6, 7, 8),  (2, 12, 31, 14, 51, 16, 17, 18),  (3, 22, 23, 42, 52, 62, 72, 82);
INSERT INTO tableInfo VALUES (2, 'TESTMACHINE', 32, 0, 0, 0);

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

public OrderChunk getAllUnfinishedOrders() {
	OrderChunk result = new OrderChunk();
	// SELECT * FROM orderInfo, kitchen WHERE kitchen.order_id=orderInfo.order_id AND kitchen.status=0;
	ResultSet rs = selectCommand("orderInfo.order_id, kitchen.status, orderInfo.notes, orderInfo.menuitem_id", "orderInfo, kitchen WHERE kitchen.order_id=orderInfo.order_id AND kitchen.status=0" );
	 try {
		    while (rs.next()) {
		       // System.out.println("Here's the result of row " + index++ + ":");
		       // System.out.println(rs.getString(1));
		    	// ZONEENUMS.valueOf(rs.getString(6))
		    	System.out.println("Found an unfinished order:"+rs.getString(4));
		    	SingleItemWithNote toTest = new SingleItemWithNote(getMenuItem(rs.getInt(4)), rs.getString(3));
		    	if(toTest == null) System.out.println("OOPS");
		    	result.addItem(new SingleItemWithNote(getMenuItem(rs.getInt(4)), rs.getString(3)));
		    	
		    }
		  } catch (SQLException se) {
		    System.out.println("We got an exception while getting a result:this " +
		                       "shouldn't happen: we've done something really bad.");
		    se.printStackTrace();
		    System.exit(1);
		  }
	return result;
}

public MenuItem getMenuItem(int menuID) {
	//public MenuItem(int itemID, String itemName, CATEGORYENUMS category, String description,
		//    Double price, Double cookingTimeMinutes,NutritionInfo nutrition)
	System.out.println("Looking for "+menuID);
	MenuItem result = null;
	ResultSet rs = selectCommand("*", "menuItem WHERE menu_id="+menuID+";");
	try {
	    while (rs.next()) {
	       // System.out.println("Here's the result of row " + index++ + ":");
	       // System.out.println(rs.getString(1));
	    	// ZONEENUMS.valueOf(rs.getString(6))
	    	System.out.println("Found this:"+rs.getString(1));
	    	result = new MenuItem(rs.getInt(1), rs.getString(2), CATEGORYENUMS.APPETIZER, rs.getString(3), rs.getDouble(4), rs.getDouble(5), getNutrition(rs.getInt(1)));
	    }
	  } catch (SQLException se) {
	    System.out.println("We got an exception while getting a result:this " +
	                       "shouldn't happen: we've done something really bad.");
	    se.printStackTrace();
	    System.exit(1);
	  }
	return result;
}

public NutritionInfo getNutrition(int menuID) {
	//	public NutritionInfo(String calories, String totalFat, String saturatedFat, String cholesterol,
	//String sodium, String carbohydrates, String protein)
	
	NutritionInfo result = null;
	ResultSet rs = selectCommand("*", "nutritionInfo WHERE nutrition_id="+menuID+";");
	try {
	    while (rs.next()) {
	       // System.out.println("Here's the result of row " + index++ + ":");
	       // System.out.println(rs.getString(1));
	    	// ZONEENUMS.valueOf(rs.getString(6))
	    	result = new NutritionInfo(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8));
	    }
	  } catch (SQLException se) {
	    System.out.println("We got an exception while getting a result:this " +
	                       "shouldn't happen: we've done something really bad.");
	    se.printStackTrace();
	    System.exit(1);
	  }
	return result;
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

public int primaryKeyGenerator(String where)
{
	if (dbPrimaryKeys.containsKey(where)) {
		String primaryKey = dbPrimaryKeys.get(where);
		int largestId = 0;
		int countId = 0;
		
		ResultSet count = this.selectCommand("COUNT(" + primaryKey + ")", where);
		
		try {
			
			countId = count.getInt(1);
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		
		if (countId == 0) {
			return 1;
		}
		
		ResultSet max = this.selectCommand("MAX(" + primaryKey + ")", where);
		try {
			largestId = max.getInt(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Could not select from database");
			e.printStackTrace();
		}
		
		if (largestId != 0) {
			return ++largestId;
		}
		
	}
	
	return -1;
}

static 
{
	Map<String, String> primaryKeys = new HashMap<String,String>();
	
	primaryKeys.put("kitchen", "order_id");
	primaryKeys.put("orderInfo", "order_id");
	primaryKeys.put("menuItem", "menu_id");
	primaryKeys.put("nutritionInfo", "nutrition_id");
	primaryKeys.put("tableInfo", "table_id");
	
	dbPrimaryKeys = Collections.unmodifiableMap(primaryKeys);
}

private static Map<String,String> dbPrimaryKeys;

 private String host;
 private String name;
 private String pass;
}
