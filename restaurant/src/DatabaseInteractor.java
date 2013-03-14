import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;


/*
 * 
 * 

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
	
		try {
		    // The second and third arguments are the username and password,
		    // respectively. They should be whatever is necessary to connect
		    // to the database.
		    databaseConnection = DriverManager.getConnection("jdbc:postgresql://localhost/",
		                                    "postgres", "sflhdl");
		  } catch (SQLException se) {
		    System.out.println("Couldn't connect: print out a stack trace and exit.");
		    se.printStackTrace();
		    System.exit(1);
		  }
	}
  public static void main(String[] argv) {
	  testDB();
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
  
  Connection databaseConnection = null;
  
}