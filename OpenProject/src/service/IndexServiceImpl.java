package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IndexServiceImpl implements service.Service{

	@Override
	public String getViewPage(HttpServletRequest request, HttpServletResponse response) {
		// viewPage (보여줄 페이지)
				String viewPage = "/WEB-INF/views/index.jsp";
				
				
				// 응답 해 줄 데이터가 없는 정적(Static)인 페이지는 그냥 Return viewPage 하면 된다.
				// Date now = new Date();
				//request.setAttribute("result", now);
				
				return viewPage;
	}


}
