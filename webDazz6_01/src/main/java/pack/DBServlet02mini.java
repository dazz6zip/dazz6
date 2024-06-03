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

@WebServlet("/DBServlet02mini")
public class DBServlet02mini extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;	

	public void init(ServletConfig config) throws ServletException {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/test", "root", "123");
			pstmt = conn.prepareStatement("INSERT INTO guest (name, subject, content) values (?, ?, ?)");
			// 최초에 한 번만 데이터베이스를 읽어옴 (선처리방식)
		} catch (Exception e) {
			System.out.println("init() ERROR : " + e.getMessage());
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8"); // 한글 깨짐 방지
		
		String name = request.getParameter("name");
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		// 입력자료 검증 생략
		
		try {
			// 수신된 자료로 INSERT 처리
			pstmt.setString(1, name);
			pstmt.setString(2, subject);
			pstmt.setString(3, content);
			pstmt.executeUpdate(); // 리턴값 받아 줄 수 있지만 생략
			
//			response.sendRedirect("minimain.html");
			
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<html><body>");
			out.println("<b>" + name + " 님</b> 방명록 등록 완료");
			out.println("<br/><a href='minimain.html'>새 글</a>");
			out.println("<br/><a href='DBServlet02list'>글 보기</a>");
			out.println("</body></html>");
			out.close();
		} catch (Exception e) {
			System.out.println("doPost() ERROR : " + e.getMessage());
		}
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
