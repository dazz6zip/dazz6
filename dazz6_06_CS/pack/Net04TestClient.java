package pack;

import java.io.PrintWriter;
//import java.net.InetAddress;
import java.net.Socket;

public class Net04TestClient {

	public static void main(String[] args) {
		// 특정 컴의 접속 후 메시지 전달용
		try {
//			InetAddress ia = InetAddress.getByName("127.0.0.1");
//			System.out.println(ia);
//			Socket socket = new Socket(ia, 9999);
			Socket socket = new Socket("127.0.0.1", 9999); // 서버 접속
			PrintWriter writer = new PrintWriter(socket.getOutputStream(), true); 
			writer.println("Hi I am dazz6" + "\n"); // 127.0.0.1로 출력
			writer.close();
			socket.close();
		} catch (Exception e) {
			System.out.println("CLIENT ERROR : " + e);
		}

	}

}
