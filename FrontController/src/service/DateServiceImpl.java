package service;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DateServiceImpl implements service.Service{

	@Override
	public String getViewPage(HttpServletRequest request, HttpServletResponse response) {
		// viewPage (보여줄 페이지)
				String viewPage = "/WEB-INF/views/view02.jsp";
				// 응답 해 줄 데이터 처리. (로직)
				Date now = new Date();
				// request 속성에 저장. (처리한 값 저장)
				request.setAttribute("result", now);
				
				return viewPage;
	}


}
