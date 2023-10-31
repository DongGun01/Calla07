package project.spring.calla.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import project.spring.calla.domain.QBoardCommentVO;
import project.spring.calla.domain.UProductCommentVO;

@Repository
public class QBoardCommentDAOImple implements QBoardCommentDAO{

	private static final Logger logger =
			LoggerFactory.getLogger(QBoardCommentDAOImple.class);

	private static final String NAMESPACE = 
			"project.spring.calla.QNABoardCommentMapper";
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public int insert(QBoardCommentVO vo) {
		logger.info("insert() ȣ�� : vo = " + vo.toString());
		return sqlSession.insert(NAMESPACE + ".insert", vo);
	}

	@Override
	public List<QBoardCommentVO> select(int qBoardId) {
		logger.info("select() ȣ�� : qBoardId = " + qBoardId);
		return sqlSession.selectList(NAMESPACE + ".select_all_by_qboard_id", qBoardId);
	}

	@Override
	public int update(int qBoardCommentId, String qBoardCommentContent) {
		logger.info("update() ȣ��");
		logger.info("qBoardCommentId = " + qBoardCommentId + ", qBoardCommentContent = " + qBoardCommentContent);
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("qBoardCommentId", qBoardCommentId);
		args.put("qBoardCommentContent", qBoardCommentContent);
		return sqlSession.update(NAMESPACE + ".update", args);
	}

	@Override
	public int delete(int qBoardCommentId) {
		logger.info("delete() ȣ�� : qBoardCommentId = " + qBoardCommentId);
		return sqlSession.delete(NAMESPACE + ".delete", qBoardCommentId);
	}
	
	@Override
	public List<QBoardCommentVO> select(String memberNickname) {
		logger.info("select(memberNickname) ȣ�� memberNickname = " + memberNickname);
		return sqlSession.selectList(NAMESPACE + ".select_by_memberNickname", memberNickname);
	}
}
