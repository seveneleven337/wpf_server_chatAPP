package socketPildoras;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;

public class Socket_server implements Runnable {

	private static int port = 6000;
	DataInputStream in;
	DataOutputStream out;
	int read = 0;
	byte[] buffer = new byte[1024];
	private ArrayList<Client_information> list = new ArrayList<Client_information>();
	

	public static void main(String[] args) throws IOException {
		
		Runnable runnable = new Socket_server();
		Thread thread = new Thread(runnable);
		thread.start();
		
	}

	@Override
	public void run() {
		
		ServerSocket server = null;
		Socket socket = null;
		DataInputStream in;
		DataOutputStream out;
		String data = "";
		try {
			server = new ServerSocket(port);
			System.out.println("server running");
			
			while (true) {
				socket = server.accept(); // accept connection from outside
				Client_information temp = new Client_information(socket.getInetAddress(), socket.getPort());
				list.add(temp);
				in = new DataInputStream(socket.getInputStream()); // receive an stream from the socket input stream
				while (true) {
					in.read(buffer);
					data += new String(buffer, StandardCharsets.UTF_8);
					if( data.indexOf("<EOF>") > -1 ) break;
				}
				System.out.println(socket.getRemoteSocketAddress().toString());
				System.out.println(new String(buffer, StandardCharsets.UTF_8));
				
				
				broadcastMsg(socket, socket.getInetAddress(), buffer);
				buffer = new byte[1024];
				
				socket.close();
			}

		} catch (EOFException e) {
			System.out.println(e);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	private void broadcastMsg(Socket socket, InetAddress inetAddress, byte[] buffer) {
		try {
			out = new DataOutputStream(socket.getOutputStream());
			out.write(buffer);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
