package pack;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

// DBConnection copy

public class DBConnection02 {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	public static int MAX = 0;
	public static int MIN = 2147483647; // 2147483647 : int 최대값
	
	public DBConnection02() {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		} catch (Exception e) {
			System.out.println("DBConnection() ERROR : " + e.getMessage());
		}
	}
	
	public ArrayList<JikwonDTO> getData(String buser) {
		ArrayList<JikwonDTO> list = new ArrayList<JikwonDTO>();
		try {
			String url = "jdbc:mariadb://localhost:3306/test";
			conn = DriverManager.getConnection(url, "root", "123");
			String sql = "SELECT jikwon_no, jikwon_name, jikwon_jik, jikwon_gen, jikwon_pay FROM jikwon WHERE buser_num IN (SELECT buser_no FROM buser WHERE buser_name = ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, buser);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				JikwonDTO dto = new JikwonDTO();
				dto.setNo(rs.getString("jikwon_no"));
				dto.setName(rs.getString("jikwon_name"));
				dto.setJik(rs.getString("jikwon_jik"));
				dto.setGen(rs.getString("jikwon_gen"));
				list.add(dto);
				
				// 추가로 SQL 사용하지 않고 변수와 if문 사용하여 최대값, 최소값 구하기
				if(MAX < rs.getInt("jikwon_pay")) {
					MAX = rs.getInt("jikwon_pay");
				}
				if(MIN > rs.getInt("jikwon_pay")) {
					MIN = rs.getInt("jikwon_pay");
				}
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
