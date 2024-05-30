package pack;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/post.do")
public class ServletEx05Post extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8"); // 수신 자료 한글 깨짐 방지
		response.setContentType("text/html;charset=utf-8");
		
		PrintWriter out = response.getWriter();
		out.println("<html><body>");
		out.println("<h2>post 요청 결과</h2>");

		String irum = request.getParameter("name");
		String[] juso = request.getParameterValues("addr"); // 매개변수 중복 -> 배열 처리
		out.println(irum + " 님" + ", 주소 : " + juso[0] + " " + juso[1]);
		
		// checkbox
		try {
			String[] sports = request.getParameterValues("sports");
			out.println("<br/><br/>선택 종목 : ");
			for (String s : sports) {
				out.println(s + " ");
			}
		} catch (Exception e) {
			out.print("<br/><br/>종목 선택 권장");
			return;
		}
		
		// radio
		String language = request.getParameter("lan");
		out.print("<br/><br/>선택 언어 : " + language);
		
		// select
		String tr = request.getParameter("tr");
		out.print("<br/><br/>교통 수단 : " + tr);
		
		// hidden
		String edu = request.getParameter("edu");
		out.print("<br/><br/>교육 센터 : " + edu);
		
		out.println("<br/><br/><a href='postdata.html'>자료 다시 입력</a>");
		out.println("</body></html>");
		out.close();
	}

}
