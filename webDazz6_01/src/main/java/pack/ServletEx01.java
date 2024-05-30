package pack;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ServletEx01")

// @WebServlet(name = "ServletEx01", urlPatterns = { "/ServletEx01", "/good.kor", "/dajeong" }, loadOnStartup = 1)
// 요청이 없어도, 웹서비스가 시작되면 서블릿 수행

public class ServletEx01 extends HttpServlet {
	private static final long serialVersionUID = 1L; // 버전넘버 무시 (지워도 무관)

	// 파라미터 순서 변경 금지
	// 받을 떄 request, 보낼 때 response
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("get 요청 성공");

		// 서블릿(웹용 자바)으로 클라이언트 브라우저에 데이터 전송
		response.setContentType("text/html;charset=utf-8"); // Mime type 과 문자 코드
		PrintWriter out = response.getWriter();
		// 웹용출력 out 객체 만듦
		out.println("<html><body>");
		out.println("<h1>서블릿 문서</h1>");
		out.println("방가버");
		out.println("</body></html>");
		out.close();
	}

}
