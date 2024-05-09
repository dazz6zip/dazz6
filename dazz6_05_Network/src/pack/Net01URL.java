package pack;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;

// URL 클래스로 특정 웹 서버 컴퓨터의 문서 읽기
// 인터넷이 가능한 서버들의 자원에 접근하여 주소 및 기타 정보를 다루는 클래스
public class Net01URL {
	public static void main(String[] args) {
		try {
			URL url = new URL("https://www.daum.net");
			// https://www.daum.net:80/index.html
			// http 보안용 서버 ==> https, TCP 프로토콜 기반 응용 프로그램 계층에서 사용 
			InputStream is = url.openStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			
			// 읽은 문서 파일로 저장
			PrintWriter pw = new PrintWriter(System.out, true); // PrintWriter : 자료를 찍고 나서 데이터 지우기
			PrintWriter fw = new PrintWriter(new FileOutputStream("c:/work/ok.html"));
			String line = "";
			while ((line = br.readLine()) != null) {
				pw.println(line); // console 출력
				fw.println(line); // file 저장
				fw.flush();
			}
			br.close();
			is.close();
			pw.close();
			fw.close();

		} catch (Exception e) {
			System.out.println("ERROR : " + e);
		}
	}

}
