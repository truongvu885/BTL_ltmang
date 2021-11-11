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
		while(true)
		{
			System.out.print("Nhập 1 số: ");
			int so = input.nextInt();
			
			if(so == 00)
			{
				break;
			}
			
				data_out.write(so);
				data_out.flush();
				
				String str = data_in.readUTF();
				System.out.println("Server trả về: "+str);
			
		}
		socket.close();
		data_in.close();
		data_out.close();
		input.close();
		
		
	}
}
