package javad.utils.update;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class OTAServer {

	private final int port;
	private ServerSocket server;
	private Socket client;
	private Scanner sc;
	private PrintWriter pw;
	private PrintWriter pwOut;
	
	public OTAServer(int port, File thisFile) {
		this.port = port;
		
		try {
			server = new ServerSocket(port);
			client = server.accept();
			sc = new Scanner(client.getInputStream());
			pw = new PrintWriter(client.getOutputStream());
			client.setKeepAlive(true);
			pwOut = new PrintWriter(thisFile);
			Timer t = new Timer();
			t.schedule(new TimerTask() {
				
				@Override
				public void run() {
					if(sc.hasNextByte()) {
						byte b = sc.nextByte();
						pwOut.write(b);
					}
				}
			}, 1, 1);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	

	public void update(boolean restart) {
		
	}
	
	public int getPort() {
		return port;
	}
	
}
