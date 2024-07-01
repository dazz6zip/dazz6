package pack.controller;

import java.io.IOException;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("*.do")
public class ControllerService extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 방법 1 : parameter 사용
//		String command = request.getParameter("command");
		
		// 방법 2 : 파일명을 요청으로 사용
		String ss = request.getRequestURI();
//		System.out.println("ss : " + ss);
		// ss : /wDazz6MVC04sangpum/jikwon.do
		int idx = ss.lastIndexOf("/");
		StringTokenizer st = new StringTokenizer(ss.substring(idx + 1), ".");
		ss = st.nextToken();
//		System.out.println("ss : " + ss);
		// ss : jikwon
		
		String command = ss;
		CommandInter inter = null;
		String viewName = "/WEB-INF/views/";
		
		try {
			if(command.equals("sang")) {
				inter = new SangpumImpl();
				viewName += inter.showData(request, response);
				request.getRequestDispatcher(viewName).forward(request, response);
			} else if (command.equals("jikwon")) {
				// ...
			} else {
				viewName = "error.html";
				// html은 redirect 사용
				response.sendRedirect(viewName);
			}
		} catch (Exception e) {
			System.out.println("service err : " + e.getMessage());
		}
	}

}
