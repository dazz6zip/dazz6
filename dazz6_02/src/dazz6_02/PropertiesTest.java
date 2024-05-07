package dazz6_02;

import java.io.FileInputStream;
import java.util.Properties;

public class PropertiesTest {

	public static void main(String[] args) {
		// properties 파일 읽기
		// 유지보수와 보안적 측면을 위해 파일명.properties에 정보를 담은 후 읽어 주는 형식으로 사용
		
		Properties properties = new Properties();
		try {
			properties.load(new FileInputStream("C:\\work\\JAVA\\dazz6_02\\src\\dazz6_02\\ex.properties"));
			System.out.println(properties.getProperty("mes1"));
			System.out.println(properties.getProperty("mes2"));
		} catch (Exception e) {
			System.out.println("ERROR : " + e);
		}
		
	}

}
