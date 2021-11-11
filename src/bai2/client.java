package bai2;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import java.util.Scanner;

public class client {
	public static void main(String[] args) throws IOException {
		System.out.println("\t\tCLIENT START");
		
		DatagramSocket dt_socket = new DatagramSocket();
		
		Scanner input = new Scanner(System.in);
		while(true)
		{
			System.out.println("=========================");
			System.out.print("Nhập a và b(vd 1:2): ");
			
			String data = input.nextLine();
			
			byte[] buffer_out = data.getBytes();
			InetAddress ip = InetAddress.getByName("localhost");
			DatagramPacket dt_packet_send = new DatagramPacket(buffer_out, buffer_out.length, ip, 3000);
			dt_socket.send(dt_packet_send);
			
			if(data.equalsIgnoreCase("exit"))
			{
				System.out.println("=============The end=============");
				break;
			}
			
			byte[] buffer_in = new byte[1024];
			DatagramPacket dt_packet_recevice = new DatagramPacket(buffer_in, buffer_in.length);
			dt_socket.receive(dt_packet_recevice);
			String data_receive = new String(dt_packet_recevice.getData(),0,dt_packet_recevice.getLength());
			
			System.out.println("Server trả về: \n"+data_receive);
		}
		dt_socket.close();
		input.close();
	}
}
