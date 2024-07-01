package pack.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pack.model.DateModel;

public class DatePro implements CommandInter {
	@Override
	public String showData(HttpServletRequest request, HttpServletResponse response) throws Exception {
		DateModel dm = new DateModel(); // 싱글톤을 사용하는 것이 바람직함
		
		ArrayList<String> list = dm.getDate();
		request.setAttribute("datas", list);
		
		return "view2.jsp";
	}
}
