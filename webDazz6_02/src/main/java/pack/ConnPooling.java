package pack;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class ConnPooling {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private DataSource ds;
	
	public ConnPooling() {
/*		JNDI (java naming and directory interface)
		이름지정 및 디렉토리 서비스에서 제공하는 데이터 및 객체를 참조(lookup)하기 위한 자바 API
		일반적으로 자바 애플리케이션을 외부 디렉터리 서비스(DB server,LDAP server...)에 연결할 때 쓰이는데
		그중에서도 데이터베이스 연결에 가장 많이 쓰인다. */
		try {
			// context.xml에 설정된 DB 연결 정보 읽기
			// pool에서 Connection 객체 읽음
			Context context = new InitialContext();
			ds = (DataSource) context.lookup("java:comp/env/jdbc_maria"); 
			// java:comp/env : 키워드 + META-INF의 context.xml에서 DB 연결을 위해 지정한 name
		} catch (Exception e) {
			System.out.println("DB 연결 실패 : " + e.getMessage());
		}
	}
	
	public ArrayList<SangpumDTO> getDataAll() {
		ArrayList<SangpumDTO> list = new ArrayList<SangpumDTO>();
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement("SELECT * FROM sangdata");
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				SangpumDTO dto = new SangpumDTO();
				dto.setCode(rs.getString(1));
				dto.setSang(rs.getString(2));
				dto.setSu(rs.getString(3));
				dto.setDan(rs.getString(4));
				list.add(dto);
			}
		} catch (Exception e) {
			System.out.println("getdataAll() ERROR : " + e.getMessage());
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
	
	public boolean insertData(SangpumBean bean) {
		boolean b = false;
		
		try {
			conn = ds.getConnection();
			
			// 신상 번호 구하기
			pstmt = conn.prepareStatement("SELECT MAX(code) AS max FROM sangdata");
			rs = pstmt.executeQuery();
			int maxCode = 0;
			if (rs.next()) {
				maxCode = rs.getInt("max");
			}
			maxCode++;
//			rs.next();
//			int maxCode = rs.getInt(1) + 1;

			// 추가 작업
			pstmt = conn.prepareStatement("INSERT INTO sangdata(code, sang, su, dan) VALUES (?, ?, ?, ?)");
			pstmt.setInt(1, maxCode);
			pstmt.setString(2, bean.getSang());
			pstmt.setString(3, bean.getSu());
			pstmt.setString(4, bean.getDan());
			int success = pstmt.executeUpdate();
			if (success == 1) {
				b = true;
			}
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
		
		return b;
	}
	
	public SangpumDTO updateData(String code) {
		SangpumDTO dto = null;
		/* 클래식
		try {
			String sql = "SELECT * FROM sangdata WHERE code = ?";
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, code);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				dto = new SangpumDTO();
				dto.setCode(rs.getString("code"));
				dto.setSang(rs.getString("sang"));
				dto.setSu(rs.getString("su"));
				dto.setDan(rs.getString("dan"));
			}
		} catch (Exception e) {
			System.out.println("updateData() ERROR : " + e.getMessage());
		} finally {
			try {
				if (rs != null) {rs.close();}
				if (pstmt != null) {pstmt.close();}
				if (conn != null) {conn.close();}
			} catch (Exception e2) {
				System.out.println("updateData() - finally ERROR : " + e2.getMessage());
			}
		}
		*/
		
		String sql = "SELECT * FROM sangdata WHERE code = ?";
		try(
			Connection conn = ds.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
		) {
			pstmt.setString(1, code);
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
				dto = new SangpumDTO();
				dto.setCode(rs.getString("code"));
				dto.setSang(rs.getString("sang"));
				dto.setSu(rs.getString("su"));
				dto.setDan(rs.getString("dan"));
			}
		} catch (Exception e) {
			System.out.println("updateData() ERROR : " + e.getMessage());
		}
		return dto;
	}
	
	public boolean updateDataOk(SangpumBean bean) {
		boolean b = false;
		
		String sql = "UPDATE sangdata SET sang = ?, su = ?, dan = ? WHERE code = ?";
		try(
			Connection conn = ds.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
		) {
			pstmt.setString(1, bean.getSang());
			pstmt.setString(2, bean.getSu());
			pstmt.setString(3, bean.getDan());
			pstmt.setString(4, bean.getCode());
			
			if (pstmt.executeUpdate() > 0) {
				b = true; // 업데이트 작업에 성공하면 boolean 변수에 true 치환
			}
		} catch (Exception e) {
			System.out.println("updateDataOk() ERROR : " + e.getMessage());
		}		
		return b;
	}
	
	public boolean deleteData(String code) {
		boolean b = false;
		
		String sql = "DELETE FROM sangdata WHERE code = ?";
		try(
			Connection conn = ds.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
		) {
			pstmt.setString(1, code);
			
			if (pstmt.executeUpdate() > 0) {
				b = true; // 업데이트 작업에 성공하면 boolean 변수에 true 치환
			}
		} catch (Exception e) {
			System.out.println("deleteData() ERROR : " + e.getMessage());
		}		
		
		return b;
	}
}
