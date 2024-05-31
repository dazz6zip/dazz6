package pack;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/ServletEx07Session")
public class ServletEx07Session extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// session (세션) : 각 클라이언트의 정보를 웹서버에 메모리 확보 후 저장, 동적인 크기
		// 쿠키 안에 서버가 만든 세션 ID를 들고 옴
		HttpSession session = request.getSession(true); // 세션이 있으면 읽고 없으면 세션 생성
//		HttpSession session = request.getSession(false); // 세션이 있으면 읽음 (없어도 세션 생성 안 함)
		session.setMaxInactiveInterval(10); // 세션 유효시간 (초 단위, 60 -> 1분) // 기본값은 30분
		// 유효시간으로 부여한 시간은 동적으로 작동함. 클라이언트가 활동을 하지 않은 시간부터 카운팅. 활동하는 동안은 카운팅 x
		
		if (session != null) {
			session.setAttribute("name", "홍길동"); // 세션 ID 생성 후 서버뿐아니라 클라이언트 컴퓨터의 쿠키에도 저장됨 // 복수 작성 가능
		}
		
		response.setContentType("text/html;charset=utf-8");

		PrintWriter out = response.getWriter();
		out.println("<html><body>");
		out.println("session ID : " + session.getId());
		out.println("<br/>사용자명 : " + session.getAttribute("name"));
		out.println("</body></html>");
		out.close();
		
	}

}
