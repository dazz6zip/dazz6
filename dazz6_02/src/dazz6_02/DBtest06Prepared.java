package dazz6_02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBtest06Prepared {
	Connection conn;
	PreparedStatement pstmt; // 선처리 가능, SQL문에 입력 자료 적용시 ?(연산자) 사용 가능
	ResultSet rs;

	public DBtest06Prepared() {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			String url = "jdbc:mariadb://localhost:3306/test";
			conn = DriverManager.getConnection(url, "root", "123");

			String sql = "";
			
//			// 자료 추가하기
//			sql = "INSERT INTO sangdata VALUES (?, ?, ?, ?)";
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, "10");
//			pstmt.setString(2, "신상품");
//			pstmt.setInt(3, 12);
//			pstmt.setString(4, "5000");
//			pstmt.executeUpdate();
			
//			// 자료 수정하기
//			sql = "UPDATE sangdata SET sang = ?, su = ? WHERE code = ?";
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, "아메리카노");
//			pstmt.setInt(2, 33);
//			pstmt.setString(3, "10");
//			pstmt.executeUpdate();		
			
			// 자료 삭제하기
//			sql = "DELETE FROM sangdata WHERE code = ?";
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setInt(1, 10);
//			if (pstmt.executeUpdate() >= 1) {
//				System.out.println("성공");
//			} else {
//				System.out.println("실패");
//			}
			
			// 전체 자료 읽기
			sql = "SELECT * FROM sangdata";
			pstmt = conn.prepareStatement(sql); // 객체를 만들면서 sql 문장 부여
			rs = pstmt.executeQuery();

			while (rs.next()) {
				System.out.println(
						rs.getString(1) + "\t" + rs.getString(2) + "\t\t" + rs.getString(3) + "\t" + rs.getString(4));
			}

			System.out.println();

			// 부분 자료 읽기
			String no = "2"; // 외부에서 받았다고 가정
			sql = "SELECT * FROM sangdata WHERE code = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, no); // 첫 번째 ?에 no가 대응(맵핑)
			// 여기서 setSting이면 받아오는 자료의 타입이 String이어야 함 (DB에 있는 칼럼의 타입과는 상관없음)
			rs = pstmt.executeQuery();

			if (rs.next()) {
				System.out.println(
						rs.getString(1) + "\t" + rs.getString(2) + "\t\t" + rs.getString(3) + "\t" + rs.getString(4));
			}

		} catch (Exception e) {
			System.out.println("ERROR : " + e);
		}
	}

	public static void main(String[] args) {
		new DBtest06Prepared();

	}

}
