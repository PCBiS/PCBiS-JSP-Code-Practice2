package member.service;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdbc.ConnectionProvider;
import member.dao.MemberDao;
import model.Member;
import service.Service;

public class WithdrawMemberImpl implements Service {

	@Override
	public String getViewPage(HttpServletRequest request, HttpServletResponse response) {		
		MemberDao dao = MemberDao.getInstance();
		Member getMember = null;
		Connection conn = null;
		int getIdx = Integer.parseInt(request.getParameter("manageIdx"));
		
		try {
			conn = ConnectionProvider.getConnection();
			getMember = dao.getMemberInfo(conn, getIdx);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		// 탈퇴 시키기 전에 회원을 정말 내보낼 것인지에 대한 확인이 필요함으로 멤버 정보를 담아서 확인여부 검증 페이지를 로드.
		request.setAttribute("dowithrawmember", getMember);
		return "/WEB-INF/views/member/doWithdrawMember.jsp";
	}
//
}
