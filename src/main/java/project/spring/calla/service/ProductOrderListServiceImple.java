package project.spring.calla.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.spring.calla.domain.ProductOrderListVO;
import project.spring.calla.persistence.ProductOrderListDAO;

@Service
public class ProductOrderListServiceImple implements ProductOrderListService {
	
	private static final Logger logger = 
			LoggerFactory.getLogger(ProductOrderListServiceImple.class);
	
	@Autowired
	private ProductOrderListDAO dao;
	
	@Override
	public int create(ProductOrderListVO vo) {
		logger.info("create() ȣ�� : vo = " + vo.toString());
		return dao.insert(vo);
	}

	@Override
	public List<ProductOrderListVO> read() {
		logger.info("read() ȣ��  ");
		return dao.select();
	}

	@Override
	public int update(int productId, String memberId, int productAmount) {
		logger.info("update() ȣ�� ");
		return dao.update(productId, memberId, productAmount);
	}

	@Override
	public int delete(int productId, String memberId) {
		logger.info("delete() ȣ�� : productId = " + productId + ", memberId = " + memberId);
		return dao.delete(productId, memberId);
	}

	@Override
	public ProductOrderListVO read(int productId, String memberId) {
		logger.info("read() ȣ�� : memberId = " + memberId);
		return dao.select(productId, memberId);
	}

	@Override
	public List<ProductOrderListVO> readBy(String memberId) {
		logger.info("readBy() ȣ�� : " + memberId);
		return dao.selectBy(memberId);
	}

}
