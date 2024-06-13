package pack.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class memberMgr {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private DataSource ds;
	
	public memberMgr() {
		try {
			Context context = new InitialContext();
			ds = (DataSource) context.lookup("java:comp/env/jdbc_maria");
		} catch (Exception e) {
			System.out.println("DB CONNECT ERROR : " + e.getMessage());
		}
	}
	
	// 동 이름으로 우편자료를 검색하기 위한 메소드
	public ArrayList<ZipcodeDTO> zipcodeRead(String dongName) {
		// 동 이름을 받아오고, ArrayList 타입으로 zipcode 자료를 반환
		ArrayList<ZipcodeDTO> list = new ArrayList<ZipcodeDTO>();
	
		try {
			conn = ds.getConnection();
			String sql = "SELECT * FROM ziptab WHERE area3 LIKE ?";
			// ziptab TABLE의 area3 내용 중 받아온 dongName 이 포함된 값을 찾는 SELECT
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dongName + "%"); // LIKE로 포함된 값을 찾기 때문에 % 혹은 _ 필수
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				// 위 SELECT문에서 받아온 자료를 모두 출력하기 위한 반복문
				// 열이 두 개 이상이기 때문에 while 반복문 사용 (값이 있는 동안 반복)
				ZipcodeDTO dto = new ZipcodeDTO();
				dto.setZipcode(rs.getString("zipcode"));
				dto.setArea1(rs.getString("area1"));
				dto.setArea2(rs.getString("area2"));
				dto.setArea3(rs.getString("area3"));
				dto.setArea4(rs.getString("area4"));
				// 받아온 자료들을 dto에 넣음
				list.add(dto); 
				// dto를 list에 넣음
			}
		} catch (Exception e) {
			System.out.println("zipcodeRead() ERROR : " + e);
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
				System.out.println("zipcodeRead() - finally ERROR : " + e2.getMessage());
			}
		}
		return list;
		// dongName 해당 자료를 dto 사용하여 ArrayList에 넣고 이를 반환
	}
	
	// 아이디 중복 확인을 위한 메소드
	public boolean idCheckProcess(String id) {
		boolean b = false;
		
		try {
			conn = ds.getConnection();
			String sql = "SELECT id FROM member WHERE id = ?";
//			String sql = "SELECT COUNT(*) FROM member WHERE id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			b = rs.next(); // 값이 있으면 true, 없으면 false 치환
		} catch (Exception e) {
			System.out.println("idCheckProcess() ERROR : " + e);
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
				System.out.println("idCheckProcess() - finally ERROR : " + e2.getMessage());
			}
		}
		return b;
	}
	
	// 회원가입 정보 DB에 INSERT 하는 메소드
	public boolean memberInsert(MemberBean mbean) {
		boolean b = false;
		
		try {
			conn = ds.getConnection();
			String sql = "INSERT INTO member VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mbean.getId());
			pstmt.setString(2, mbean.getPasswd());
			pstmt.setString(3, mbean.getName());
			pstmt.setString(4, mbean.getEmail());
			pstmt.setString(5, mbean.getPhone());
			pstmt.setString(6, mbean.getZipcode());
			pstmt.setString(7, mbean.getAddress());
			pstmt.setString(8, mbean.getJob());
			if (pstmt.executeUpdate() > 0) { 
				// INSERT 작업에 성공하면
				b = true;
			}
		} catch (Exception e) {
			System.out.println("memberInsert() ERROR : " + e);
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
				System.out.println("memberInsert() - finally ERROR : " + e2.getMessage());
			}
		}
		
		return b;
	}
	
	// 로그인했는지 확인하는 메소드
	public boolean loginCheck(String id, String passwd) {
		boolean b = false;
		
		try {
			conn = ds.getConnection();
			String sql = "SELECT * FROM member WHERE id = ? AND passwd = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, passwd);
			rs = pstmt.executeQuery();
			
			b = rs.next(); // 값이 있으면 true, 없으면 false 반환
		} catch (Exception e) {
			System.out.println("loginCheck() ERROR : " + e);
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
				System.out.println("loginCheck() - finally ERROR : " + e2.getMessage());
			}
		}
		return b;
	}
	
	// 회원 정보를 수정하기 위해 등록된 정보를 읽는 메소드
	public MemberBean getMember(String id) {
		MemberBean bean = null;
		try {
			conn = ds.getConnection();
			String sql = "SELECT * FROM member WHERE id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				bean = new MemberBean();
				bean.setId(rs.getString("id"));
				bean.setPasswd(rs.getString("passwd"));
				bean.setName(rs.getString("name"));
				bean.setEmail(rs.getString("email"));
				bean.setPhone(rs.getString("phone"));
				bean.setZipcode(rs.getString("zipcode"));
				bean.setAddress(rs.getString("address"));
				bean.setJob(rs.getString("job"));			
			}
		} catch (Exception e) {
			System.out.println("getMember() ERROR : " + e);
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
				System.out.println("getMember() - finally ERROR : " + e2.getMessage());
			}
		}
		return bean;
	}
}
