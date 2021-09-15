package de.wwu.jmrigreenfootinterface;

import java.io.IOException;
import java.util.Arrays;

import de.wwu.jmrigreenfootinterface.net.WiThrottleClient;

public class StartJMRITest {

	public static void main(String[] args) throws IOException, InterruptedException {
		WiThrottleClient client = new WiThrottleClient("localhost", "12090", "Mein Test Device");
		System.out.println("Connecting to WiThrottleServer...");
		client.connect();
		System.out.println("Connected to WiThrottleServer.\n");
		
		System.out.println("Sending command...");
		client.send("HUtestid");
		System.out.println("Sent.\n");
		
		System.out.println("Receiving...");
		String[] result = client.receive();
		System.out.println(Arrays.toString(result));
		System.out.println("Received.\n");
		
		client.sendHeartBeat();
		
		Thread.sleep(20000);
	}

}
