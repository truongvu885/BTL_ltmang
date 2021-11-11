package Bai4;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class server {
	public static void main(String[] args) throws IOException {
System.out.println("\tSERVER START");
		
		ServerSocket sv_socket = new ServerSocket(3000);
		Random rd = new Random();
		int random = rd.nextInt(101);
		System.out.println("Số random: "+random);
		Socket socket = sv_socket.accept();
		
		DataInputStream data_in = new DataInputStream(socket.getInputStream());
		
		DataOutputStream data_out = new DataOutputStream(socket.getOutputStream());
		
		while(true)
		{
				int data = data_in.read();
				String str = " ";
			
				
				
				if(random == data)
				{
					str = str+"Chính xác";
				}
				else if(random<data)
				{
					str = str +"Số vừa nhập lớn hơn số cần tìm";
				}
				else if(random>data)
				{
					str = str+"Số vừa nhập nhỏ hơn số cần tìm";
				}
			
			
			data_out.writeUTF(str);
			data_out.flush();
			if(data == 00)
			{
				break;
			}
		}
		sv_socket.close();
		data_in.close();
		data_out.close();
		
		
	}
}
