

import java.util.ArrayList;
import java.util.Scanner;


public class MainTest {

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DatabaseInteractor data = new DatabaseInteractor();
		RestaurantSystem rest = new RestaurantSystem();
				while (true)
				{
					try
					{ 
						Scanner consoleIn = new Scanner(System.in);
						if(consoleIn.hasNext()) 
						{
							String use = consoleIn.next();
							if(use.equals("Read"))
							rest.getTableStatusFromDatabase(123);
							if(use.equals("Occupy"))
							rest.DBInteractor.updateTableStatus(123, "1");
							if(use.equals("Unoccupy"))
							rest.DBInteractor.updateTableStatus(123, "0");
						}
						
					}
					catch (Exception e)
					{
					}
				}

	}

}
