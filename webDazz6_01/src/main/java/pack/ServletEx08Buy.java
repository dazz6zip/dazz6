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

@WebServlet("/ServletEx08Buy")
public class ServletEx08Buy extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 장바구니에 들어 있는 상품 가격의 합을 출력 후 세션 삭제
		// 클라이언트가 넘겨 준 session id를 이용해 session 읽기
		
		HttpSession session = request.getSession(false);
		// 이미 있는 세션을 지우기 위해 생성했으므로 false (있으면 읽고, 없어도 생성하지 않도록)
		
		if(session == null) { // 세션이 없으면 지우는 작업 하지 않음
			return;
		}
		
		ArrayList<ServletEx08Goods> glist = (ArrayList<ServletEx08Goods>)session.getAttribute("list");
		if (glist == null) { // 리스트가 비워져 있으면 지우는 작업 하지 않음
			return;
		}
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<html><body>");

		out.println("<p><table width='80%'>");
		out.println("<tr><th>상품명</th><th>가격</th></tr>");
		
		int total = 0;
		for (int i = 0; i < glist.size(); i++) {
			ServletEx08Goods goods = (ServletEx08Goods)glist.get(i);
			out.println("<tr><td align='center'>" + goods.getName() + "</td>");
			out.println("<td align='center'>" + goods.getPrice() + "</td></tr>");
			total += goods.getPrice();
		}
		out.println("<tr><td align='center' colspan='2'>결제 총액 : " + total + "</td></tr>");
		// 실제적인 결제는 현실적으로 불가 (결제가 되었다고 가정하는 것)
		
		// 결제 완료 이후 세션 삭제
//		session.invalidate(); // 해당 고객(클라이언트)의 모든 세션 삭제
		session.removeAttribute("list"); // 해당 고객(클라이언트)의 특정 세션 삭제
		out.println("<br/>감사합니다");
		out.println("<br/>[<a href='myshop.html'>다시 쇼핑하기</a>] ");
		out.println("</p></table>");
		out.println("</body></html>");
		out.close();
	}

}
