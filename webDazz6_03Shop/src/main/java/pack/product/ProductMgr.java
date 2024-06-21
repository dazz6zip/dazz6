package pack.product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import pack.member.ZipcodeDTO;
import pack.order.OrderBean;

public class ProductMgr {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private DataSource ds;
	
	public ProductMgr() {
		try {
			Context context = new InitialContext();
			ds = (DataSource) context.lookup("java:comp/env/jdbc_maria");
		} catch (Exception e) {
			System.out.println("DB CONNECT ERROR : " + e.getMessage());
		}
	}
	
	// 모든 상품 정보를 읽어 오기 위한 메소드
	public ArrayList<ProductDto> getProductAll() {
		ArrayList<ProductDto> list = new ArrayList<ProductDto>();
		try {
			conn = ds.getConnection();
			String sql = "SELECT * FROM shop_product ORDER BY no DESC";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				ProductDto dto = new ProductDto();
				dto.setNo(rs.getInt("no"));
				dto.setName(rs.getString("name"));
				dto.setPrice(rs.getString("price"));
				dto.setDetail(rs.getString("detail"));
				dto.setSdate(rs.getString("sdate"));
				dto.setStock(rs.getString("stock"));
				dto.setImage(rs.getString("image"));
				list.add(dto);
			}
		} catch (Exception e) {
			System.out.println("getProductAll() ERROR : " + e);
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
				System.out.println("getProductAll() - finally ERROR : " + e2.getMessage());
			}
		}
		return list;
	}
	
	// 상품 정보를 등록하기 위한 메소드 (INSERT)
	public boolean insertProduct(HttpServletRequest request) {
		boolean b = false;
		
		try {
			// 업로드할 이미지 경로 : upload 폴더 (절대 경로)
			String uploadDir = "C:/work/JAVA/JAVA-02/webDazz6_03Shop/src/main/webapp/upload";
			MultipartRequest multi = new MultipartRequest(request, uploadDir, (5 * 1024 * 1024), "UTF-8", new DefaultFileRenamePolicy());
			conn = ds.getConnection();
			String sql = "INSERT INTO shop_product(name, price, detail, sdate, stock, image) VALUES (?, ?, ?, NOW(), ?, ?)";
			pstmt = conn.prepareStatement(sql);
			// no 컬럼은 자동증가이기 때문에 setString 제외함
			pstmt.setString(1, multi.getParameter("name"));
			pstmt.setString(2, multi.getParameter("price"));
			pstmt.setString(3, multi.getParameter("detail"));
			// sdate는 SQL NOW() 사용
			pstmt.setString(4, multi.getParameter("stock"));
			
			if (multi.getFilesystemName("image") == null) { // 상품 등록시 이미지 선택 안 함
				pstmt.setString(5, "ready.gif");
			} else { // 상품 등록시 이미지 선택함
				pstmt.setString(5, multi.getFilesystemName("image"));				
			}
			
			if (pstmt.executeUpdate() > 0) {
				b = true;
			}
			
		} catch (Exception e) {
			System.out.println("insertProduct() ERROR : " + e);
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
				System.out.println("insertProduct() - finally ERROR : " + e2.getMessage());
			}
		}
		
		return b;
	}

	// 상품 목록에서 상세 보기 한 상품의 정보를 가져오기 위한 메소드
	public ProductDto getProduct(String no) {
		ProductDto dto = null;
		
		try {
			conn = ds.getConnection();
			String sql = "SELECT * FROM shop_product WHERE no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, no);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				dto = new ProductDto();
				dto.setNo(rs.getInt("no"));
				dto.setName(rs.getString("name"));
				dto.setPrice(rs.getString("price"));
				dto.setDetail(rs.getString("detail"));
				dto.setSdate(rs.getString("sdate"));
				dto.setStock(rs.getString("stock"));
				dto.setImage(rs.getString("image"));
			}
		} catch (Exception e) {
			System.out.println("getProduct() ERROR : " + e);
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
				System.out.println("getProduct() - finally ERROR : " + e2.getMessage());
			}
		}
		return dto;
	}

	// 상품 정보를 수정 후 등록하기 위한 메소드 (INSERT)
	public boolean updateProduct(HttpServletRequest request) {
		boolean b = false;
		
		try {
			// 업로드할 이미지 경로 : upload 폴더 (절대 경로)
			String uploadDir = "C:/work/JAVA/JAVA-02/webDazz6_03Shop/src/main/webapp/upload";
			MultipartRequest multi = new MultipartRequest(request, uploadDir, (5 * 1024 * 1024), "UTF-8", new DefaultFileRenamePolicy());
			
			conn = ds.getConnection();

			if (multi.getFilesystemName("image") == null) { // 상품 등록시 이미지 선택 안 함
				String sql = "UPDATE shop_product SET name = ?, price = ?, detail = ?, stock = ? WHERE no = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, multi.getParameter("name"));
				pstmt.setString(2, multi.getParameter("price"));
				pstmt.setString(3, multi.getParameter("detail"));
				pstmt.setString(4, multi.getParameter("stock"));
				pstmt.setString(5, multi.getParameter("no"));
			} else { // 상품 등록시 이미지 선택함
				String sql = "UPDATE shop_product SET name = ?, price = ?, detail = ?, stock = ?, image = ? WHERE no = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, multi.getParameter("name"));
				pstmt.setString(2, multi.getParameter("price"));
				pstmt.setString(3, multi.getParameter("detail"));
				pstmt.setString(4, multi.getParameter("stock"));
				pstmt.setString(5, multi.getFilesystemName("image"));				
				pstmt.setString(6, multi.getParameter("no"));
			}
			if (pstmt.executeUpdate() > 0) {
				b = true;
			}
		} catch (Exception e) {
			System.out.println("updateProduct() ERROR : " + e);
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
				System.out.println("updateProduct() - finally ERROR : " + e2.getMessage());
			}
		}
		
		return b;
	}
	
	// 상품을 삭제하기 위한 메소드 (DELETE)
	public boolean deleteProduct(String no) {
		boolean b = false;
		
		try {
			conn = ds.getConnection();
			String sql = "DELETE FROM shop_product WHERE no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, no);
			if (pstmt.executeUpdate() > 0) {
				b = true;
			}
		} catch (Exception e) {
			System.out.println("deleteProduct() ERROR : " + e);
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
				System.out.println("deleteProduct() - finally ERROR : " + e2.getMessage());
			}
		}
		
		return b;
	}
	
	// 상품 주문시 재고량 계산을 위한 메소드 (UPDATE)
	public void reduceProduct(OrderBean bean) {
		try {
			conn = ds.getConnection();
			String sql = "UPDATE shop_product SET stock = (stock - ?) WHERE no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bean.getQuantity());
			pstmt.setString(2, bean.getProduct_no()); // shop_order의 product_no은 shop_product의 no
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("reduceProduct() ERROR : " + e);
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
				System.out.println("reduceProduct() - finally ERROR : " + e2.getMessage());
			}
		}
	}
}
