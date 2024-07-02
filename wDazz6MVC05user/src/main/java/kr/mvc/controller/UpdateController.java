package kr.mvc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.mvc.model.UserManager;

public class UpdateController implements Controller {
	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		
		// 폼빈 사용
		UserForm uf = new UserForm();
		uf.setUserid(request.getParameter("userid"));
		uf.setPassword(request.getParameter("password"));
		uf.setName(request.getParameter("name"));
		uf.setEmail(request.getParameter("email"));
		
		// 모델과 통신
		int result = UserManager.instance().update(uf);
		
		ModelAndView mav = new ModelAndView();
		if (result > 0) { // INSERT 성공
			mav.setViewName("list.m2"); 
		} else { // UPDATE 실패
			mav.setViewName("fail.html");
		}
		
		mav.setRedirect(true);

		return mav;
	}
}
