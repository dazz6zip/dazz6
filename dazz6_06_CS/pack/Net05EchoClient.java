package pack;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Net05EchoClient {

	Socket socket;
	PrintWriter out;
	BufferedReader reader;
	
	public Net05EchoClient() {
		try {
			socket = new Socket("192.168.0.15", 8888);
			out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8), true);
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
		} catch (Exception e) {
			System.out.println("Net05EchoClient ERROR : " + e);
		}
	}
	
	public void sendReceiveData() {
		try {
		Scanner scanner = new Scanner(System.in);
		System.out.print("전송 메시지 입력 : ");
		String data = scanner.nextLine();
		out.println(data); // 서버로 전송
		
		String reData = reader.readLine(); // 서버가 보낸 자료 수신
		System.out.println("수신 자료 : " + reData);
		} catch (Exception e) {
			System.out.println("sendReceiveData ERROR : " + e);
		} finally {
			try {
				reader.close();
				out.close();
				socket.close();
			} catch (Exception e2) {
				System.out.println("sendReceiveData finally ERROR : " + e2);
			}
		}
	}
	
	public static void main(String[] args) {
		Net05EchoClient client = new Net05EchoClient();
		client.sendReceiveData();
		

	}

}
