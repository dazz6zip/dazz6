package pack.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pack.model.MessageModel;

public class MessagePro implements CommandInter {
	@Override
	public String showData(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 적당한 모델을 선택해 수행 결과 얻기
		MessageModel mm = new MessageModel(); // 싱글톤을 사용하는 것이 바람직함
		String msg = mm.messageProcess();
		request.setAttribute("datas", msg);
		return "view1.jsp";
	}
}
