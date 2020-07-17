package member.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.dao.MemberDao;
import service.Service;

public class MemberManagerServiceImpl implements Service {

	@Override
	public String getViewPage(HttpServletRequest request, HttpServletResponse response) {
		//MemberDao dao = MemberDao.getInstance();
		int manageIdx = Integer.parseInt(request.getParameter("manageIdx"));
		String doFunc = request.getParameter("func");
		
		request.setAttribute("manageIdx", manageIdx);
		request.setAttribute("function", doFunc);
		return "/WEB-INF/views/member/memberManageResult.jsp";
	}

}
