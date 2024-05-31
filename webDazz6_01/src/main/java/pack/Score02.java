package pack;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Score02")
public class Score02 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		// 세션 생성, 있으면 읽음 (없어도 만들지 않음)
		PrintWriter out = response.getWriter();
		
		if(session == null) {
			// 만들어진 세션이 없으면 삭제 작업 하지 않음
			return;
		}
	
		ArrayList<Score01DTO> slist = (ArrayList<Score01DTO>)session.getAttribute("list");
		if (slist == null) {
			// 세션이 비워져 있으면 삭제 작업 하지 않음
			return;
		}
		
		session.removeAttribute("list");
		// 클라이언트 list의 값을 지움
		
		response.sendRedirect("score.html");
		// 점수 입력 창으로 되돌아감
	}

}
