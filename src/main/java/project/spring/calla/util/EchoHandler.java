package project.spring.calla.util;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import project.spring.calla.domain.AlarmVO;
import project.spring.calla.persistence.AlarmDAO;

@Component
@RequestMapping("/echo")
public class EchoHandler extends TextWebSocketHandler{
	
	@Autowired
	private AlarmDAO alarmDAO;
	
	public void setAlarmDAO(AlarmDAO alarmDAO) {
		this.alarmDAO = alarmDAO;
	}

	
	private static final Logger logger = LoggerFactory.getLogger(WebSocketHandler.class);
	//�α��� �� �ο� ��ü
	private List<WebSocketSession> sessions = new ArrayList<WebSocketSession>();
	
	//Ŭ���̾�Ʈ�� �� ���� ����
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		logger.info("Socket ����");
		//�� ������ ������ ������ ����Ʈ�� �־���
		sessions.add(session);
		logger.info(sessions.toString());
	}
	
	//JS���� �޼��� ���� ��.
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {// �޽���
	
		for(WebSocketSession single : sessions) {
//			���� �� ���
//			String msg = message.getPayload();
//			String[] str = msg.split(",");
//			//JS���� ���ϴ´�� send�Ͽ� �ش� ��� �� �˶� ����
//			//������ �亯 �޷��� ��(������ ID�� ���� ����)
//			if(str != null && str.length == 2) {
//				String id = str[0];
//				String q_subject = str[1];
//				int count = alarmDao.selectAlarmCount(id); //�˶��� ������ ��
//			}		
			
			//���Ǿ��̵�
			String hsid = (String) single.getAttributes().get("memberId");
			
			//���ǰ��� ������, �˶����� ���� ���� ���� ���� -> �α��� �� ����ڰ� ó������ �˶� �޴� ������
			//�ش� sendMsg�� DB���� �־ üũ �ȵ� �˶� ���� �����ϱ�
			if(single.getAttributes().get("memberId").equals(session.getAttributes().get("memberId"))) {//üũ �ȵ� �˸��鸸 ��Ƽ� View
				List<AlarmVO> vo = new ArrayList<>();
				vo = alarmDAO.select(hsid);
				for(AlarmVO alarm : vo) {
					int idx = alarm.getAlarmId();
					String prefix = alarm.getPrefix();
					String code = alarm.getCode();
					if(code.equals("NewPost")) {
						code = "�亯�� ��ϵǾ����ϴ�.";
					}
					TextMessage sendMsg = new TextMessage("("+idx+")" + prefix + "�� " + code);
					single.sendMessage(sendMsg);
				}
			}
		}
	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {//���� ����
		// TODO Auto-generated method stub
		logger.info("Socket ����");
		//�� ������ ����� ������ ����Ʈ���� ����.
		sessions.remove(session);
	}
}