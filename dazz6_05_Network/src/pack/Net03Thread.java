package pack;

import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

// 멀티 스레드로 멀티 태스킹 : 복수 개의 문서 읽기
public class Net03Thread implements Runnable {

	private String url;
	private String title;

	public Net03Thread(String url, String title) {
		this.url = url;
		this.title = title;
	}

	@Override
	public void run() {
		try {
			Document doc = Jsoup.connect(url).get(); // 네트워크를 통해 다른 컴에 접속 후 자료 읽기
			String text = doc.text();
			System.out.println("-----");
			System.out.println("문서 제목 : " + title);
			printKorText(text);
		} catch (Exception e) {
			System.out.println("READ ERROR : " + e);
		}
	}

	private static void printKorText(String text) { // 한글만 추출하기 위한 메소드
		// 정규 표현식 사용 ----------------------------- 정리 필요
		// 한글과 공백만 얻기
		Pattern pattern = Pattern.compile("[가-힣\\s]+");
		Matcher matcher = pattern.matcher(text);

		while (matcher.find()) {
			String line = matcher.group().trim();
			if (!line.isEmpty() && line.length() > 1) { // 비어 있는 줄이나 한 글자는 제외
				System.out.println(line);
			}
		}
	}

	public static void main(String[] args) throws Exception {
		String[] titles = { "백설공주", "인어공주" };
		String[] urls = { "https://ko.wikipedia.org/wiki/" + URLEncoder.encode(titles[0], "UTF-8"),
				"https://ko.wikipedia.org/wiki/" + URLEncoder.encode(titles[1], "UTF-8") };

		for (int i = 0; i < urls.length; i++) {
			Thread thread = new Thread(new Net03Thread(urls[i], titles[i]));
			thread.start();
		}
		
		System.out.println("프로그램 종료");
	}
}
