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

@WebServlet("/ServletEx08Cart")
public class ServletEx08Cart extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String name = request.getParameter("name");
		int price = Integer.parseInt(request.getParameter("price"));
		
		HttpSession session = request.getSession(true); // 세션 생성
		
		ArrayList<ServletEx08Goods> glist = (ArrayList<ServletEx08Goods>)session.getAttribute("list");
		// session.getAttribute 리턴 타입 : Object, ArrayList로 casting 한 것
		// list라는 이름의 세션을 꺼냄
		
		if(glist == null) {
			glist = new ArrayList<ServletEx08Goods>(); // ServletEx08Goods 객체를 담을 glist 생성
		}
		glist.add(new ServletEx08Goods(name, price));
		session.setAttribute("list", glist);
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<html><body>🍥" + name + " 구입");
		out.println("<br/>[<a href='myshop.html'>계속 쇼핑하기</a>] ");
		out.println("[<a href='ServletEx08Buy'>결제</a>]<br/>");

		out.println("<p><table width='80%'>");
		out.println("<tr><th>상품명</th><th>가격</th></tr>");
		
		for (int i = 0; i < glist.size(); i++) {
			ServletEx08Goods goods = (ServletEx08Goods)glist.get(i);
			out.println("<tr><td align='center'>" + goods.getName() + "</td>");
			out.println("<td align='center'>" + goods.getPrice() + "</td></tr>");
		}
		
		out.println("</p></table>");
		out.println("</body></html>");
		out.close();
	}

}
