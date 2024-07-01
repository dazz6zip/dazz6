package pack.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pack.model.SangpumDto;
import pack.model.SangpumModel;

public class SangpumImpl implements CommandInter {
	// 모델과 통신하기 위한 controller 영역 내 클래스
	@Override
	public String showData(HttpServletRequest request, HttpServletResponse response) throws Exception {
		SangpumModel sm = new SangpumModel(); // 싱글톤이 바람직함
		
		ArrayList<SangpumDto> list = (ArrayList<SangpumDto>)sm.selectDataAll();
		request.setAttribute("datas", list);
		
		return "show.jsp";
	}
}
