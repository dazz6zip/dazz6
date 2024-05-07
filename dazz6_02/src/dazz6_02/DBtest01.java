package dazz6_02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

// MariaDB(원격 DB 서버) 연동 프로그램 
public class DBtest01 {

	private Connection conn; // 단일 생성 가능
	private Statement stmt; // 여러 개 생성 가능
	private ResultSet rs; // 여러 개 생성 가능

	public DBtest01() {
		// 1. Driver file Loading
		// ------------------------------------------------------- 설명 첨언 필요
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		} catch (Exception e) {
			System.out.println("FAIL(Loading) : " + e);
			return;
		}

		// 2. Connect DB server
		try {
			String url = "jdbc:mariadb://localhost:3306/test";
			conn = DriverManager.getConnection(url, "root", "123");
		} catch (Exception e) {
			System.out.println("FAIL(Connect) : " + e);
			return;
		}

		// 3. Run SQL from DB server
		try {
			// sangdata 테이블 자료 읽기
			stmt = conn.createStatement();
//			rs = stmt.executeQuery("SELECT * FROM sangdata");
			rs = stmt.executeQuery("SELECT code, sang, su, dan FROM SANGDATA"); // 컬럼명 기입 권장
//			rs.next(); // cursor (record pointer) move 
//			System.out.println(rs.getString("sang")); // get 뒤에 불러올 데이터 타입 지정 (int, double...)

			while (rs.next()) { // 자료가 있는 동안 반복
				String code = rs.getString("code"); // 연산할 필요 없을 때 문자열로 받음
				String sangname = rs.getString("sang");
				int su = rs.getInt("su"); // 연산이 필요할 때 이렇게 받으면 됨 (예시)
				int dan = rs.getInt("dan");
				System.out.println(code + "\t" + sangname + "\t" + su + "\t" + dan);
			}

			System.out.println("\n-----");
			rs.close(); // ResultSet(rs) CLOSE
			rs = stmt.executeQuery("SELECT code AS 코드, sang AS 상품명, su, dan FROM sangdata"); // ResultSet(rs) REOPEN
			while (rs.next()) {
				String code = rs.getString("코드");
				String sangname = rs.getString(2);
				int su = rs.getInt(3);
				int dan = rs.getInt("dan");
				System.out.println(code + "\t" + sangname + "\t" + su + "\t" + dan);
			}

			rs.close();
			String sql = "SELECT COUNT(*) AS cou FROM sangdata";
			rs = stmt.executeQuery(sql);
			rs.next();
			System.out.println("건수 : " + rs.getString("cou"));
			System.out.println("건수 : " + rs.getString(1)); // 컬럼에 자동으로 순서 부여, 별명 대신 순서 기입 가능
		} catch (Exception e) {
			System.out.println("FAIL(Run) : " + e);
			return;
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e2) {
			}
		}
	}

	public static void main(String[] args) {
		new DBtest01();
	}

}
