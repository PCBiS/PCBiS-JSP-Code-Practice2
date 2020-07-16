package controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FrontController2 extends HttpServlet {

	// 1. HTTP의 요청을 받는다.
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	private void processRequest(
			HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {

		// String type = request.getParameter("date");
		String type = null;
		String command = request.getRequestURI();
		System.out.println(command);
		System.out.println(command.indexOf(request.getContextPath()));
		
		
		// 만일 /fc/date 로 들어온다면 contextPath의 길이는 3이다.
		if (command.indexOf(request.getContextPath()) == 0) {
			type = command.substring(request.getContextPath().length());
		}
		
		// 3. 핵심 처리 : 기능 수행.
		Object resultObj = null;
		String page = null;
		String views = "/WEB-INF/views";
		
		// http://localhost:8080/fc/greeting
		if(type.equals("/greeting")) {
			resultObj = "안녕하세요";
			page = views + "view01.jsp";
		// http://localhost:8080/fc/date
		}else if (type.equals("/date")) {
			resultObj = new Date();
			page = views + "view02.jsp";
		}else if (type.equals("/")) {
			page = views + "index.jsp";
		}else{
			resultObj = "invalid Type";
		}
		
		// 4. 처리한 결과 데이터를 속성에 저장. : view 페이지에 공유(전달)
		request.setAttribute("result", resultObj);
		
		// 5. 저장한 속성을 적절한 뷰 페이지로 포워딩.
		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}

	
}
