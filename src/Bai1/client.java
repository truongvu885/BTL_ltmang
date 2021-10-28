package Bai1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class client {
	public static void main(String[] args) {
		System.out.println("\t\t...CONNECTING TO SERVER...");
		
		try {
			Socket socket = new Socket("127.0.0.1",9555);
			//tạo các luồng nhâp xuất dữ liệu
			DataOutputStream data_out = new DataOutputStream(socket.getOutputStream());
			DataInputStream data_in  = new DataInputStream(socket.getInputStream());
			
			Scanner input = new Scanner(System.in);
			
			while(true)
			{
				System.out.print("Mời bạn nhập(exit để thoát): ");
				String data = input.nextLine();
				
			
				data_out.writeUTF(data);
				data_out.flush();
				if(data.equalsIgnoreCase("exit"))
				{
					System.out.println("-------The end-------");
					break;
				}
				else
					System.out.println("\t\t...CONNECTED TO SERVER...\t\t");
					
				String nhan = data_in.readUTF();
				System.out.println("Server trả về: "+nhan);
			}
			socket.close();
			data_in.close();
			data_out.close();
			input.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
