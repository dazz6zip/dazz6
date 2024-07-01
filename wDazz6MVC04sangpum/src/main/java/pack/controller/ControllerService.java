package pack.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("*.do")
public class ControllerService extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String command = request.getParameter("command");
		
		CommandInter inter = null;
		String viewName = "/WEB-INF/views/";
		
		try {
			if(command.equals("sang")) {
				inter = new SangpumImpl();
				viewName += inter.showData(request, response);
				request.getRequestDispatcher(viewName).forward(request, response);
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
