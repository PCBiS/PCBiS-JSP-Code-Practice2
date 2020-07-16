package controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.NullServiceImpl;
import service.Service;

public class FrontController extends HttpServlet {

	private Map<String, Service> commands = new HashMap<>();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		processRequest(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		processRequest(req, resp);
	}

	// 1. commandService.properties(외부 설정) => Properties
	// 2. 클래스 정보의 클래스들을 생성 -> 인스턴스 자동 생성 처리.
	// 3. map에 사용자 요청 command 와 instance 를 저장.
	@Override
	public void init(ServletConfig conf) throws ServletException {
		// 1. 외부 설정파일의 내용을 메모리로 로딩.
		Properties prop = new Properties();
		FileInputStream fis = null;
		// path 가 바라보는 경로 : 설정 파일의 웹에서의 경로.
		String path = "/WEB-INF/commandService.properties";
		// configFile이 바라보는 경로 : 설정파일의 시스템상의 Absoulte Path
		String configFile = conf.getServletContext().getRealPath(path);

		// 참조 -> https://codechacha.com/ko/java-try-with-resources/ 개선권장
		try {
			fis = new FileInputStream(configFile);
			prop.load(fis);
		} catch (FileNotFoundException e) {
			System.out.println("파일을 찾지 못하였습니다. properties 파일이 제대로 제공 되었는지 확인 해 주십시요.");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("파일 내부 설정이 잘못되었습니다. properties파일 내부의 설정값의 규칙을 확인 바랍니다.");
			e.printStackTrace();
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		Iterator itr = prop.keySet().iterator();

		while (itr.hasNext()) {
			String command = (String) itr.next(); // 사용자가 요청 한 URI
			String serviceClassName = prop.getProperty(command); // 사용자 요청 처리를 위한 클래스 정보.

			try {
				// 인스턴스 생성을 위한 Class 객체
				Class serviceClass = Class.forName(serviceClassName);
				Service service = (Service) serviceClass.newInstance();
				commands.put(command, service);
				System.out.println(command + "=" + serviceClassName);
			} catch (ClassNotFoundException e) {
				System.out.println("설정파일에서 설정 된 Class가 잘못되었습니다. 설정 파일을 확인해주세요.");
				e.printStackTrace();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}

	private void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

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
