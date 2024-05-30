package pack;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ServletEx04Get")
public class ServletEx04Get extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("name");
		String addr = request.getParameter("addr");
		String age = request.getParameter("age");
		// 받은 자료로 Business Logic, DB에 등록 등을 할 수 있음

		// 클라이언트로 출력
		response.setContentType("text/html;charset=utf-8"); // Mime type 과 문자 코드
		PrintWriter out = response.getWriter();
		out.println("<html><body>");
		out.println("<h2>get 요청 결과</h2>");
		out.println(name + " 님의 주소 : " + addr + ", 나이 : " + age + "세<br/>");
		out.println(calcAge(age));
		out.println("<br/><br/><a href='getdata.html'>자료 다시 입력</a>");
		out.println("</body></html>");
		out.close();
	}

	private String calcAge(String age) {
		int imsi = Integer.parseInt(age) / 10 * 10;
		String result = "기타";
		switch (imsi) {
		case 20:
			result = "이십대";
			break;
		case 30:
			result = "이십대";
			break;
		case 40:
			result = "이십대";
			break;
		}
		return result;
	}

}
