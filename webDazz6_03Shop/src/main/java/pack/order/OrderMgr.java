package pack.order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import pack.member.ZipcodeDTO;

public class OrderMgr {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private DataSource ds;
	
	public OrderMgr() {
		try {
			Context context = new InitialContext();
			ds = (DataSource) context.lookup("java:comp/env/jdbc_maria");
		} catch (Exception e) {
			System.out.println("DB CONNECT ERROR : " + e.getMessage());
		}
	}
	
	public ArrayList<OrderBean> getOrder(String id) {
		ArrayList<OrderBean> list = new ArrayList<OrderBean>();
		
		try {
			conn = ds.getConnection();
			String sql = "SELECT * FROM shop_order WHERE id = ? ORDER BY no DESC";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				OrderBean bean = new OrderBean();
				bean.setNo(rs.getString("no"));
				bean.setProduct_no(rs.getString("product_no"));
				bean.setQuantity(rs.getString("quantity"));
				bean.setSdate(rs.getString("sdate"));
				bean.setState(rs.getString("state"));
				bean.setId(rs.getString("id"));
				list.add(bean);
			}
		} catch (Exception e) {
			System.out.println("getOrder() ERROR : " + e);
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
				System.out.println("getOrder() - finally ERROR : " + e2.getMessage());
			}
		}
		
		return list;
	}
	
	public void insertOrder(OrderBean bean) {
		try {
			conn = ds.getConnection();
			String sql = "INSERT INTO shop_order(product_no, quantity, sdate, state, id) VALUES (?, ?, now(), ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bean.getProduct_no());
			pstmt.setString(2, bean.getQuantity());
			// sdate는 now()로 INSERT
			pstmt.setString(3, "1");
			// state 구분 - 1: 접수 완료, 2: 입금 확인, 3: 배송 준비, 4: 배송 중, 5: 처리 완료
			pstmt.setString(4, bean.getId());
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("insertOrder() ERROR : " + e);
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
				System.out.println("insertOrder() - finally ERROR : " + e2.getMessage());
			}
		}
	}
	
	public ArrayList<OrderBean> getOrderAll() {
		ArrayList<OrderBean> list = new ArrayList<OrderBean>();
		
		try {
			conn = ds.getConnection();
			String sql = "SELECT * FROM shop_order ORDER BY no DESC";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				OrderBean bean = new OrderBean();
				bean.setNo(rs.getString("no"));
				bean.setProduct_no(rs.getString("product_no"));
				bean.setQuantity(rs.getString("quantity"));
				bean.setSdate(rs.getString("sdate"));
				bean.setState(rs.getString("state"));
				bean.setId(rs.getString("id"));
				list.add(bean);
			}
		} catch (Exception e) {
			System.out.println("getOrderAll() ERROR : " + e);
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
				System.out.println("getOrderAll() - finally ERROR : " + e2.getMessage());
			}
		}
		
		return list;
	}
	
	public OrderBean getOrderDetail(String no) {
		OrderBean bean = null;
		
		try {
			conn = ds.getConnection();
			String sql = "SELECT * FROM shop_order WHERE no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, no);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				bean = new OrderBean();
				bean.setId(rs.getString("id"));
				bean.setNo(rs.getString("no"));
				bean.setProduct_no(rs.getString("product_no"));
				bean.setQuantity(rs.getString("quantity"));
				bean.setSdate(rs.getString("sdate"));
				bean.setState(rs.getString("state"));
			}
		} catch (Exception e) {
			System.out.println("getOrderDetail() ERROR : " + e);
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
				System.out.println("getOrderDetail() - finally ERROR : " + e2.getMessage());
			}
		}
		
		return bean;
	}
}
