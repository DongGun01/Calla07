package project.spring.calla.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.spring.calla.domain.UProductReplyVO;
import project.spring.calla.persistence.UProductReplyDAO;

@Service
public class UProductReplyServiceImple implements UProductReplyService{
	private static final Logger logger = 
			LoggerFactory.getLogger(UProductReplyServiceImple.class);
	
	@Autowired
	private UProductReplyDAO uproductReplyDAO;

	@Override
	public int create(UProductReplyVO vo) throws Exception {
		logger.info("create() ȣ�� : vo = " + vo.toString());;
		int resultInsert = uproductReplyDAO.insert(vo);
		logger.info(resultInsert + " �� ��� �Է� ����");
		return 1;
	}

	@Override
	public List<UProductReplyVO> read(int uProductCommentId) {
		logger.info("read() ȣ�� : uProductCommentId = " + uProductCommentId);
		return uproductReplyDAO.select(uProductCommentId);
	}
	

	@Override
	public int update(int uProductReplyId, String uProductReplyContent) {
		logger.info("update() ȣ��");
		logger.info("uProductReplyId = " + uProductReplyId + ", uProductReplyContent = " + uProductReplyContent);
		return uproductReplyDAO.update(uProductReplyId, uProductReplyContent);
	}

	@Override
	public int delete(int uProductReplyId) throws Exception {
		logger.info("delete() ȣ�� : uProductReplyId = " + uProductReplyId);
		int resultDelete = uproductReplyDAO.delete(uProductReplyId);
		logger.info(resultDelete + " �� ���� ����");
		return 1;
	}




	

}
