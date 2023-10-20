package project.spring.calla.persistence;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import project.spring.calla.domain.MemberVO;

@Repository
public class MemberDAOImple implements MemberDAO{

	
	private static final Logger logger=
			LoggerFactory.getLogger(MemberDAOImple.class);
	
	private static final String NAMESPACE = 
			"project.spring.calla.Membermapper"; // ���߿� ���� �κи� ����
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public int checkId(String memberId) { // ���̵� �ߺ�üũ
		logger.info("select_by_id() ȣ��");
		int result  = sqlSession.selectOne(NAMESPACE + ".select_by_id", memberId);
		logger.info(result+"�ߺ�");
		return result;
	}

	@Override
	public int checkNickname(String memberNickname) { // �г��� �ߺ�üũ
		logger.info("select_by_nickname() ȣ��");
		int result = sqlSession.selectOne(NAMESPACE + ".select_by_nickname", memberNickname);
		logger.info(result+"�ߺ�");
		return result;
	}

	@Override
	public int insert(MemberVO vo) { // ȸ������ ���
		logger.info("insert() ȣ�� : vo = " + vo.toString());
		return sqlSession.insert(NAMESPACE + ".insert", vo);
	}
	
	
}
