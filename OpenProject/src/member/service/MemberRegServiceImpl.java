package member.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.Service;

public class MemberRegServiceImpl implements Service {

	@Override
	public String getViewPage(HttpServletRequest request, HttpServletResponse response) {
		// 사용자 데이터 받기 - uid, upw, uname, uphoto(파일의 경로)
		// 파일이 있을 경우 파일도 받기 (바이너리 파일)
		return "/WEB-INF/views/member/regForm.jsp";
	}

}
