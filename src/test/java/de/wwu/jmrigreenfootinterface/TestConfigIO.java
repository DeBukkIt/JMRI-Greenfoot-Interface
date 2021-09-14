package de.wwu.jmrigreenfootinterface;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.json.JSONObject;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

class TestConfigIO {

	@Test
	void testReadConfig() {
		assertDoesNotThrow(() -> {
			Object result = ConfigIO.getInstance().get("network");
			
			assertNotNull(result);
			
			@SuppressWarnings("unused")
			JSONObject json = (JSONObject) result;
		});
	}
	
	@Test
	void testWriteConfig() {
		ConfigIO.getInstance().set("test", "test phrase");
		ConfigIO.getInstance().writeConfigFile();
		assertEquals(ConfigIO.getInstance().get("test"), "test phrase");
	}
	
	@AfterAll
	void cleanup() {
		ConfigIO.getInstance().remove("test");
		ConfigIO.getInstance().writeConfigFile();
	}

}
