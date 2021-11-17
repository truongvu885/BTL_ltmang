package Bai4;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class server {
	public static Boolean isNumber(String str) {
		try {
			Integer.parseInt(str);
			return true;
		}catch(NumberFormatException e)
		{
			return false;
		}
	}
	public static void main(String[] args) throws IOException {
		System.out.println("\tSERVER START");
		
		ServerSocket sv_socket = new ServerSocket(3000);
		
		Socket socket = sv_socket.accept();
		
		DataInputStream data_in = new DataInputStream(socket.getInputStream());
		
		DataOutputStream data_out = new DataOutputStream(socket.getOutputStream());
		int random =-1 ;
		while(true)
		{
				String str = " ";
				String chuoi = data_in.readUTF();
				if(chuoi.equalsIgnoreCase("new"))
				{
					Random rd = new Random();
					random = rd.nextInt(10001);
					System.out.println("Số random: "+random);
					str = str +"Tạo 1 số mới thành công";
				}
				else if(random==-1)
				{
					str = str+"Bạn cần nhập new để tạo số mới";
				}
				else if(isNumber(chuoi))
				{
					int data = Integer.parseInt(chuoi);
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
				}
				else  {
					str = str+chuoi;
				}
			
			data_out.writeUTF(str);
			data_out.flush();
			if(chuoi.equalsIgnoreCase("exit"))
			{
				break;
			}
		}
		sv_socket.close();
		data_in.close();
		data_out.close();
		
		
	}
}
