package pack.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class BoardMgr {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private DataSource ds;
	
	private int recTot; // 전체 레코드 수
	private int pList = 5; // 페이지당 출력할 레코드 수
	private int pageSu; // 전체 페이지 수
	
	public BoardMgr() {
		try {
			Context context = new InitialContext();
			ds = (DataSource)context.lookup("java:comp/env/jdbc_maria");
		} catch (Exception e) {
			System.out.println("DB CONNECT ERROR : " + e.getMessage());
		}
	}
	
//	public ArrayList<BoardDTO> getDataAll(int page) {
	public ArrayList<BoardDTO> getDataAll(int page, String stype, String sword) {
		ArrayList<BoardDTO> list = new ArrayList<BoardDTO>();
		
//		String sql = "SELECT * FROM board ORDER BY gnum DESC, onum ASC";
		
		String sql = "SELECT * FROM board";	
		try {
			conn = ds.getConnection();
			
			if (sword == null) { // 검색이 없는 경우
				sql += " ORDER BY gnum DESC, onum ASC";
				pstmt = conn.prepareStatement(sql);
			} else { // 검색이 있는 경우
				sql += " WHERE " + stype + " LIKE ?";
				sql += " ORDER BY gnum DESC, onum ASC";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, "%" + sword + "%"); // sword의 값이 포함되는
			}
			
			rs = pstmt.executeQuery();
			
			
			// paging
			for (int i = 0; i < (page - 1) * pList; i++) {
				rs.next(); // 레코드 포인터 이동
			}
			
			int k = 0;
			while(rs.next() && k < pList) {
				BoardDTO dto = new BoardDTO();
				dto.setNum(rs.getInt("num"));
				dto.setName(rs.getString("name"));
				dto.setTitle(rs.getString("title"));
				dto.setBdate(rs.getString("bdate"));
				dto.setReadcnt(rs.getInt("readcnt"));
				dto.setNested(rs.getInt("nested"));
				list.add(dto);
				k++;
			}
			
		} catch (Exception e) {
			System.out.println("getDataAll() ERROR : " + e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					rs.close();
				}
				if (conn != null) {
					rs.close();
				}
			} catch (Exception e2) {
				System.out.println("getDataAll() - finally ERROR : " + e2.getMessage());
			}
		}
		return list;
	}
	
	public int currentMaxNum() { // board 테이블의 최대 번호 반환
		String sql = "SELECT MAX(num) FROM board";
		int num = 0; // 반환된 레코드가 없으면 0 반환
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				num = rs.getInt(1);
			}
		} catch (Exception e) {
			System.out.println("currentMaxNum() ERROR : " + e.getMessage());
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					rs.close();
				}
				if (conn != null) {
					rs.close();
				}
			} catch (Exception e2) {
				System.out.println("currentMaxNum() - finally ERROR : " + e2.getMessage());
			}
		}
		return num;
	}

	
	public void saveData(BoardFormBean bean) { // board INSERT
		String sql = "INSERT INTO board VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bean.getNum());
			pstmt.setString(2, bean.getName());
			pstmt.setString(3, bean.getPass());
			pstmt.setString(4, bean.getMail());
			pstmt.setString(5, bean.getTitle());
			pstmt.setString(6, bean.getCont());
			pstmt.setString(7, bean.getBip());
			pstmt.setString(8, bean.getBdate());
			pstmt.setInt(9, 0); // readcnt (조회수)
			pstmt.setInt(10, bean.getGnum());
			pstmt.setInt(11, 0); // onum
			pstmt.setInt(12, 0); // nested (들여쓰기)
			
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("saveData() ERROR : " + e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					rs.close();
				}
				if (conn != null) {
					rs.close();
				}
			} catch (Exception e2) {
				System.out.println("saveData() - finally ERROR : " + e2.getMessage());
			}
		}
	}
	
	public void totalList() { // 전체 레코드 수 구하기
		String sql = "SELECT COUNT(*) FROM board";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			recTot = rs.getInt(1);
			System.out.println("전체 레코드 수 : " + recTot);
		} catch (Exception e) {
			System.out.println("totalList() ERROR : " + e.getMessage());
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					rs.close();
				}
				if (conn != null) {
					rs.close();
				}
			} catch (Exception e2) {
				System.out.println("totalList() - finally ERROR : " + e2.getMessage());
			}
		}
	}
	
	public int getPageSu() { // 전체 페이지 수 반환
		pageSu = recTot / pList;
		if (recTot % pList > 0) {
			pageSu++;
		}
		return pageSu;
	}
}
