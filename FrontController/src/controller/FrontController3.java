package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.DateServiceImpl;
import service.GreetingServiceImpl;
import service.IndexServiceImpl;
import service.NullServiceImpl;
import service.Service;

public class FrontController3 extends HttpServlet {
	
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
		System.out.println("요청파악 : " + type);
		
		// 3. 핵심 처리 : 기능 수행.
		// Object resultObj = null;
		// String page = null;
		Service service = null;
		
		// http://localhost:8080/fc/greeting
		if(type.equals("/greeting")) {
			service = new GreetingServiceImpl();
		// http://localhost:8080/fc/date
		}else if (type.equals("/date")) {
			service = new DateServiceImpl();
		}else if (type.equals("/") || type.equals("/index") ) {
			service = new IndexServiceImpl();
		}else{
			service = new NullServiceImpl();
		}
		
		// 4. 처리한 결과 데이터를 속성에 저장. : view 페이지에 공유(전달)
		String page = service.getViewPage(request, response);
		
		// 5. 저장한 속성을 적절한 뷰 페이지로 포워딩.
		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}

	
	// 1. HTTP의 요청을 받는다.
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		processRequest(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		processRequest(req, resp);
	}
	
}
