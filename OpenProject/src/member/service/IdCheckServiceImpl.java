package member.service;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdbc.ConnectionProvider;
import member.dao.MemberDao;
import service.Service;

public class IdCheckServiceImpl implements Service {
	
	MemberDao dao;
	
	@Override
	public String getViewPage(HttpServletRequest request, HttpServletResponse response) {
		String result = "N";
		String id = request.getParameter("uid");
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			dao = MemberDao.getInstance();
			int resultCnt = dao.selectById(conn, request, id);
			
			if (resultCnt == 0) {
				result = "Y";
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		request.setAttribute("idCheck", result);
		
		return "/WEB-INF/views/member/id_check.jsp";
	}

}
