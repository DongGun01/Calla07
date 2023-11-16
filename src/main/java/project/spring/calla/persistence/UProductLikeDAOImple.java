package project.spring.calla.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import project.spring.calla.domain.ProductLikeVO;
import project.spring.calla.domain.UProductLikeVO;

@Repository
public class UProductLikeDAOImple implements UProductLikeDAO {

	private static final Logger logger = LoggerFactory.getLogger(UProductLikeDAOImple.class);

	private static final String NAMESPACE = "project.spring.calla.UProductLikeMapper";

	@Autowired
	private SqlSession sqlSession;

	@Override
	public int insert(UProductLikeVO vo) {
		logger.info("insert() ȣ�� : vo = " + vo.toString());
		return sqlSession.insert(NAMESPACE + ".insert", vo);
	}

	@Override
	public int delete(int uProductId, String memberId) {
		logger.info("delete() ȣ�� : productId = " + uProductId + ",memberId = " + memberId);
		Map<String, Object> args = new HashMap();
		args.put("uProductId", uProductId);
		args.put("memberId", memberId);
		return sqlSession.delete(NAMESPACE + ".delete", args);
	}

	@Override
	public int getTotalCount(int uProductId) {
		logger.info("getTotalCounts() ȣ�� : productId = " + uProductId);
		return sqlSession.selectOne(NAMESPACE + ".total_count", uProductId);
	}

	@Override
	public int checkProductLike(int uProductId, String memberId) {
		logger.info("checkProductLike() ȣ�� : productId = " + uProductId + ", memberId = " + memberId);
		Map<String, Object> args = new HashMap<>();
		args.put("uProductId", uProductId);
		args.put("memberId", memberId);
		Integer result = sqlSession.selectOne(NAMESPACE + ".checkProductLike", args);

		if (result != null && result > 0) {
			// ���ƿ並 �� ��
			return 1;
		} else {
			// ���ƿ並 ���� ���� ��
			return 0;
		}
	}

	@Override
	public UProductLikeVO select(int uProductId, String memberId) {
		logger.info("select() ȣ�� : productId = " + uProductId + ",memberId = " + memberId);
		Map<String, Object> args = new HashMap();
		args.put("uProductId", uProductId);
		args.put("memberId", memberId);
		return sqlSession.selectOne(NAMESPACE + ".select_by_member_id", args);
	}

	@Override
	public int deleteById(int uProductLikeId) {
		logger.info("delete() ȣ�� : productLikeId = " + uProductLikeId);
		return sqlSession.delete(NAMESPACE + ".delete_by_id", uProductLikeId);
	}

}
