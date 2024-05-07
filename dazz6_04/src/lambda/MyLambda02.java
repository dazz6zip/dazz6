package lambda;

@FunctionalInterface
interface MyInter {
	void aaa();
}

@FunctionalInterface
interface MyInterArg {
	void bbb(int a, int b);
}

@FunctionalInterface
interface MyInterArgReturn {
	int ccc(int a, int b);
}

public class MyLambda02 {

	public static void main(String[] args) {
		// 1. 인자가 없는 추상 메소드 처리
		// 전통적 표현
		MyInter inter = new MyInter() {
			@Override
			public void aaa() {
				System.out.println("익명 클래스의 aaa() 메소드 오버라이딩");
			}
		};
		inter.aaa();

		// 람다식 표현
		MyInter inter2 = () -> {
			System.out.println("익명 클래스의 aaa() 메소드 오버라이딩 - 람다 사용 (1)"); // 실행 문장 하나일 경우 중괄호 없어도 됨
		};
		inter2.aaa();

		MyInter inter3 = () -> {
			int imsi = 10;
			System.out.println("익명 클래스의 aaa() 메소드 오버라이딩 - 람다 사용 (2)"); // 실행 문장 하나일 경우 중괄호 없어도 됨
			System.out.print(" : 복수 명령문 처리, ");
			System.out.println("imsi : " + imsi);
		};
		inter3.aaa();
		
		System.out.println("\n-----");
		// 2. 인자가 있는 추상 메소드 처리
		// 전통적 표현
		MyInterArg interArg = new MyInterArg() {
			@Override
			public void bbb(int a, int b) {
				System.out.println("두 수의 합 : " + (a + b));
			}
		};
		interArg.bbb(3, 4);

		// 람다식 표현 : 아규먼트가 한 개일 경우 괄호 없어도 됨
		MyInterArg interArg2 = (a, b) -> {
			System.out.println("두 수의 합 (람다) : " + (a + b));
		};
		interArg2.bbb(3, 4);
		
		System.out.println("\n-----");
		// 3. 인자가 있고 반환값도 있는 추상 메소드 처리
		// 전통적 표현
		MyInterArgReturn argReturn = new MyInterArgReturn() {
			@Override
			public int ccc(int a, int b) {
				System.out.println("ccc() 처리");
				return a + b;
			}
		};
		int result = argReturn.ccc(5, 6);
		System.out.println("a + b = " + result);
		
		// 람다식 표현
//		MyInterArgReturn argReturn2 = (m, n) -> (m + n);
		MyInterArgReturn argReturn2 = (m, n) -> {
			System.out.println("ccc() 람다 처리");
			return m + n;
		};
		int result2 = argReturn2.ccc(5, 6);
		System.out.println("a + b = " + result2);
		
	}

}
