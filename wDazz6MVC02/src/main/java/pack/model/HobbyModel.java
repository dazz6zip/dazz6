package pack.model;

import java.util.ArrayList;

public class HobbyModel { // Model 영역 클래스
	
	public ArrayList<String> getHobby(String hobby) {
		ArrayList<String> list = new ArrayList<String>();
		
		if(hobby.equals("m")) {
			list.add("한라산");
			list.add("설악산");
		} else if(hobby.equals("t")) {
			list.add("부산");
			list.add("제주");
			list.add("인천");
		} else if(hobby.equals("s")) {
			list.add("수영장");
		} else if(hobby.equals("r")) {
			list.add("마라톤");
			list.add("단거리");
		}
		
		return list;
	}
}
