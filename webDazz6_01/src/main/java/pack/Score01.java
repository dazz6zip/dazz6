package pack;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

@WebServlet("/Score01")
public class Score01 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		HttpSession session = request.getSession(true);
		// 세션 있으면 읽고, 없으면 생성하기

		ArrayList<Score01DTO> slist = (ArrayList<Score01DTO>) session.getAttribute("list");
		// DTO 생성, 세션의 list 값을 받음

		if (slist == null) {
			// 최초의 경우 세션이 없기 때문에 if 블럭으로 들어옴
			slist = new ArrayList<Score01DTO>();
			// Score01DTO 타입의 객체를 담을 리스트 생성
		}
		
		String no = request.getParameter("no");
		String name = request.getParameter("name");
		int kor = Integer.parseInt(request.getParameter("kor"));
		int eng = Integer.parseInt(request.getParameter("eng"));
		// 국어 점수와 영어 점수는 연산이 필요하기 때문에 int로 casting
		
		for (int i = 0; i < slist.size(); i++) {
			// 중복값을 찾기 위한 for문
			if (no.equals(slist.get(i).getNo())) {
				// 입력받은 no 값이 세션 리스트 안에 이미 저장되어 있을 경우
				response.sendRedirect("score.html");
				// 이전 페이지로 돌아감
				return;
			} 
		}
		
		slist.add(new Score01DTO(no, name, kor, eng));
		// 생성자를 이용하여 Score01DTO 멤버필드에 값 치환
		// slist에 값 추가
		
		session.setAttribute("list", slist);
		// 값이 추가된 slist를 session에 추가
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<html><body>성적표");

		out.println("<p><table>");
		out.println("<tr><th>번호</th><th>이름</th><th>국어</th><th>영어</th><th>총점</th></tr>");

		int count = 0;
				
		for (int i = 0; i < slist.size(); i++) {
			// slist(ArrayList)의 전체 항목을 출력하기 위한 for문
			Score01DTO score = (Score01DTO) slist.get(i);
			// slist의 i 번째 값들을 Score01DTO 타입으로 casting 후 치환
			out.println("<tr><td align='center'>" + score.getNo() + "</td>");
			out.println("<td align='center'>" + score.getName() + "</td>");
			out.println("<td align='center'>" + score.getKor() + "</td>");
			out.println("<td align='center'>" + score.getEng() + "</td>");
			int total = score.getKor() + score.getEng();
			out.println("<td align='center'>" + total + "</td></tr>");
			count++;
		}
		out.println("</p></table>");
		out.println("인원수 : " + count + "명");
		out.println("<br/><br/>");
		out.println("<a onclick='history.back()'>새로 입력</a> <a style='text-decoration: none; color: black' href='Score02'>세션 삭제</a>");
		out.println("</body></html>");
		out.close();
		
	}

}
