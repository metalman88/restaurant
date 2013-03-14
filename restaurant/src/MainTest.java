

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
							rest.getTableStatusFromDatabase(123);
						}
						
					}
					catch (Exception e)
					{
					}
				}

	}

}
