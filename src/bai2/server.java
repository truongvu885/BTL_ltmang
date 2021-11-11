package bai2;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
public class server {
	
	public static String Xuly(String data)
	{
		String[] chuoi = data.split("[:,!,#,/]");
		String so1 = chuoi[0];
		String so2 = chuoi[1];
		
		int s1 =Integer.parseInt(so1);
		int s2 = Integer.parseInt(so2);
		if(s1<0&& s2<0)
		{
			return "\nKhông được nhâp số âm\n";
		}
		if(s1<s2)
		{
			return "\nSố thứ nhất không được nhỏ hơn số thứ 2\n";
		}
		int tong = s1+s2;
		int tich = s1*s2;
		double mu = Math.pow(s1, s2);
		long giai_thua = server.tinhGiaithua(s1);
	
		return "Tổng: "+tong+"\nTích: "+tich+"\nMũ: "+mu+"\nGiai thừa: "+giai_thua;
	}
	
	 public static long tinhGiaithua(int n) {
	        long giai_thua = 1;
	        if (n == 0 || n == 1) {
	            return giai_thua;
	        } else {
	            for (int i = 2; i <= n; i++) {
	                giai_thua *= i;
	            }
	            return giai_thua;
	        }
	    }
	public static void main(String[] args) {
		
		System.out.println("\t\tSERVER KHỞI ĐỘNG THÀNH CÔNG");
		
		try {
			DatagramSocket dt_socket = new DatagramSocket(3000);
			
			DatagramSocket dt_socket_send = new DatagramSocket();
			
			while(true)
			{
				byte[] buffer_in = new byte[1024];
				DatagramPacket dt_packet_receive = new DatagramPacket(buffer_in, buffer_in.length);
				dt_socket.receive(dt_packet_receive);
				
				String data = new String(dt_packet_receive.getData(),0,dt_packet_receive.getLength());
				
				if(data.equalsIgnoreCase("exit"))
				{
					System.out.println("-----------------The end------------------");
					break;
				}
				System.out.println("data: "+data);
				String data_send = server.Xuly(data);
				byte [] buffer_out = data_send.getBytes();
				DatagramPacket dt_packet_send = new DatagramPacket(buffer_out, buffer_out.length, dt_packet_receive.getAddress(), dt_packet_receive.getPort());
				
				dt_socket_send.send(dt_packet_send);
			}
			dt_socket.close();
			dt_socket_send.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
