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
	public List<ProductOrderVO> read() {
		logger.info("read() ȣ��  ");
		return dao.select();
	}

	@Override
	public int update(ProductOrderVO vo) {
		logger.info("update() ȣ�� : vo = " + vo.toString());
		return dao.update(vo);
	}

	@Override
	public int delete(int productOrderId) {
		logger.info("delete() ȣ�� : productOrderId = " + productOrderId);
		return dao.delete(productOrderId);
	}

}
