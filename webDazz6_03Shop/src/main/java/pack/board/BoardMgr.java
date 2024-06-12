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
	private int pList = 10; // 페이지당 출력할 레코드 수
	private int pageSu; // 전체 페이지 수

	public BoardMgr() {
		try {
			Context context = new InitialContext();
			ds = (DataSource) context.lookup("java:comp/env/jdbc_maria");
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
			while (rs.next() && k < pList) {
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
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
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
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
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
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
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

	public void updateReadCnt(String num) { // 조회수 증가
		String sql = "UPDATE board SET readcnt = readcnt + 1 WHERE num = ?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, num);
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("updateReadCnt() ERROR : " + e.getMessage());
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
				System.out.println("updateReadCnt() - finally ERROR : " + e2.getMessage());
			}
		}
	}

	public BoardDTO getData(String num) {
		String sql = "SELECT * FROM board WHERE num = ?";
		BoardDTO dto = null;

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, num);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				dto = new BoardDTO();
				dto.setName(rs.getString("name"));
				dto.setPass(rs.getString("pass"));
				dto.setMail(rs.getString("mail"));
				dto.setTitle(rs.getString("title"));
				dto.setCont(rs.getString("cont"));
				dto.setBip(rs.getString("bip"));
				dto.setBdate(rs.getString("bdate"));
				dto.setReadcnt(rs.getInt("readcnt"));
			}

		} catch (Exception e) {
			System.out.println("getData() ERROR : " + e.getMessage());
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
				System.out.println("getData() - finally ERROR : " + e2.getMessage());
			}
		}
		return dto;
	}

	public BoardDTO getReplyData(String num) { // 댓글용
		String sql = "SELECT * FROM board WHERE num = ?";
		BoardDTO dto = null;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, num);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				dto = new BoardDTO();
				dto.setTitle(rs.getString("title"));
				dto.setGnum(rs.getInt("gnum"));
				dto.setOnum(rs.getInt("onum"));
				dto.setNested(rs.getInt("nested"));
			}
		} catch (Exception e) {
			System.out.println("getReplyData() ERROR : " + e);
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
				System.out.println("getReplyData() - finally ERROR : " + e2.getMessage());
			}
		}
		return dto;
	}

	public void updateOnum(int gnum, int onum) { // 댓글용 : onum 갱신
		// 같은 그룹의 레코드는 모두 작업에 참여 - 같은 그룹의 onum 값 갱신
		// 댓글의 onum은 이미 db에 있는 onum 보다 크거나 같은 값을 변경함
		String sql = "UPDATE board SET onum = onum + 1 WHERE onum >= ? AND gnum = ?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, onum);
			pstmt.setInt(2, gnum);
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("updateOnum() ERROR : " + e);
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
				System.out.println("updateOnum() - finally ERROR : " + e2.getMessage());
			}
		}
	}

	public void replySave(BoardFormBean bean) { // 댓글용 : 저장
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
			// 새로운 글은 항상 조회수가 0부터 시작
			pstmt.setInt(10, bean.getGnum());
			pstmt.setInt(11, bean.getOnum()); // onum
			pstmt.setInt(12, bean.getNested()); // nested (들여쓰기)
			// onum과 nested는 댓글일 때만 값을 받아옴

			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("replySave() ERROR : " + e);
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
				System.out.println("replySave() - finally ERROR : " + e2.getMessage());
			}
		}
	}

	public boolean checkPass(int num, String user_pass) { // 글 수정에서 비밀번호 비교
		boolean b = false;

		String sql = "SELECT pass FROM board WHERE num = ?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				if (user_pass.equals(rs.getString(1))) {
					b = true;
				}
			}
		} catch (Exception e) {
			System.out.println("checkPass() ERROR : " + e);
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
				System.out.println("checkPass() - finally ERROR : " + e2.getMessage());
			}
		}
		return b;
	}

	public void saveEdit(BoardFormBean bean) {
		String sql = "UPDATE board SET name = ?, mail = ?, title = ?, cont = ? WHERE num = ?";

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bean.getName());
			pstmt.setString(2, bean.getMail());
			pstmt.setString(3, bean.getTitle());
			pstmt.setString(4, bean.getCont());
			pstmt.setInt(5, bean.getNum());
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("saveEdit() ERROR : " + e);
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
				System.out.println("saveEdit() - finally ERROR : " + e2.getMessage());
			}
		}
	}

	public void delData(String num) {
		String sql = "DELETE FROM board WHERE num = ?";

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, num);
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("delData() ERROR : " + e);
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
				System.out.println("delData() - finally ERROR : " + e2.getMessage());
			}
		}
	}
}
