package pack.order;

import java.util.Hashtable;

public class CartMgr {
	private Hashtable<String, OrderBean> hCart = new Hashtable<String, OrderBean>();
	// key, value 형식으로 담기 위해 ArrayList가 아닌 MAP - HashTable 이용
	
	// 카트에 내용 추가
	public void addCart(OrderBean obean) {
		
	}
	
	// 카트 내용 읽음
	public Hashtable<String, OrderBean> getCartList() {
		return hCart;
	}
	
	// 카트 내용 수정
	public void updateCart(OrderBean obean) {
		
	}
	
	// 카트 삭제
	public void deleteCart(OrderBean obean) {
		
	}
}
