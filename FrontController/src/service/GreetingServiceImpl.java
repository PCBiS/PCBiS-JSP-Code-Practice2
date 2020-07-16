package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GreetingServiceImpl implements service.Service{

	@Override
	public String getViewPage(HttpServletRequest request, HttpServletResponse response) {
		// viewPage (보여줄 페이지)
				String viewPage = "/WEB-INF/views/view01.jsp";
				// 응답 해 줄 데이터 처리. (로직)
				String result = "안녕하세요";
				// request 속성에 저장. (처리한 값 저장)
				request.setAttribute("result", result);
				
				return viewPage;
	}


}
