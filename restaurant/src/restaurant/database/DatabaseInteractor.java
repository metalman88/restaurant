package restaurant.database;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import restaurant.system.Menu;
import restaurant.system.MenuItem;
import restaurant.system.NutritionInfo;
import restaurant.system.OrderChunk;
import restaurant.system.SingleItemWithNote;
import restaurant.system.TableInfo;
import restuarant.enums.CATEGORYENUMS;
import restuarant.enums.ORDERSTATUSENUMS;
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
		                                  "postgres", "sflhdl");
		    
		    
		    //databaseConnection = DriverManager.getConnection("jdbc:postgresql://localhost/restaurant",
              //     "postgres", "ounhuoead");
		    
		  //  deleteCommand("kitchen", ";");
		 //  deleteCommand("orderInfo", ";");
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
  
  ResultSet deleteCommand(String What, String Where) {
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
	    s.executeUpdate("DELETE FROM "+What+" "+Where+" ;");
	  } catch (SQLException se) {
	    System.out.println("We got an exception while executing our query:" +
	                       "that probably means our SQL is invalid");
	    se.printStackTrace();
	    System.exit(1);
	  }
	  return rs;
	  
	   
  }
  
  public void clearOrder(String orderID) {
	  deleteCommand("kitchen", "order_id="+orderID+";");
	  deleteCommand("orderInfo", "order_id="+orderID+";");
  }
 
  
  Connection databaseConnection = null;

  
public  ArrayList<OrderChunk> getAllUnfinishedOrders() {
	OrderChunk result = new OrderChunk();
	ArrayList<OrderChunk> fResult = new ArrayList<OrderChunk>();
	System.out.println("Unfinishedorder checking on");
	// SELECT * FROM orderInfo, kitchen WHERE kitchen.order_id=orderInfo.order_id AND kitchen.status=0;
	int check = 0;
	ResultSet rs = selectCommand("orderInfo.order_id, kitchen.status, orderInfo.notes, orderInfo.menuitem_id", "orderInfo, kitchen WHERE kitchen.order_id=orderInfo.order_id AND kitchen.status<2" );
	 try {
		    while (rs.next()) {
		       // System.out.println("Here's the result of row " + index++ + ":");
		       // System.out.println(rs.getString(1));
		    	// ZONEENUMS.valueOf(rs.getString(6))
		    	//result.updateOrderStatus();
		    	if(check != 0 && check != rs.getInt(1))
		    	{
		    		fResult.add(result);
		    	
		    		result = new OrderChunk();
		    	}
		    	result.setOrderID(rs.getString(1));
		    	ORDERSTATUSENUMS newStatus = null;
		    	switch(rs.getInt(2))
		    	{
		    	case 0:
		    		newStatus = ORDERSTATUSENUMS.PREPPING;
		    		break;
		    	case 1:
		    		newStatus = ORDERSTATUSENUMS.COOKING;
		    		break;
		    	case 2:
		    		newStatus = ORDERSTATUSENUMS.OUT;
		    		break;
		    	case 3:
		    		newStatus = ORDERSTATUSENUMS.FINISHED;
		    		break;
		    	
		    	
		    	}
		    	
		    	result.updateOrderStatus(newStatus);
		    	System.out.println("Found an unfinished order:"+rs.getString(4));
		    	SingleItemWithNote toTest = new SingleItemWithNote(getMenuItem(rs.getInt(4)), rs.getString(3));
		    	result.addItem(new SingleItemWithNote(getMenuItem(rs.getInt(4)), rs.getString(3)));
	
		    	
		    	check = rs.getInt(1);
		    }
		  } catch (SQLException se) {
		    System.out.println("We got an exception while getting a result:this " +
		                       "shouldn't happen: we've done something really bad.");
		    se.printStackTrace();
		    System.exit(1);
		  }
	 fResult.add(result);
	 System.out.println("Size is"+fResult.size());
	return fResult;
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
    TableInfo toAdd = new TableInfo(rs.getString(2), Integer.parseInt(rs.getString(1)), Integer.parseInt(rs.getString(3)), getEnum(rs.getString(6)), this, menu, isTaken);

    toAdd.setServiceStatus(rs.getBoolean(5));
       result.put(Integer.parseInt(rs.getString(1)), toAdd );
   }
 } catch (SQLException se) {
   System.out.println("We got an exception while getting a result:this " +
                      "shouldn't happen: we've done something really bad.");
   se.printStackTrace();
   System.exit(1);
 }
