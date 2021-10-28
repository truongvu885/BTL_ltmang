package Bai1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class server {
	public static void main(String[] args) {
		System.out.println("\t\t...WAITING CONNECTION...");
		try {
			ServerSocket sv_socket = new ServerSocket(9555);
			Socket socket = sv_socket.accept();
			
			DataInputStream data_in = new DataInputStream(socket.getInputStream());
			DataOutputStream data_out = new DataOutputStream(socket.getOutputStream());
			
		
			int dem =0;
			while(true)
			{
			String data = data_in.readUTF();
			if(data.length()!=0)
			{
				System.out.println("\t\t...CLIENT CONNECTED..."+dem++);
			}
			if(data.equalsIgnoreCase("exit"))
			{
				System.out.println("----------the end----------");
				break;
			}
		
			switch (data) {
			case "NAME":
			case "name":{
				InetAddress ip_sv = socket.getInetAddress();
				String data1 = "\nIP server: "+ip_sv.getHostAddress();
				data_out.writeUTF(data1);
				data_out.flush();
				break;
			}
			case "PORT":
			case "port":{
				int port_sv = sv_socket.getLocalPort();
				String data2 = "\nPort server: "+port_sv;
				data_out.writeUTF(data2);
				data_out.flush();
				break;
			}
			case "ADDR":
			case "addr":{
				InetAddress ip_sv = socket.getInetAddress();
				int port_sv = sv_socket.getLocalPort();
				String data3 = "IP server: "+ip_sv.getHostAddress()+"\nPort Server: "+port_sv;
				data_out.writeUTF(data3);
				data_out.flush();
				break;
			}
			default:
				data_out.writeUTF("Data: "+data);
				data_out.flush();
			}
			
			}
			sv_socket.close();
			socket.close();
			data_in.close();
			data_out.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
