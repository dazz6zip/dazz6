package pack;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// DBConnection copy

public class DBConnection03 {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	// 페이징을 위한 변수 선언
	private int recTotal = 0; // 레코드 전체 갯수
	private int pageSize = 5; // 페이지당 출력 레코드 수
	private int totalPage = 0; // 전체 페이지 수
	
	public DBConnection03() {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		} catch (Exception e) {
			System.out.println("DBConnection() ERROR : " + e.getMessage());
		}
	}
	
	/*
	public ArrayList<SangpumDTO> getDataAll(String pa) {
//		System.out.println("pa : " + pa);
		ArrayList<SangpumDTO> list = new ArrayList<SangpumDTO>();
		try {
			String url = "jdbc:mariadb://localhost:3306/test";
			conn = DriverManager.getConnection(url, "root", "123");
			pstmt = conn.prepareStatement("SELECT * FROM sangdata ORDER BY code DESC");
			rs = pstmt.executeQuery();
			
			int startNum = ((Integer.parseInt(pa) - 1) * pageSize) + 1;
			for (int p = 1; p < startNum; p++) { // 포인터 이동만을 위한 for문
				rs.next(); // 레코드 포인터 위치 이동 
				// pa:1(recPointer:0), pa:2(recPointer:5), pa:3(recPointer:10)
				
			}
			
			int i = 1;
			while (rs.next() && i <= pageSize) { // 이동된 포인터에서 5개만 읽는 것 (pageSize = 5)
				SangpumDTO dto = new SangpumDTO();
				dto.setCode(rs.getString("code")); // rs.getString(1)과 같은 의미
				dto.setSang(rs.getString("sang"));
				dto.setSu(rs.getString("su"));
				dto.setDan(rs.getString("dan"));
				list.add(dto);
				i++;
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
	*/
	
	// 위 코드를 스트림 + 람다 표현식으로 수정
	public ArrayList<SangpumDTO> getDataAll(String pa) {
		ArrayList<SangpumDTO> list = new ArrayList<SangpumDTO>();
		String url = "jdbc:mariadb://localhost:3306/test";
		try(
			Connection conn = DriverManager.getConnection(url, "root", "123");
			PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM sangdata ORDER BY code DESC");
			ResultSet rs = pstmt.executeQuery();
		) { 
			int startNum = ((Integer.parseInt(pa) - 1) * pageSize) + 1;
			rs.absolute(startNum - 1); // 레코드 포인터를 시작 위치로 이동

			list = Stream.iterate(1, (i) -> i + 1) // 1부터 시작하는 스트림 생성
					.limit(pageSize) // 스트림 크기 : pageSize(5) 로 제한
					.map((i) -> {
						// map을 통해 각 스트림 요소에 대해 SangpumDTO 객체 생성
						// rs.next()를 호출, 다음 레코드로 이동하고 각 컬럼값을 SangpumDTO에 설정
						try {
							if (rs.next()) {
								SangpumDTO dto = new SangpumDTO();
								dto.setCode(rs.getString("code")); // rs.getString(1)과 같은 의미
								dto.setSang(rs.getString("sang"));
								dto.setSu(rs.getString("su"));
								dto.setDan(rs.getString("dan"));
								return dto;
							}
						} catch (Exception e) {
							System.out.println("getDataAll() - list ERROR : " + e.getMessage());
						}
						return null;
					})
					.filter(Objects::nonNull) // null이 아닌 객체만 필터링
					.collect(Collectors.toCollection(ArrayList::new));
					// collect() : 스트림 결과를 ArrayList로 수집
		} catch (Exception e) {
			System.out.println("getDataAll() ERROR : " + e.getMessage());
		} // finally 필요없음, 자동으로 close 처리
		return list;
	}
	
	public int prepareTotalPage() {
		try {
			String url = "jdbc:mariadb://localhost:3306/test";
			conn = DriverManager.getConnection(url, "root", "123");
			pstmt = conn.prepareStatement("SELECT COUNT(*) FROM sangdata");
			rs = pstmt.executeQuery();
			
			if (rs.next()) { // 레코드 값이 있을 때
				recTotal = rs.getInt(1); // 갯수를 recTotal 변수에 치환
			}
			
			// 전체 페이지 수 구하기
			totalPage = (recTotal / pageSize);
			if (recTotal % pageSize != 0) { // 나머지가 있을 때
				totalPage += 1; // totalPage를 하나 늘려 줌
			}
//			System.out.println("전체 페이지 수 : " + totalPage);
		} catch (Exception e) {
			System.out.println("prepareTotalPage() ERROR : " + e.getMessage());
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
				System.out.println("prepareTotalPage() - finally ERROR : " + e2.getMessage());
			}
		}
		return totalPage;
	}
	
	public void insertData(SangpumBean bean) {
//		System.out.println(bean.getSang() + " " + bean.getSu() + " " + bean.getDan());
		try {
			String url = "jdbc:mariadb://localhost:3306/test";
			conn = DriverManager.getConnection(url, "root", "123");
			
			// 새 상품 코드 부여
			String sql = "SELECT MAX(code) FROM sangdata";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next(); // 값 시작 지점으로 포인터 한 칸 옮김
			int maxCode = rs.getInt(1);
			System.out.println("마지막 상품 번호 : " + maxCode);
			
			// 새 상품 INSERT
			pstmt = conn.prepareStatement("INSERT INTO sangdata VALUES (?, ?, ?, ?)");
			pstmt.setInt(1, maxCode + 1);
			pstmt.setString(2, bean.getSang());
			pstmt.setString(3, bean.getSu());
			pstmt.setString(4, bean.getDan());
			pstmt.executeUpdate(); // 변수에 값을 받을 경우 : 성공하면 1, 실패하면 0을 반환함
		} catch (Exception e) {
			System.out.println("insertData() ERROR : " + e.getMessage());
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
				System.out.println("insertData() - finally ERROR : " + e2.getMessage());
			}
		}
	}
}
