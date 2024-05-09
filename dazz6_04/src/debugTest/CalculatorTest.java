package debugTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import debug.Calculator;

// TDD 정리 필요
public class CalculatorTest {
	
//	Calculator calculator = new Calculator();
	Calculator calculator;
	
	@Before // 테스트하기 전에 먼저 실행한다는 의미의 어노테이션 ------ 정리 필요
	public void setup() {
		calculator = new Calculator();
	}

	@Test
	public void testPlus() {
		assertTrue(calculator.plus(8, 4) == 12); // 괄호 안에 들어간 식이 맞으면 TRUE 아니면 FAlSE 반환 
		int a = 8, b = 8;
		assertEquals(a, b); // 괄호 안에 들어간 기본형 변수의 값이 같은지 확인
		
		int[] arr1 = {0, 0};
		int[] arr2 = new int[2];
		assertArrayEquals(arr1, arr2);
	}

	@Ignore // 제외한다는 의미의 어노테이션
	@Test
	public void testMulti() {
		fail("Not yet implemented");
	}

	@Test(timeout = 1000) // 1초 단위로 수행 시간 검사
	public void testDivide() {
		assertTrue(calculator.divide(8, 4) == 2);
//		assertTrue(calculator.divide(10, 4) == 2.5);
		
		for (int i = 0; i < 10000; i ++) {
			System.out.print(i + " ");
		}
	}

}
