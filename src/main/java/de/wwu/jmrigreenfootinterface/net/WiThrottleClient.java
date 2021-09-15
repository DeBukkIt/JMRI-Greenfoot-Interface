package de.wwu.jmrigreenfootinterface.net;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class WiThrottleClient {

	private String host, port;
	private static final int CONNECT_TIMEOUT_MS = 5000;
	
	private String clientName;
	private UUID clientUuid;
			
	private boolean heartActive;
	private int heartBeatRate;
	
	private Socket socket;
	private InputStream inputStream;
	private OutputStream outputStream;

	public WiThrottleClient(String host, String port, String clientName) {
		this.host = host;
		this.port = port;
		this.clientName = clientName;
		this.clientUuid = UUID.randomUUID();
	}

	public void connect() throws IOException {		
		// connect to TCP-Server @ host:port
		socket = new Socket();
		socket.connect(new InetSocketAddress(host, Integer.parseInt(port)), CONNECT_TIMEOUT_MS);
		socket.setKeepAlive(true);
		
		// get and store input and output streams
		outputStream = socket.getOutputStream();
		inputStream = socket.getInputStream();
		
		// send this client's name
		send("N" + clientName);
		String[] welcomeMsgLines = receive();
		// start heart for sending heartbeats at demanded rate
		startHeart(welcomeMsgLines);
		
		// send this client's unique ID
		send("HU" + clientUuid);
		receive();
	}
	
	public String[] receive() throws IOException {
		maintainConnection();
		
		// allocate space to receive lines from server
		List<String> result = new ArrayList<>();
		
		// read lines and store them in the list
		BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
		while(br.ready()) {
			// only add empty lines
			String line = br.readLine();
			if(!line.isEmpty()) {
				result.add(line);
			}
		}
		// return list as array of Strings
		return result.toArray(new String[result.size()]);
	}
	
	public void send(String s) throws IOException {
		maintainConnection();
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(outputStream));
		bw.write(s);
		bw.newLine();
		bw.flush();
	}
	
	public void startHeart(String[] welcomeMsgLines) {
		for(int i = welcomeMsgLines.length - 1; i >= 0; i--) {
			String line = welcomeMsgLines[i];
			if(line.startsWith("*")) {
				this.heartBeatRate = Integer.parseInt(line.substring(1));
				break;
			}
		}
		startHeart(heartBeatRate - 1);
	}
	
	public void startHeart(int interval) {
		heartActive = true;
		
		new Thread(() -> {
			while(heartActive) {
				try {
					// send heartbeat
					send("*");
					// sleep
					Thread.sleep(interval);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}
	
	public void stopHeart() {
		heartActive = false;
	}
	
	public void sendHeartBeat() throws IOException {
		send("*");
	}

	private void maintainConnection() throws IOException {
		if(!socket.isConnected() || socket.isClosed()) {
			connect();
		}
	}
	
}
