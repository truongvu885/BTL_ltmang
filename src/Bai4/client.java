package Bai4;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class client {
	public static void main(String[] args) throws IOException {
		
		Socket socket = new Socket("127.0.0.1",3000);
		
		DataOutputStream data_out = new DataOutputStream(socket.getOutputStream());
		DataInputStream data_in = new DataInputStream(socket.getInputStream());
		Scanner input = new Scanner(System.in);
		System.out.println("'new' để tạo số mới\n'exit' để thoát");
		while(true)
		{
			System.out.print("Bạn đoán : ");
			String so = input.nextLine();
			
				data_out.writeUTF(so);
				data_out.flush();
				if(so.equalsIgnoreCase("exit"))
				{
					break;
				}
				String str = data_in.readUTF();
				System.out.println("Server trả về: "+str);
		}
		socket.close();
		data_in.close();
		data_out.close();
		input.close();
		
		
	}
}
