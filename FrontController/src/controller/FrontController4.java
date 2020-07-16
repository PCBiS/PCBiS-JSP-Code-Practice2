package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.DateServiceImpl;
import service.GreetingServiceImpl;
import service.IndexServiceImpl;
import service.NullServiceImpl;
import service.Service;

public class FrontController4 extends HttpServlet {
	
	private Map<String, Service> commands = new HashMap<>();
	

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		processRequest(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		processRequest(req, resp);
	}
	
	@Override
	public void init() throws ServletException {
		
		commands.put("/", new IndexServiceImpl());
		commands.put("/index", new IndexServiceImpl());
		commands.put("/greeting", new GreetingServiceImpl());
		commands.put("/date", new DateServiceImpl());
	}

	
	private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String type = null;
		String command = request.getRequestURI();
		System.out.println(command);
		System.out.println(command.indexOf(request.getContextPath()));
		
		if (command.indexOf(request.getContextPath()) == 0) {
			type = command.substring(request.getContextPath().length());
		}
		System.out.println("요청파악 : " + type);
		
		Service service = commands.get(type);
		
		if (service == null) {
			service = new NullServiceImpl();
		}
		
		String page = service.getViewPage(request, response);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}	
}