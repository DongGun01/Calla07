package project.spring.calla.util;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@WebListener
public class SessionManager implements HttpSessionListener{
	
	private static final Logger logger = LoggerFactory.getLogger(SessionManager.class);
	
	private static Map<String, HttpSession> loginSessions = new HashMap<>();
	
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        // ������ ������ �� ȣ��Ǵ� ������ �ۼ��մϴ�.
        System.out.println("Session Created: " + se.getSession().getId());
        HttpSession session = se.getSession();
        String sessionId = session.getId();
        loginSessions.put(sessionId, session);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        // ������ ����� �� ȣ��Ǵ� ������ �ۼ��մϴ�.
        System.out.println("Session Destroyed: " + se.getSession().getId());
        HttpSession session = se.getSession();
        String sessionId = session.getId();
        loginSessions.remove(sessionId);
    }
    
    public Map<String, HttpSession> getLoginSessions() {
    	return loginSessions;
    }
}
