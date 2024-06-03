package pack;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DBServlet02list")
public class DBServlet02list extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;	

	public void init(ServletConfig config) throws ServletException {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/test", "root", "123");
			pstmt = conn.prepareStatement("SELECT * FROM guest ORDER BY code DESC");
			// 최초에 한 번만 데이터베이스를 읽어옴 (선처리방식)
		} catch (Exception e) {
			System.out.println("init() ERROR : " + e.getMessage());
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<html><body>");
		out.println("<h2>방명록 목록</h2>");
		out.println("<table border='1' width='80%'>");
		try {
			rs = pstmt.executeQuery();
			while (rs.next()) {
				out.println("<tr style='background-color:black; color:white'>");
				out.println("<td>번호 : " + rs.getString("code") + "</td>");
				out.println("<td>작성자 : " + rs.getString("name") + "</td>");
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("<td colspan='2'><b>제목 : " + rs.getString("subject") + "</b></td>");
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("<td colspan='2' height='100' style='word-break:break-all; vertical-align:top;'>" + rs.getString("content") + "</td>");
				out.println("</tr>");
			}
		} catch (Exception e) {
			System.out.println("doGet() ERROR : " + e.getMessage());
		}
		out.println("</table>");
		out.println("<div style='text-align:center'>");
		out.println("<br/><a href='minimain.html'>글쓰기</a>");
		out.println("</div>");
		out.println("</body></html>");
		out.close();
	}

	public void destroy() {
		try {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (Exception e) {
			System.out.println("destroy() ERROR : " + e.getMessage());
		}
	}
}
