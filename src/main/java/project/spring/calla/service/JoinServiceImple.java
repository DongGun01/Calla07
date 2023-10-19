package project.spring.calla.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.spring.calla.domain.memberVO;
import project.spring.calla.persistence.JoinDAO;

@Service
public class JoinServiceImple implements JoinService {
	private static final Logger logger = 
			LoggerFactory.getLogger(JoinServiceImple.class);
	
	@Autowired
	private JoinDAO dao;

	@Override
	public int create(memberVO vo) { // ����� ���� ����
		logger.info("create() ȣ�� : vo = " + vo.toString());
		int resultInsert = dao.insert(vo); // ���� �Է�? ����?
		logger.info(resultInsert + "�� ��������");
		return 1;
		
	}
	
	@Override
	public int checkId(String memberId) { // ���̵� �ߺ�Ȯ��
		int result = 0;
        result = dao.checkId(memberId);
		return result;
	}
	
	@Override
	public int checkNick(String memberNickname) { // �г��� �ߺ�Ȯ��
		int result = 0;
        result = dao.checkNickname(memberNickname);
		return result;
	}
	  
	 
	   
}
