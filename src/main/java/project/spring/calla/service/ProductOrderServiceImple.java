package project.spring.calla.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.spring.calla.domain.ProductOrderVO;
import project.spring.calla.persistence.ProductOrderDAO;

@Service
public class ProductOrderServiceImple implements ProductOrderService {
	
	private static final Logger logger = 
			LoggerFactory.getLogger(ProductOrderServiceImple.class);
	
	@Autowired
	private ProductOrderDAO dao;
	
	@Override
	public int create(ProductOrderVO vo) {
		logger.info("create() ȣ�� : vo = " + vo.toString());
		return dao.insert(vo);
	}

	@Override
	public int update(int productOrderId, String recipientName, String memberAddress) {
		logger.info("update() ȣ�� ");
		return dao.update(productOrderId, recipientName, memberAddress);
	}

	@Override
	public int delete(int productOrderId) {
		logger.info("delete() ȣ�� : productOrderId = " + productOrderId);
		return dao.delete(productOrderId);
	}

	@Override
	public ProductOrderVO readBy(String memberId) {
		logger.info("read() ȣ�� : memberId = " + memberId);
		return dao.selectBy(memberId);
	}

//	@Override
//	public int create(int productId, String productName, int productPrice, int productAmount, String memberId) {
//		logger.info("create() ȣ�� :" + productId, productName, productPrice, productAmount, memberId);
//		return dao.insert(productId, productName, productPrice, productAmount, memberId);
//	}


	@Override
	public List<ProductOrderVO> read(String memberId) {
		logger.info("read() ȣ�� : memberId = " + memberId);
		return dao.select(memberId);
	}

	@Override
	public int updateDeliveryStatus(int productOrderId, String deliveryStatus) {
		logger.info("updateDeliveryStatus() ȣ�� ");
		return dao.updateDeliveryStatus(productOrderId, deliveryStatus);
	}


	@Override
	public List<ProductOrderVO> read() {
		logger.info("read() ȣ��  ");
		return dao.select();
	}

	@Override
	public int updateStatus(int productOrderId, String newDeliveryStatus) {
		logger.info("updateStatus() ȣ��");
		return dao.updateStatus(productOrderId, newDeliveryStatus);
	}

}
