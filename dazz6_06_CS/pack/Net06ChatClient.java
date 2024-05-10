package pack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Net06ChatClient {

	private static final String HOST = "192.168.0.21";
	private static final int PORT = 5000;

	public static void main(String[] args) throws IOException {
		Socket socket = new Socket(HOST, PORT);
		System.out.println("서버 연결 성공");

		BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

		Scanner scanner = new Scanner(System.in);
		System.out.print("접속자 이름 입력 : ");
		String name = scanner.nextLine();
		out.println(name); // 접속자 이름 서버로 전송

		Thread readThread = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					String serverMessage;
					while ((serverMessage = in.readLine()) != null) {
						System.out.println(serverMessage);
					}
				} catch (Exception e) {
					System.out.println("수신 데이터 오류 : " + e.getMessage());
				}
			}
		});
		readThread.start();

		String msg;
		while ((msg = scanner.nextLine()) != null) {
			if (!msg.isEmpty()) {
				out.println(msg); // 채팅 메시지 전송
			}
		}
		socket.close();
		scanner.close();
	}
}
