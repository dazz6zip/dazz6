package pack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

// Thread, Socket 활용 간단한 웹 서버 작성
public class Net07SimpleHTTPServer {
	private ServerSocket serversocket;
	private final int PORT;

	public Net07SimpleHTTPServer(int port) {
		this.PORT = port;
	}

	public void gogo() throws IOException {
		serversocket = new ServerSocket(PORT);
		System.out.println("HTML SERVER STARTED ON PORT : " + PORT);
		while (true) {
			try {
				Socket clientSocket = serversocket.accept();
				System.out.println("RECEIVED REQUEST FROM " + clientSocket.getRemoteSocketAddress());
				ClientHandler clientHandler = new ClientHandler(clientSocket);
				new Thread(clientHandler).start();
			} catch (Exception e) {
				System.out.println("gogo() ERROR : " + e.getMessage());
				break;
			}
		}
	}

	// 내부 클래스
	private static class ClientHandler implements Runnable {
		private Socket clientSocket;

		public ClientHandler(Socket socket) {
			clientSocket = socket;
		}

		@Override
		public void run() {
			try {
				BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
				String requestLine = in.readLine();
				System.out.println("Request : " + requestLine);
				
				// RESPONE (FOR HTTP REQUEST)
				out.println("HTTP/1.1 200 OK");
				out.println("Content-Type : text/html;charset=UTF-8");
				out.println("");
				out.println("<html><head><title>연습</title></head>");
				out.println("<body>");
				out.println("<h1>홈페이지</h1>");
				out.println("<a href='https://www.daum.net'>다음으로</a>출발<br>");
				out.println("<a href='https://www.naver.com'>네이버로</a>출발<br>");
				out.println("</body></html>");
				out.close();
				in.close();
				clientSocket.close();
				
			} catch (Exception e) {
				System.out.println("CLIENT REQUEST ERROR : " + e.getMessage());
			}
		}
	}

	public static void main(String[] args) {
		int port = 8080; // 8080 : 연습용 웹 서버 Default port
		try {
			Net07SimpleHTTPServer httpServer = new Net07SimpleHTTPServer(port);
			httpServer.gogo();
		} catch (Exception e) {
			System.out.println("ERROR : " + e);
		}
	}

}
