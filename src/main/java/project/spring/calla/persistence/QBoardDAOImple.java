package project.spring.calla.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import project.spring.calla.domain.FBoardVO;
import project.spring.calla.domain.QBoardVO;
import project.spring.calla.pageutil.PageCriteria;


@Repository
public class QBoardDAOImple implements QBoardDAO {

	
	private static final Logger logger = 
				LoggerFactory.getLogger(QBoardDAOImple.class);
		
	private static final String NAMESPACE = 
				"project.spring.calla.QNABoardMapper";
	
	// MyBatis�� SqlSession�� ����ϱ� ����
	// ������ �����ӿ�ũ�� ������ bean�� ����(injection)����
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public int insert(QBoardVO vo) {
		logger.info("insert() ȣ�� : vo = " + vo.toString());
		// TODO Auto-generated method stub
		return sqlSession.insert(NAMESPACE + ".insert", vo); 
		// NAMESPACR�� ������ mapper�� ã�ư��� id="insert"��
		// <insert> �±׿� vo �����͸� ����
	}

	@Override
	public List<QBoardVO> select() {
		logger.info("select All() ȣ��");
		return sqlSession.selectList(NAMESPACE + ".select_all"); 
	}

	@Override
	public QBoardVO select(int qBoardId) {
		logger.info("select() ȣ�� : boardId = " + qBoardId);
		return sqlSession.selectOne(NAMESPACE + ".select_by_board_id", qBoardId);
	}

	@Override
	public int update(QBoardVO vo) {
		logger.info("update() ȣ�� : vo = " + vo.toString());
		return sqlSession.update(NAMESPACE + ".update", vo);
	}

	@Override
	public int delete(int qBoardId) {
		logger.info("delete() ȣ�� : boardId = " + qBoardId);
		return sqlSession.delete(NAMESPACE + ".delete", qBoardId);
	}

	@Override
	public List<QBoardVO> select(PageCriteria criteria) {
		logger.info("select() ȣ��");
		logger.info("start = " + criteria.getStart());
		logger.info("end = " + criteria.getEnd());
		return sqlSession.selectList(NAMESPACE + ".paging", criteria);
	}

	@Override
	public int getTotalCounts() {
		logger.info("getTotalCounts() ȣ��");
		return sqlSession.selectOne(NAMESPACE + ".total_count");
	}

	@Override
	public List<QBoardVO> select(String memberNickname) {
		logger.info("select() ȣ�� : memberId = " + memberNickname);
		return sqlSession.selectList(NAMESPACE + ".select_by_memberNickname", "%" + memberNickname + "%");
	}

	@Override
	public List<QBoardVO> selectByTitleOrContent(String keyword) {
		logger.info("selectByTitleOrContent() ȣ��");
		return sqlSession.selectList(NAMESPACE + ".select_by_title_content", "%" + keyword + "%");
		
	}

	@Override
	public int updateCommentCnt(int amount, int qBoardId) {
		logger.info("updateCommentCnt() : qBoardId = " + qBoardId);
		Map<String, Integer> args = new HashMap();
		args.put("amount", amount);
		args.put("qBoardId", qBoardId);
		return sqlSession.update(NAMESPACE + ".update_comment_count", args); // ���� ���߿� ����
	}
	
	@Override
	public List<QBoardVO> selectAllByMemberNickname(String memberNickname) {
		logger.info("selectAllByMemberNickname() ȣ�� memberNickname = " + memberNickname);
		return sqlSession.selectList(NAMESPACE + ".select_all_by_memberNickname", memberNickname);
	}

	@Override
	public int updateViews(int views, int qBoardId) {
		logger.info("updateViews() : qBoardId = " + qBoardId);
		Map<String, Integer> args = new HashMap();
		args.put("views", views);
		args.put("qBoardId", qBoardId);
		return sqlSession.update(NAMESPACE + ".update_views", args);
	}

}
