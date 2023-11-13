package project.spring.calla.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import project.spring.calla.domain.ProductCommentVO;
import project.spring.calla.pageutil.PageCriteria;
import project.spring.calla.persistence.ProductCommentDAO;
import project.spring.calla.persistence.ProductDAO;

@Service
public class ProductCommentServiceImple implements ProductCommentService {
	private static final Logger logger =
			LoggerFactory.getLogger(ProductCommentServiceImple.class);
	
	@Autowired
	private ProductCommentDAO productCommentDAO;
	
	@Autowired
	private ProductDAO productDAO;
	
	@Transactional(value = "transactionManager")
	@Override
	public int create(ProductCommentVO vo) throws Exception {
		logger.info("create() ȣ�� : vo = " + vo.toString());
		int resultInsert = productCommentDAO.insert(vo);
		logger.info(resultInsert + " �� ��� �Է� ����");
		int result = productDAO.updateCommentCount(1, vo.getProductId());
		logger.info(result + " �� ���� ����");
		return 1;
	}

	@Override
	public List<ProductCommentVO> read(int productId) {
		logger.info("read() ȣ�� : porductId = " + productId);
		return productCommentDAO.select(productId);
	}

	@Override
	public int update(int productCommentId, String productCommentContent) {
		logger.info("update() ȣ��");
		logger.info("productCommentId = " + productCommentId + ", productCommentContent = " + productCommentContent);
		return productCommentDAO.update(productCommentId, productCommentContent);
	}
	
	@Transactional(value = "transactionManager")
	@Override
	public int delete(int productCommentId, int productId) throws Exception {
		logger.info("delete() ȣ�� : productCommentId = " + productCommentId);
		int resultDelete = productCommentDAO.delete(productCommentId);
		logger.info(resultDelete + " �� ���� ����");
		int result = productDAO.updateCommentCount(-1, productId);
		logger.info(result + "�� ���� ����");
		return 1;
	}

	@Override
	public List<ProductCommentVO> read(PageCriteria criteria, int productId) {
		logger.info("read() ȣ��");
		logger.info("start = " + criteria.getStart());
		logger.info("end = " + criteria.getEnd());
		logger.info("productId = " + productId);
		return productCommentDAO.select(criteria, productId);
	}

	@Override
	public int getTotalCounts(int productId) {
		logger.info("getTotalCounts() ȣ��");
		return productCommentDAO.getTotalCount(productId);
	}

	@Override
	public List<ProductCommentVO> read(int productId, int productRated) {
		logger.info("read() ȣ�� : productId, productRated =" + productId, productRated);
		return productCommentDAO.select(productId, productRated);
	}


}
