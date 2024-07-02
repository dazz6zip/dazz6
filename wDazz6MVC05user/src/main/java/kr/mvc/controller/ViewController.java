package kr.mvc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.mvc.model.UserDto;
import kr.mvc.model.UserManager;

public class ViewController implements Controller {
	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 상세 보기 처리
		request.setCharacterEncoding("utf-8");
		String userid = request.getParameter("userid");
		
		// 모델과 통신
		UserDto dto = UserManager.instance().findUser(userid);
		request.setAttribute("user", dto);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("view.jsp");
		mav.setRedirect(false);
		
		return mav;
	}
}
