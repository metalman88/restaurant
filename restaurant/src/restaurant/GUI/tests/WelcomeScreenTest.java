package restaurant.GUI.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import restaurant.GUI.WelcomeScreen;

public class WelcomeScreenTest {

	@Test
	public void testFullScreen() {
		WelcomeScreen WS = new WelcomeScreen();
		WS.setVisible(true);
		WS.swapLoginCustomerPanel();
		WS.switchToFullScreen();
		
	}

}
