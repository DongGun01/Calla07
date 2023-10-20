package project.spring.calla.persistence;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import project.spring.calla.domain.UBoardVO;
import project.spring.calla.domain.UImageVO;

@Repository // @Component
public class UBoardDAOImple implements UBoardDAO {

	private static final Logger logger = LoggerFactory.getLogger(UBoardDAOImple.class);

	private static final String NAMESPACE = "project.spring.calla.UboardMapper";

	@Autowired
	private SqlSession sqlSession;

	@Override
	public int insert(UBoardVO vo) {
		logger.info("insert() ȣ��");
		return sqlSession.insert(NAMESPACE + ".insert", vo);
		// NAMESPACE�� ������ mapper�� ã�ư��� id="insert"��
		// <insert> �±׿� vo �����͸� ����
	}

	@Override
	public void imageinsert(UImageVO vo) {
		// TODO Auto-generated method stub
		
	}

}
