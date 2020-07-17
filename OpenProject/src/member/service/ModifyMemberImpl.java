package member.service;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdbc.ConnectionProvider;
import member.dao.MemberDao;
import model.Member;
import service.Service;

public class ModifyMemberImpl implements Service {

	@Override
	public String getViewPage(HttpServletRequest request, HttpServletResponse response) {
		Member member = null;
		MemberDao dao = MemberDao.getInstance();
		Connection conn = null;
		int getIdx = Integer.parseInt(request.getParameter("manageIdx"));
		
		try {
			conn = ConnectionProvider.getConnection();
			member = dao.getMemberInfo(conn, getIdx);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		request.setAttribute("modMember", member);
		return "/WEB-INF/views/member/memberInfoModifyForm.jsp";
	}

}
