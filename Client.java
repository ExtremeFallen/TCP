package socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {

	public static void main(String[] args) throws IOException {
	/*
	 * 客户端
	 */
		Socket socket = new Socket("127.0.0.1",5446);  //创建客户端连接,指定要连接的目的ip和端口号
		
		InputStream is = socket.getInputStream();   //获得输入流
		OutputStream os = socket.getOutputStream();  //获得输出流
		
		/*
		 * 进行包装,方便字符串读写
		 */
		BufferedReader reader = new BufferedReader(new InputStreamReader(is)); 
		PrintStream ps = new PrintStream(os);
		
		//-----------------------------------------------------------------
		Scanner scanner = new Scanner(System.in);
		String string = scanner.nextLine();
		ps.println(string);  //向服务器发送字符串
		
		String result = reader.readLine();  //接受服务器反转后的字符串
		System.out.println(result);
		
		socket.close();  //关闭socket,相应的流也会关闭
	}

}
