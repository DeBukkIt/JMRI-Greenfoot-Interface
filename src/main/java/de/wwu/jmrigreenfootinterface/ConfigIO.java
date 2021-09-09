package de.wwu.jmrigreenfootinterface;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Scanner;

import org.json.JSONObject;

public class ConfigIO {
	
	// ---- Singleton ----
	
	public static ConfigIO instance;
	
	private ConfigIO() {
		readConfigFile();
	}

	public static ConfigIO getInstance() {
		if(instance == null) {
			instance = new ConfigIO();
		}
		return instance;
	}
	
	// ---- ---- ---- ----
	
	private JSONObject configJsonObject;
	
	private void readConfigFile() {
		Scanner s = new Scanner(new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("/config.json"))));
		StringBuilder builder = new StringBuilder();
		while(s.hasNextLine()) {
			builder.append(s.nextLine() + "\n");
		}
		configJsonObject = new JSONObject(builder.toString().trim());
		s.close();
	}
	
	public void writeConfigFile() {
		try {
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.getClass().getResource("/config.json").getPath())));
			writer.write(configJsonObject.toString());
			writer.flush();
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// ---- ---- ---- ----
	
	public Object get(String key) {
		return configJsonObject.get(key);
	}
	
	public void set(String key, Object obj) {
		configJsonObject.put(key, obj);
	}

}
