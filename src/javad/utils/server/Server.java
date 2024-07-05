package javad.utils.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server implements Runnable{

	private ArrayList<ServerMessageListener> listeners = new ArrayList<>();
	private ServerSocket server;
	private Socket client;
	
	public Server(int port) {
		try {
			server  = new ServerSocket(port);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean registerServerMessageListener(ServerMessageListener listener) {
		if(listener == null)return false;
		return listeners.add(listener);
	}
	
	public boolean UnregisterServerMessageListener(ServerMessageListener listener) {
		return listeners.remove(listener);
	}

	@Override
	public void run() {
		
		while(true) {
			
			while(true) {
				
			}
			
		}
		
	}
	
	
	public void accept() {
		try {
			server.accept();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
