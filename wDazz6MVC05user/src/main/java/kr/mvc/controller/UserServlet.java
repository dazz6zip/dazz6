package kr.mvc.controller;

import java.io.IOException;
import java.util.StringTokenizer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("*.m2")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L; // 없어도 되는 구문
	
	private ModelAndView mav = null;
	private Controller ctr = null;
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		try {
			String ss = request.getRequestURI();
			int idx = ss.lastIndexOf("/");
			StringTokenizer st = new StringTokenizer(ss.substring(idx + 1), ".");
			ss = st.nextToken();
//			System.out.println("ss : " + ss); // login, list, insert, view
			
			String cmd = ss;
			ctr = getController(cmd);
			mav = ctr.execute(request, response);
			
			// 파일 호출 방식 선택
			if (mav.isRedirect()) {
				response.sendRedirect(mav.getViewName());
			} else {
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/" + mav.getViewName());
				rd.forward(request, response);
			}
		} catch (Exception e) {
			System.out.println("service ERROR " + e.getMessage());
		}
	}
	
	public Controller getController(String cmd) throws Exception {
		// cmd : 요청명 (= 파일명)
		if (cmd.equals("login")) {
			ctr = new LoginController();
		} else if (cmd.equals("list")) {
			ctr = new ListController();
		} else if (cmd.equals("insert")) {
			ctr = new InsertController();
		} else if (cmd.equals("view")) {
			ctr = new ViewController();
		} else if (cmd.equals("logout")) {
			ctr = new LogoutController(); 
		} else if (cmd.equals("updateform")) {
			ctr = new UpdateformController(); 
		} else if (cmd.equals("update")) {
			ctr = new UpdateController(); 
		} else if (cmd.equals("delete")) {
			ctr = new DeleteController(); 
		}
		return ctr;
	}

}
