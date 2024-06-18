package pack.order;

import java.util.Hashtable;

public class CartMgr {
	private Hashtable<String, OrderBean> hCart = new Hashtable<String, OrderBean>();
	// key, value 형식으로 담기 위해 ArrayList가 아닌 MAP - HashTable 이용
	
	// 카트에 내용 추가
	public void addCart(OrderBean obean) {
		 // obean : id, quantity, product_no
		String product_no = obean.getProduct_no();
		int quantity = Integer.parseInt(obean.getQuantity());
		
		if (quantity > 0) {
			// 동일 상품 주문인 경우 주문 수량만 증가
			if (hCart.containsKey(product_no)) { // 해당 상품이 Cart에 이미 있는 경우
				OrderBean temp = hCart.get(product_no);
				// key에서 product_no를 찾고, 그에 상응하는 value를 받아옴

				quantity += Integer.parseInt(temp.getQuantity());
				// 기존에 등록돼 있는 값에 누적
				// product_no에 해당하는 상품 수량 증가 (누적)
				
				temp.setQuantity(Integer.toString(quantity));
				
				// 누적한 값을 정수형으로 바꿔서 quantity 치환
				// value(OrderBean)에서 setter 사용하여 Quantity 값 변경
				
				hCart.put(product_no, temp);
				// product_no key를 가진 OrderBean 덮어씌우기
				// quantity만 setter로 수정했기 때문에 id, product_no는 기존 값 그대로임!
			} else { // 해당 상품이 Cart에 최초로 들어오는 경우
				hCart.put(product_no, obean);
				// key(product_no) : obean(OrderBean) 타입으로 저장
			}
		}
	}
	
	// 카트 내용 읽음
	public Hashtable<String, OrderBean> getCartList() {
		return hCart;
	}
	
	// 카트 내용 수정
	public void updateCart(OrderBean obean) {
		String product_no = obean.getProduct_no();
		hCart.put(product_no, obean); // 덮어씌우기
	}
	
	// 카트 삭제
	public void deleteCart(OrderBean obean) {
		String product_no = obean.getProduct_no();
		hCart.remove(product_no);
	}
}
