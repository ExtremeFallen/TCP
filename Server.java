package socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main(String[] args) throws IOException {
		/*
		 * 服务端(多线程)
		 */
        ServerSocket server = new ServerSocket(5446);  //创建服务端连接,同时指定所要监听的端口号
        while(true){
        	Socket socket = server.accept();   //监听端口,获得客户端对象
        	
        	/*
        	 * 开启一个新线程
        	 */
        	new Thread(){
        		public void run(){
        			try {
								InputStream is = socket.getInputStream();   //获得输入流
								OutputStream os = socket.getOutputStream();  //获得输出流
								
								/*
								 * 进行包装,方便字符串读写
								 */
								BufferedReader reader = new BufferedReader(new InputStreamReader(is)); 
								PrintStream ps = new PrintStream(os);
								
								//---------------------------------------------------------
								String result = reader.readLine(); //得到客户端字符串
								result = new StringBuilder(result).reverse().toString();  //调用StringBuilder的reverse()方法,虽然StringBuilder是线程不安全的,但是在
								                                                                                           //每一个线程中不是并发的
								ps.println(result); // 返回到客户端反转之后的字符串
								socket.close(); 
					} catch (IOException e) {
						e.printStackTrace();
					}
        		}
        	}.start();
        }
		
		
		
	}

}
