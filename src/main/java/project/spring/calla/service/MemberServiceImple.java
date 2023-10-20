package project.spring.calla.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.spring.calla.domain.MemberVO;
import project.spring.calla.persistence.MemberDAO;

@Service
public class MemberServiceImple implements MemberService {
	private static final Logger logger = 
			LoggerFactory.getLogger(MemberServiceImple.class);
	
	@Autowired
	private MemberDAO dao;

	@Override
	public int create(MemberVO vo) { // ����� ���� ����
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
