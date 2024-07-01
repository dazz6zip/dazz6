package pack.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pack.model.TestModel;
import pack.model.TestModel2;


@WebServlet("*.test")
public class TestController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TestModel model;
	private TestModel2 model2;

	public void init(ServletConfig config) throws ServletException {
		model = new TestModel();
		model2 = new TestModel2();
	}

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String num1 = request.getParameter("num1");
		String num2 = request.getParameter("num2");
		
		if (num1 == null) {
			String result = model2.oper2(num2);
			request.setAttribute("data", result);	
			request.getRequestDispatcher("/WEB-INF/views/test2show.jsp").forward(request, response);
		} else if (num2 == null) {
			ArrayList<String> list = model.oper1(num1);
			request.setAttribute("data", list);			
			request.getRequestDispatcher("/WEB-INF/views/test1show.jsp").forward(request, response);
		}
	}

}
