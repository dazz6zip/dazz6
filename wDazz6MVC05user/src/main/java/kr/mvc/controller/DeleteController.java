package kr.mvc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.mvc.model.UserManager;

public class DeleteController implements Controller {
	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		String userid = request.getParameter("userid");
		// 모델과 통신
		int result = UserManager.instance().delete(userid);
		
		ModelAndView mav = new ModelAndView();
		if (result > 0) { // INSERT 성공
			mav.setViewName("list.m2"); 
			// 서버에서 서블릿을 부를 때는 클라이언트를 통해 호출해야 하므로 redirect 사용
			// 서버 -> 서블릿 (forward) 불가
		} else { // INSERT 실패
			mav.setViewName("fail.html");
		}
		
		mav.setRedirect(true);

		return mav;
	}
}
