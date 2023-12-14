package project.spring.calla.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import project.spring.calla.domain.AlarmVO;
import project.spring.calla.service.AlarmService;

@Component
@RequestMapping("/echo")
public class EchoHandler extends TextWebSocketHandler{
	
	@Autowired
	private AlarmService alarmService;
	
	
	private static final Logger logger = LoggerFactory.getLogger(WebSocketHandler.class);
	//�α��� �� �ο� ��ü
//	private List<WebSocketSession> sessions = new ArrayList<WebSocketSession>();
	
	Map<String, WebSocketSession> userSessions = new HashMap<>();
	
	
	//Ŭ���̾�Ʈ�� �� ���� ����
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		logger.info("���� ����" + session.getId());
		logger.info("Socket ����" + session);
		//�� ������ ������ ������ ����Ʈ�� �־���
//		for(WebSocketSession sess : sessions) {
//			if (session.getAttributes().get("memberNickname").equals(sess.getAttributes().get("memberNickname")))
//			sessions.remove(sess);
//			break;
//		}
//		sessions.add(session);
//		logger.info("sessions size = " + sessions.size());
		
		Map<String, Object> sessionVal =  session.getAttributes();
		String memberNickname = (String) sessionVal.get("memberNickname");
		
		if(userSessions.get(memberNickname) != null) {
			//userId�� ���� �����ǰ��� ����Ǿ� �ִٸ� update
			userSessions.replace(memberNickname, session);
		} else {
			//userId�� �����ǰ��� ���ٸ� put
			userSessions.put(memberNickname, session);
		}
		logger.info("userSessions size = " + userSessions.size());
		logger.info(userSessions.toString());
	}
	
	//JS���� �޼��� ���� ��.
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {// �޽���
		logger.info("handlerTextMessage ȣ��");
		//protocol �Խ���,boardId,boardTitle(name),�ۼ���id,�����nick,��۳���
		String msg = message.getPayload();
		logger.info(msg);
		AlarmVO vo = new AlarmVO();
		// ���ۿ� ���, �� ��ۿ� ���, �߰� �ı�� ��� �Ǿ����ϴ�, �߰� �ı�� ��������, ����� ���۵Ǿ����ϴ�.
		// ���ۿ� ���
		// memberNickname, alarmCode, alarmPrefix, content, sendNickname, title, boardId
		if(!StringUtils.isEmpty(msg)) {
			String[] strs = msg.split(",");
			if (strs != null && strs.length == 7) {
				String registerNick = strs[0];
				String alarmCode = strs[1];
				String alarmPrefix = strs[2];
				String content = strs[3];
				String sendNick = strs[4];
				String title = strs[5];
				int boardId = Integer.parseInt(strs[6]);
				vo.setMemberNickname(registerNick);
				vo.setAlarmCode(alarmCode);
				vo.setAlarmPrefix(alarmPrefix);
				vo.setContent(content);
				vo.setSendNickname(sendNick);
				vo.setTitle(title);
				vo.setBoardId(boardId);
				
				if(!registerNick.equals(sendNick)) { // �޴»���� ������ ����� �ٸ����� �˶� ���
					int insertResult = alarmService.create(vo);
					logger.info(insertResult+"�� �˶� ����");

					
					if (insertResult == 1) { // �˶� ����� ������������ �˶� ����
						
						List<AlarmVO> list = alarmService.read(registerNick);
						int alarmId = list.get(0).getAlarmId();
						WebSocketSession responseIdSession = userSessions.get(registerNick);
						TextMessage tmpMsg = new TextMessage("");
						
						if(alarmCode.contains("���")) { // ��� �˶�
							int commentId = alarmService.readCommentId(alarmPrefix, alarmCode, sendNick);
							logger.info("commentId = " + commentId);
	
							int updateResult = alarmService.updateCommentId(alarmId, commentId);
							logger.info("updateResult = " + updateResult +"�� ������Ʈ �Ϸ�");
							
							tmpMsg = new TextMessage(title + "," + alarmCode + "," + sendNick + "," + content + "," + boardId + "," + alarmPrefix + "," + alarmId + "," + commentId);
								
						} else if (alarmCode.contains("���")) { // ��۾˶�
							int replyId = alarmService.readCommentId(alarmPrefix, alarmCode, sendNick);
							logger.info("replyId = " + replyId);
							
							int updateReplyResult = alarmService.updateReplyId(alarmId, replyId);
							logger.info("updateResult = " + updateReplyResult +"�� ������Ʈ �Ϸ�");
							
							int commentId = alarmService.findCommentIdByReplyId(alarmPrefix, replyId);
							logger.info("commentId = " + commentId);
							
							int updateResult = alarmService.updateCommentId(alarmId, commentId);
							logger.info("updateResult = " + updateResult +"�� ������Ʈ �Ϸ�");
						
							tmpMsg = new TextMessage(title + "," + alarmCode + "," + sendNick + "," + content + "," + boardId + "," + alarmPrefix + "," + alarmId + "," + commentId + "," + replyId);
							
						}
						
						if (responseIdSession != null) { // �޴»���� �α��� ���� �϶��� �˶� ����	
							logger.info("if responseId" + responseIdSession);
							responseIdSession.sendMessage(tmpMsg);
						}
					}
				}
			}
		} 
//		for(WebSocketSession single : sessions) {
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
//			String hsid = (String) single.getAttributes().get("memberId");
//			
//			
//			//���ǰ��� ������, �˶����� ���� ���� ���� ���� -> �α��� �� ����ڰ� ó������ �˶� �޴� ������
//			//�ش� sendMsg�� DB���� �־ üũ �ȵ� �˶� ���� �����ϱ�
//			if(single.getAttributes().get("memberId").equals(session.getAttributes().get("memberId"))) {//üũ �ȵ� �˸��鸸 ��Ƽ� View
//				List<AlarmVO> vo = new ArrayList<>();
//				vo = alarmDAO.select(hsid);
//				logger.info("vo = " + vo.toString());
//				for(AlarmVO alarm : vo) {
//					int idx = alarm.getAlarmId();
//					logger.info("idx = " + idx);
//					String prefix = alarm.getPrefix();
//					String code = alarm.getCode();
//					if(code.equals("NewPost")) {
//						code = "�亯�� ��ϵǾ����ϴ�.";
//					}
//					TextMessage sendMsg = new TextMessage("("+idx+")" + prefix + "�� " + code);
//					single.sendMessage(sendMsg);
//				}
//			}
//		}
		//protocol: ������id, �޴�id, ���� (sendId, receiveUserId, content)
		/*String msg = message.getPayload();
		logger.info(msg);
		
		if(!StringUtils.isEmpty(msg)) {
			String[] strs = msg.split(",");
			if (strs != null && strs.length == 3) {
				String sendId = strs[0];
				String receiveUserId = strs[1];
				String content = strs[2];
				
				//broadcasting
				if(receiveUserId.equals("")) {
					for (WebSocketSession sess: sessions) {
						//message�� TextMessage���·� ���� (22��°��, string x)
						sess.sendMessage(new TextMessage(receiveUserId + ":" + message.getPayload()));
					}
				} else {
					WebSocketSession responseIdSession = userSessions.get(receiveUserId);
					if (responseIdSession != null) {
						TextMessage tmpMsg = new TextMessage(sendId + "," + receiveUserId + "," + content);
						responseIdSession.sendMessage(tmpMsg);
					}
				}
				
			}
		}*/
	} // end handlerTextMassage
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {//���� ����
//		sessions.remove(session);
		userSessions.remove(session);
		logger.info("Socket ����" + session + " : " + status);
		//�� ������ ����� ������ ����Ʈ���� ����.
	}
}