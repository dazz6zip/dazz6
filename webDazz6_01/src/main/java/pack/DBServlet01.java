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

@WebServlet("/DBServlet01")
public class DBServlet01 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;	
	
	public void init(ServletConfig config) throws ServletException {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/test", "root", "123");
			pstmt = conn.prepareStatement("SELECT * FROM sangdata");
			// 최초에 한 번만 데이터베이스를 읽어옴 (선처리방식)
		} catch (Exception e) {
			System.out.println("init() ERROR : " + e.getMessage());
		}
	}

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<html><body>");
			out.println("<h2>상품 자료</h2>");
			try {
				rs = pstmt.executeQuery();
				while(rs.next()) {
					out.println(rs.getString("code") + " " + rs.getString("sang") + " " + rs.getString("su") + " " + rs.getString("dan") + "<br/>");
				}
			} catch (Exception e) {
				System.out.println("service() - try ERROR : " + e.getMessage());
			}
			
			
			
			out.println("</body></html>");
			out.close();
		} catch (Exception e) {
			System.out.println("service() ERROR : " + e.getMessage());
		}
	}
	
	@Override
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
