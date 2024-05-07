package lambda;

// Lambda Expression : 이름이 없는 익명 함수(메소드)
// 람다 표현식은 함수형 인터페이스의 구현으로만 가용할 수 있다.
// 하나의 추상 메소드를 가져야 하며, 람다 표현식은 해당 메소드의 구현으로 동작한다.

@FunctionalInterface // 함수형 인터페이스임을 명시적으로 알림 (가독성을 위한 권장 사항, 없어도 무관)
interface HelloInter { // Lambda Expression을 사용할 인터페이스
	// 추상 메소드는 반드시 한 개!
	// 함수형 인터페이스라고 부름
	int calcData(int a, int b);
//	int calcData2(int a, int b); // 추상 메소드가 두 개 이상이기 때문에 에러 발생하여 주석 처리함
}

public class MyLambda01 implements HelloInter {

	@Override
	public int calcData(int a, int b) {
		// 인터페이스의 추상 메소드 오버라이딩
		return a + b;
	}

//	@Override
//	public int calcData2(int a, int b) {
//		return 0;
//	}

	public static void main(String[] args) {
		MyLambda01 my = new MyLambda01();
		System.out.println(my.calcData(3, 4)); // 전통적 방법
		
		System.out.println();
		// 인터페이스 변수 = 람다 표현식
		// (parameter...) -> {body}
		HelloInter inter = (x, y) -> x + y;
		System.out.println(inter.calcData(4, 5));
		
		HelloInter inter2 = (x, y) -> x * y;
		System.out.println(inter2.calcData(4, 5));
		
	}

}
