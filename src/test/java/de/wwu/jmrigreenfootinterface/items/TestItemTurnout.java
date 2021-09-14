package de.wwu.jmrigreenfootinterface.items;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class TestItemTurnout {

	private class TestTurnout extends JMRITurnout {

		public TestTurnout(String name) {
			super(name);
		}
		
	}
	
	@Test
	void testGetState() {
		assertDoesNotThrow(() -> {
			TestTurnout to = new TestTurnout("ITabc123");
			to.getState();
		});		
	}
	
	@Test
	void testSetState() {
		assertDoesNotThrow(() -> {
			TestTurnout to = new TestTurnout("ITabc123");
			to.setState(TurnoutState.CLOSED);
			assertEquals(to.getState(), TurnoutState.CLOSED);
			
			to.setState(TurnoutState.THROWN);
			assertEquals(to.getState(), TurnoutState.THROWN);
		});
	}

}
