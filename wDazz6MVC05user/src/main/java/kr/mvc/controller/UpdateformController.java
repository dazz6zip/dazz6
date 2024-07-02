package kr.mvc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.mvc.model.UserDto;
import kr.mvc.model.UserManager;

public class UpdateformController implements Controller {
	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 수정 화면 출력
		request.setCharacterEncoding("utf-8");
		String userid = request.getParameter("userid");
		
		UserDto dto = UserManager.instance().findUser(userid);
		request.setAttribute("user", dto);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("update.jsp");
		mav.setRedirect(false);
		
		return mav;
	}
}
