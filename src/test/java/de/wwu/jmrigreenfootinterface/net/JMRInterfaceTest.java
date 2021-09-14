package de.wwu.jmrigreenfootinterface.net;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Random;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import de.wwu.jmrigreenfootinterface.JMRI;

class JMRInterfaceTest {
	
	@Test
	void testListing() {
		assertDoesNotThrow(() -> {
			JSONArray result = JMRI.getInterface().listTypes();
			assertFalse(result.isEmpty());
		});
	}
	
	@Test
	void testGetter() {
		assertDoesNotThrow(() -> {
			assertNotNull(JMRI.getInterface().getType("turnout"));
			assertNotNull(JMRI.getInterface().getItem("turnout", "ITabc123"));
			assertNotNull(JMRI.getInterface().getProperty("turnout", "ITabc123", "feedbackModes"));
		});		
	}
	
	@Test
	void testSetter() {
		assertDoesNotThrow(() -> {
			boolean invertedBefore = (boolean) JMRI.getInterface().getProperty("turnout", "ITabc123", "inverted");
			Object commentBeforeRaw = JMRI.getInterface().getProperty("turnout", "ITabc123", "comment");
			String commentBefore = "";
			if(commentBeforeRaw != JSONObject.NULL) {
				commentBefore = ((JSONObject) commentBeforeRaw).toString();
			}
			
			boolean testBoolean = invertedBefore ? false : true;
			String testString = "Random test string no " + String.valueOf(new Random().nextInt(9999)); 
			
			JMRI.getInterface().setProperty("turnout", "ITabc123", "inverted", testBoolean);
			JMRI.getInterface().setProperty("turnout", "ITabc123", "comment", testString);
			
			assertEquals(JMRI.getInterface().getProperty("turnout", "ITabc123", "inverted"), testBoolean);
			assertEquals(JMRI.getInterface().getProperty("turnout", "ITabc123", "comment"), testString);
			
			// Reset changes
			JMRI.getInterface().setProperty("turnout", "ITabc123", "inverted", invertedBefore);
			JMRI.getInterface().setProperty("turnout", "ITabc123", "comment", commentBefore);
		});
	}

}
