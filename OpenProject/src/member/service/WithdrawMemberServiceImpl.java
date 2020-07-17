package member.service;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdbc.ConnectionProvider;
import member.dao.MemberDao;
import service.Service;

public class WithdrawMemberServiceImpl implements Service {

	@Override
	public String getViewPage(HttpServletRequest request, HttpServletResponse response) {
		// 웹 페이지 단에서 get 형식으로 받아 올 속성.
		int getIdx = Integer.parseInt(request.getParameter("idx"));
		String getUname = request.getParameter("uid");
		System.out.println(getIdx + " / " + getUname);
		
		// 변수 영역
		Boolean withdrawMemberResult = false;
		MemberDao dao = MemberDao.getInstance();
		Connection conn;
		
		try {
			conn = ConnectionProvider.getConnection();
			withdrawMemberResult = dao.withdrawMember(conn, getIdx);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		request.setAttribute("withrawMemberName", getUname);
		request.setAttribute("withdrawResult", withdrawMemberResult);
		
		return "/WEB-INF/views/member/withdrawMemberResult.jsp";
	}

}
