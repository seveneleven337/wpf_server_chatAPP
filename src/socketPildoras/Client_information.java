package socketPildoras;

import java.net.InetAddress;

public class Client_information {

	private InetAddress ip;
	private int port;
	
	public Client_information(InetAddress ip, int port) {
		super();
		this.ip = ip;
		this.port = port;
	}

	public InetAddress getIp() {
		return ip;
	}

	public void setIp(InetAddress ip) {
		this.ip = ip;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}
	
	
	
}
