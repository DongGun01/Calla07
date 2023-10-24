package project.spring.calla.interceptor;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginSessionInterceptor extends HandlerInterceptorAdapter {
	private static final Logger logger = LoggerFactory.getLogger(LoginSessionInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		logger.info("===== preHandle ȣ��");

		// �α��� ����(���� ����) : mapping�� url�� ��Ʈ�ѷ� �޼ҵ� ����
		// �α׾ƿ� ����(���� ����) : login url�� �̵�. ��ǥ url ����
		
		HttpSession session = request.getSession();
		String memberId = (String) session.getAttribute("memberId");
		if(memberId != null) {
			logger.info("�α��� ���� -> controller method ����");
			return true;
		} else {
			logger.info("�α׾ƿ� ���� -> controller method ���� �ȵ�");
			// ��ǥ url ������ ����(query string�� �����ϸ� ���� ����)
			String targetURL = saveDestination(request);
			logger.info("targetURL = " + targetURL);
			logger.info("targetURL = " + targetURL.length());
			response.sendRedirect("/calla/member/expired?targetURL=" + targetURL);
			return false;
		}
		
	} // end preHandle()

	private String saveDestination(HttpServletRequest request) {
		logger.info("saveDestination() ȣ��");
		
		String uri = request.getRequestURI();
		logger.info("��û URI : " + uri );

		String contextRoot = request.getContextPath();
		uri = uri.replace(contextRoot, "");
		
		String queryString = request.getQueryString();
		logger.info("���� ��Ʈ�� : " + queryString);
		
		String targetURL = "";
		if(queryString == null) {
			targetURL = uri;
		} else {
			targetURL = uri + "?" + queryString;
		}
		
		logger.info("targetURL : " + targetURL);
		try {
			targetURL = URLEncoder.encode(targetURL, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return targetURL;
	} // end saveDestination
}
