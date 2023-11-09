package project.spring.calla.persistence;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import project.spring.calla.domain.UProductLikeVO;

@Repository
public class UProductLikeDAOImple implements UProductLikeDAO{
	
	private static final Logger logger =
			LoggerFactory.getLogger(UProductLikeDAOImple.class);
	
	private static final String NAMESPACE = 
			"project.spring.calla.UProductLikeMapper";
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public int insert(UProductLikeVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<UProductLikeVO> select(String memberNickname) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int delete(int uProductLikeId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteById(int uProductLikeId) {
		logger.info("delete() ȣ�� : productLikeId = " + uProductLikeId);
		return sqlSession.delete(NAMESPACE + ".delete_by_id", uProductLikeId);
	}

}
