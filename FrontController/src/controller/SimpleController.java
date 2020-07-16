package controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SimpleController extends HttpServlet {

	// 1. HTTP의 요청을 받는다.
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		processRequest(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		processRequest(req, resp);
	}

	private void processRequest(
			HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		//request.getParameter(name);
		//request.getRequestURL(); 
		//request.getRequestURI(); <----- 사용자 요청은 주로 이걸로 받게 됨 
		String type = request.getParameter("date");
		// 3. 핵심 처리 : 기능 수행.
		Object resultObj = null;
		String page = null;
		
		if(type == null || type.equals("/greeting")) {
			resultObj = "안녕하세요";
			page = "/view01.jsp";
		}else if (type.equals("/date")) {
			resultObj = new Date();
			page = "/view02.jsp";
		}else {
			resultObj = "invalid Type";
		}
		
		// 4. 처리한 결과 데이터를 속성에 저장. : view 페이지에 공유(전달)
		request.setAttribute("result", resultObj);
		
		// 5. 저장한 속성을 적절한 뷰 페이지로 포워딩.
		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}

	
}