return result;
}



private ZONEENUMS getEnum(String enumNum) {
    if (enumNum.equals("0")) {
        return ZONEENUMS.GOLD;
    }
    else if (enumNum.equals("1")) {
        return ZONEENUMS.YELLOW;
    }
    else if (enumNum.equals("2")) {
        return ZONEENUMS.BLUE;
    }
    else if (enumNum.equals("3")) {
        return ZONEENUMS.TIGER;
    }

    return null;
}


public ArrayList<TableInfo> getTablesInZone(ZONEENUMS zone) {
	
	int zoneInDB = 0;
	switch(zone) {
	case GOLD:
		zoneInDB = 0;
		break;
	case YELLOW:
		zoneInDB = 1;
		break;
	case BLUE:
		zoneInDB = 2;
		break;
	case TIGER:
		zoneInDB = 3;
		break;
	}
	ResultSet rs = selectCommand("*", "tableInfo WHERE zone="+zoneInDB+";");
	ArrayList<TableInfo> result = new ArrayList<TableInfo>();
	int index = 0;

	  try {
	    while (rs.next()) {
	       // System.out.println("Here's the result of row " + index++ + ":");
	       // System.out.println(rs.getString(1));
	    	// ZONEENUMS.valueOf(rs.getString(6))
	    	boolean isTaken = false;
	    	if(Integer.parseInt(rs.getString(4)) == 1) isTaken = true;
	    	TableInfo toAdd = new TableInfo(rs.getString(2), Integer.parseInt(rs.getString(1)), Integer.parseInt(rs.getString(3)), ZONEENUMS.BLUE, this, null, isTaken);
	        toAdd.setServiceStatus(rs.getBoolean(5));
	    	result.add(toAdd);
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

public void setTableRequestServiceToNone(int i)
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
	                      "status=0 WHERE table_id="+i+";");
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
	
	
	String addID = curOrder.getOrderID();
	if(addID == null) addID = ""+primaryKeyGenerator("kitchen");
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
	  m = s.executeUpdate("INSERT INTO kitchen VALUES" +
	                      "("+addID+", 1, 0, "+curOrder.getTable()+");");
	} catch (SQLException se) {
	  System.out.println("We got an exception while executing our query:" +
	                     "that probably means our SQL is invalid");
	  se.printStackTrace();
	  System.exit(1);
	}

	System.out.println("Successfully modified " + m + " rows.\n");
	
	
	for(SingleItemWithNote toAdd : curOrder.getItems())
	{
		s = null;
		try {
		  s = databaseConnection.createStatement();
		} catch (SQLException se) {
		  System.out.println("We got an exception while creating a statement:" +
		                     "that probably means we're no longer connected.");
		  se.printStackTrace();
		  System.exit(1);
		}

		m = 0;

		try {
		  m = s.executeUpdate("INSERT INTO orderInfo VALUES" +
		                      "("+addID+", '"+toAdd.getNote()+"', "+toAdd.getID()+");");
		  System.out.println("ID:"+toAdd.getID());
		} catch (SQLException se) {
		  System.out.println("We got an exception while executing our query:" +
		                     "that probably means our SQL is invalid");
		  se.printStackTrace();
		  System.exit(1);
		}

		System.out.println("Successfully modified " + m + " rows.\n");
	}
	
}

