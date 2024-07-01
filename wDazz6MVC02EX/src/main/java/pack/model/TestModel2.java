package pack.model;

public class TestModel2 {
	
	
	public String oper2(String t) {
		String result = "";
		
		if (Integer.parseInt(t) % 2 == 0) {
			result = "짝수";
		} else {
			result = "홀수";	
		}
		
		return result;
	}
}
