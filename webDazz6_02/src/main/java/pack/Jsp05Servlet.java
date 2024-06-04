package pack;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Jsp05Servlet")
public class Jsp05Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String data = request.getParameter("data");
		System.out.println("수신 data : " + data);
		
		// 서버가 다른 파일을 호출하는 방법 1 : redirect 방식
		// client를 통해 server에게 파일 요청
//		response.sendRedirect("aaa.html?data=" + data); '
		// html을 호출할 경우 값을 넘길 수 없음
//		response.sendRedirect("jsp05called.jsp?data=" + data); 
		// jsp를 호출한 경우 값(only String)을 넘길 수 있음 
		// 주소 : jsp05called.jsp?data=tom
		
		// 서버가 다른 파일을 호출하는 방법 2 : forward 방식
		// server가 직접 server에 있는 파일 호출
		request.setAttribute("datas", data);
/*		request.setAttribute("datas2", data2);
		request.setAttribute("jikwons", jiklist);
		...
		request에 이름, 값 형태로 자바의 어떤 객체든 전달할 수 있음 */
/*		RequestDispatcher dispatcher = request.getRequestDispatcher("jsp05called.jsp");
		// jsp05called.jsp 값을 가져와서 푸쉬
		// 주소 : Jsp05Servlet?data=tom
		dispatcher.forward(request, response);
		// request에 datas라는 이름으로 data의 값이 담김 */
		request.getRequestDispatcher("jsp05called.jsp").forward(request, response); // 위 구문과 같은 의미
	}

}