public void updateOrderStatus(String orderId, ORDERSTATUSENUMS orderstatus) {
	int status = 0;
	switch(orderstatus)
	{
	case PREPPING:
		status = 0;
		break;
	case OUT:
		status = 2;
		break;
	case COOKING:
		status = 1;
		break;
	case FINISHED:
		status = 3;
		break;
	}
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
	  m = s.executeUpdate("UPDATE kitchen SET " +
	                      "status="+status+" WHERE order_id="+orderId+";");
	} catch (SQLException se) {
	  System.out.println("We got an exception while executing our query:" +
	                     "that probably means our SQL is invalid");
	  se.printStackTrace();
	  System.exit(1);
	}

	System.out.println("Successfully modified " + m + " rows.\n");
}

public Menu fakegetMenu()
{

	
	// if you create get all the information and store it in variables i can create the objects.
	// whatever you want to do, i know database interaction stuff too. so no worries
	
	//menu needs many menuItems, and each menuItem needs nutrition info.
	
	//lets create a menu and not tell max, make sure not to commit this code
	// Max here. I'm just going to borrow this code to make menu items in my database.
	HashMap<Integer,MenuItem> menuList = new HashMap<Integer,MenuItem>();
	Random random = new Random();
	
	for(int i = 0; i < 1000; i++)
	{
		NutritionInfo nutrition = new NutritionInfo(i+"","500505","-2","5505055","-3","2112","-4");
		CATEGORYENUMS category = CATEGORYENUMS.DRINK;
		if(i%10==0){category= CATEGORYENUMS.APPETIZER;}
		else if(i%9==0){category = CATEGORYENUMS.DESSERT;}
		else if(i%7==0){category = CATEGORYENUMS.ENTREE;}
		else if(i%12==0){category = CATEGORYENUMS.OTHER;}
		
		MenuItem menuItem = new MenuItem(i,"item"+i,category,"description"+i,new Double(random.nextInt(15)),new Double(random.nextInt(15)),nutrition);
		menuList.put(i, menuItem);
	}
	Menu menu = new Menu(menuList);
	return menu;
}

public Menu getMenuFromDB()
{
	HashMap<Integer,MenuItem> menuList = new HashMap<Integer,MenuItem>();
	//Time to put the real menu in so that we have some more database interaction
	System.out.println("Searching for data;");
	ResultSet rs = selectCommand("*", "menuItem");
	try {
	    while (rs.next()) {
	       // System.out.println("Here's the result of row " + index++ + ":");
	       // System.out.println(rs.getString(1));
	    	// ZONEENUMS.valueOf(rs.getString(6))
	    	CATEGORYENUMS menuItemType = CATEGORYENUMS.APPETIZER;
	    	switch(rs.getInt(6)) {
	    	case 4:
	    		menuItemType = CATEGORYENUMS.APPETIZER;
	    		break;
	    	case 1:
	    		menuItemType = CATEGORYENUMS.ENTREE;
	    		break;
	    	case 2:
	    		menuItemType = CATEGORYENUMS.DESSERT;
	    		break;
	    	case 5:
	    		menuItemType = CATEGORYENUMS.OTHER;
	    		break;
	    	case 3:
	    		menuItemType = CATEGORYENUMS.DRINK;
	    	}
	    	MenuItem menuItem = new MenuItem(rs.getInt(1), rs.getString(2), menuItemType, rs.getString(3), rs.getDouble(4), rs.getDouble(5), getNutrition(rs.getInt(1)));
			menuList.put(rs.getInt(1), menuItem);
	    	System.out.println("Now adding:"+rs.getString(1));
	    }
	  } catch (SQLException se) {
	    System.out.println("We got an exception while getting a result:this " +
	                       "shouldn't happen: we've done something really bad.");
	    se.printStackTrace();
	    System.exit(1);
	  }
	Menu menu = new Menu(menuList);
	return menu;
	
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
while(count.next()) {
countId = count.getInt(1);
}
} catch (SQLException e) {
e.printStackTrace();
}
if (countId == 0) {
return 1;
}
ResultSet max = this.selectCommand("MAX(" + primaryKey + ")", where);
try {
while (max.next()) { 
largestId = max.getInt(1);
}
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
