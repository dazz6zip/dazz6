package pack;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DBConnection {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public DBConnection() {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		} catch (Exception e) {
			System.out.println("DBConnection() ERROR : " + e.getMessage());
		}
	}
	
	public ArrayList<SangpumDTO> getDataAll() {
		ArrayList<SangpumDTO> list = new ArrayList<SangpumDTO>();
		try {
			String url = "jdbc:mariadb://localhost:3306/test";
			conn = DriverManager.getConnection(url, "root", "123");
			pstmt = conn.prepareStatement("SELECT * FROM sangdata");
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				SangpumDTO dto = new SangpumDTO();
				dto.setCode(rs.getString("code")); // rs.getString(1)과 같은 의미
				dto.setSang(rs.getString("sang"));
				dto.setSu(rs.getString("su"));
				dto.setDan(rs.getString("dan"));
				list.add(dto);
			}
			
		} catch (Exception e) {
			System.out.println("getDataAll() ERROR : " + e.getMessage());
		} finally {
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
			} catch (Exception e2) {
				System.out.println("getDataAll() - finally ERROR : " + e2.getMessage());
			}
		}
		return list;
	}
}
