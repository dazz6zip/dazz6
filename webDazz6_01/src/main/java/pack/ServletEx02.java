package pack;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pack.other.ServletEx02other;

@WebServlet("/ServletEx02")
public class ServletEx02 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ServletEx02other other;

	public void init(ServletConfig config) throws ServletException {
		// 서버는 init() 메소드를 호출해서 Servlet을 초기화함
		// 최초 요청 한 번만 실행
		other = new ServletEx02other("고길동");
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8"); // Mime type 과 문자 코드
		PrintWriter out = response.getWriter();
		out.println("<html><body>");
		out.println("<h1>서블릿 문서 Ex02</h1>");
		
		// 지역변수 출력
		int a = 10, b = 20;
		out.println("a : " + a + ", b : " + b);
		
		// 현재 클래스의 메소드 호출
		int tot = calcData(a, b);
		out.println("<br/>a + b : " + tot);
		
		// 클래스 호출
		// ServletEx02other other = new ServletEx02other("홍길동"); // new는 init에서
		String ir = other.getIrum();
		out.println("<hr/>이름 : " + ir);
		
		out.println("</body></html>");
		out.close();
	}
	
	private int calcData(int a, int b) {
		int imsi = a + b;
		return imsi;
	}

}
