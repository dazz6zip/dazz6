package pack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
//import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// 멀티 채팅 서버 : Thread, Socket 사용
public class Net06ChatServer {
	private static final int PORT = 5000;
//	private static List<Socket> clients = new ArrayList<Socket>();
	private static List<Socket> clients = new CopyOnWriteArrayList<Socket>();
	// CopyOnWriteArrayList
	// ㄴ Thread 최적화 List, 컨텐츠를 읽어 어딘가에 전달할 때 컨텐츠를 복사하여 전달하므로 Thread 에서 안심하고 처리할 수 있음

	private static ExecutorService pool = Executors.newFixedThreadPool(4);
	// ExecutorService 이용하여 Thread pool 생성, 병렬처리 가능

	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket = new ServerSocket(PORT);
		System.out.println("채팅 서버 서비스 시작...");

		try {
			while (true) {
				Socket clientSocket = serverSocket.accept();
				System.out.println("새로운 접속자 연결 : " + clientSocket.getInetAddress());
				clients.add(clientSocket);

				// Thread pool 객체가 Thread 실행
				pool.execute(new ClientHandler(clientSocket));
			}
		} finally {
			serverSocket.close();
		}
	}

	static class ClientHandler implements Runnable {

		private Socket socket;
		private BufferedReader in;
		private PrintWriter out;

		public ClientHandler(Socket socket) throws IOException {
			this.socket = socket;
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream(), true);
		}

		@Override
		public void run() {
			try {
				String name = in.readLine(); // 클라이언트 접속자명 받기
				if (name == null) {
					throw new IOException("클라이언트 연결 실패");
				}
				System.out.println(name + " 님 접속");
				broadcastMessage(name + " 님 접속");

				String message;
				while ((message = in.readLine()) == null) { // 메시지 수신
					broadcastMessage(name + " 님 메시지 : " + message);
				}
			} catch (Exception e) {
				System.out.println("접속자 연결 오류 : " + e.getMessage());
			} finally {
				try {
					if (socket != null) {
						socket.close();
						clients.remove(socket);
					}
				} catch (Exception e2) {
					System.out.println("연결 해제 오류 : " + e2.getMessage());
				}
			}
		}

		private void broadcastMessage(String message) {
			for (Socket client : clients) {
				try {
					if (!client.isClosed()) {
						// 클라이언트로 송신할 데이터를 위한 PrintWriter 객체를 생성해 송신 후 자동으로 flush(메모리 비움)
						PrintWriter clientOut = new PrintWriter(client.getOutputStream(), true);
						clientOut.println(message);
					}
				} catch (Exception e) {
					System.out.println("broadcastMessage ERROR : " + e.getMessage());
				}
			}
		}
	}

}
