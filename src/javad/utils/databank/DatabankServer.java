package javad.utils.databank;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class DatabankServer {

	private int port = 0;
	private ServerSocket server;
	private ArrayList<Dataset> datasets = null;
	private String tmp;
	
	
	public DatabankServer(int port) {
		this.port = port;
		try {
			server = new ServerSocket(port);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		onMessage();
	}
	
	public void addRow(String name) {
		
	}
	
	private void onMessage() {
		Timer t = new Timer();
		t.schedule(new TimerTask() {
			
			@Override
			public void run() {
				
				try {
					System.out.println("Wait");
					Socket s = server.accept();
					System.out.println("Connect");
					OutputStream out = s.getOutputStream();
					InputStream in = s.getInputStream();
					
					Scanner sc = new Scanner(in, Charset.defaultCharset());
					
					String ins = null;
					
					while((ins = sc.next()) != null) {
						System.out.println("Read");
						System.out.println(ins);
						out.write(ins.getBytes());
						
					}
					
					sc.close();
					server.close();
					
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
		}, 1, 1);
		
		
	}
	
	private String get(byte[] b) {
		if(new String(b).equals("\n")) {
			
		}
		tmp += new String(b);
		
		
		return null;
	}
	
}
