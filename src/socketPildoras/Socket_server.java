package socketPildoras;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class Socket_server implements Runnable {
	
	private int port = 6000;
	DataInputStream in;
	int read = 0;
	byte[] buffer = new byte[1024];
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Runnable runnable = new Socket_server();
		Thread thread = new Thread(runnable);
		thread.start();
	}

	@Override
	public void run() {
		
		try {
			
			System.out.println("server running");
			ServerSocket server = new ServerSocket(port);   //server socket listening in the specified port
			Socket socket = server.accept();				//accept connection from outside			
			in = new DataInputStream(socket.getInputStream());    //receive an stream from the socket input stream
			while ((read = in.read(buffer, 0, buffer.length)) != -1) {
		        in.read(buffer);
		    }
			System.out.println(new String(buffer, StandardCharsets.UTF_8));
			socket.close();
			System.out.println("The server is shut down!");
		}	
		
		catch (EOFException e) {
			System.out.println(e);
				
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
