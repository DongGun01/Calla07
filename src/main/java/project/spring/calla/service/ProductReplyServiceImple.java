package project.spring.calla.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.spring.calla.domain.ProductReplyVO;
import project.spring.calla.persistence.ProductReplyDAO;

@Service
public class ProductReplyServiceImple implements ProductReplyService {
	
	private static final Logger logger =
			LoggerFactory.getLogger(ProductReplyServiceImple.class);
	
	@Autowired
	private ProductReplyDAO productReplyDAO;
	
	@Override
	public int create(ProductReplyVO vo) throws Exception {
		logger.info("create() ȣ�� : vo = " + vo.toString());;
		int resultInsert = productReplyDAO.insert(vo);
		logger.info(resultInsert + " �� ��� �Է� ����");
		return 1;
	}

	@Override
	public List<ProductReplyVO> read(int productCommentId) {
		logger.info("read() ȣ�� : productCommentId = " + productCommentId);
		return productReplyDAO.select(productCommentId);
	}

	@Override
	public int update(int productReplyId, String productReplyContent) {
		logger.info("update() ȣ��");
		logger.info("productReplyId = " + productReplyId + ", productReplyContent = " + productReplyContent);
		return productReplyDAO.update(productReplyId, productReplyContent);
	}

	@Override
	public int delete(int productReplyId) throws Exception {
		logger.info("delete() ȣ�� : productReplyId = " + productReplyId);
		int resultDelete = productReplyDAO.delete(productReplyId);
		logger.info(resultDelete + " �� ���� ����");
		return 1;
	}

}
