package de.wwu.jmrigreenfootinterface.net;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class WebSocketClient {

	private String host, port;

	public WebSocketClient(String host, String port) {
		this.host = host;
		this.port = port;
	}

	private HttpURLConnection connect(String endpoint) throws IOException {
		URL url = new URL("http://" + host + ":" + port + "/json/" + endpoint);
		return (HttpURLConnection) url.openConnection();
	}

	public String doRequest(String requestMethod, String endpoint, String message) throws IOException {
		// connect to JSON protocol endpoint
		HttpURLConnection conn = connect(endpoint);
				
		conn.setRequestProperty("Content-Type", "application/json;charset=utf-8");
		conn.setRequestMethod(requestMethod);
		conn.setDoOutput(true);
		
		// if message is not empty, send message as request body
		if(!message.isEmpty()) {
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
			bw.write(message);
			bw.flush();
		}
		
		// receive response
		int code = conn.getResponseCode();
		// if HTTP status code is not 200, return with error
		if(code != 200) {
			System.err.println("Response code: " + code);
			return null;
		}
		
		// else (HTTP status code is 200), read response line by line and return as String
		Scanner s = new Scanner(new BufferedReader(new InputStreamReader(conn.getInputStream())));
		StringBuilder builder = new StringBuilder();
		while(s.hasNextLine()) {
			builder.append(s.nextLine() + "\n");
		}
		s.close();
		return builder.toString().trim();
	}

}
