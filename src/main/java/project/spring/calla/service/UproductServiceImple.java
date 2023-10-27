package project.spring.calla.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.spring.calla.domain.ProductVO;
import project.spring.calla.domain.UproductVO;
import project.spring.calla.pageutil.PageCriteria;
import project.spring.calla.persistence.ProductDAO;
import project.spring.calla.persistence.UproductDAO;

@Service
public class UproductServiceImple implements UproductService {
	private static final Logger logger = 
			LoggerFactory.getLogger(UproductServiceImple.class);
	
	@Autowired
	private UproductDAO dao;
	
	@Override
	public int create(UproductVO vo) {
		logger.info("create() ȣ�� : vo = " + vo.toString());
		return dao.insert(vo);
	}

	@Override
	public List<UproductVO> read(PageCriteria criteria) {
		logger.info("read() ȣ��");
		logger.info("start = " + criteria.getStart());
		logger.info("end = " + criteria.getEnd());
		return dao.select(criteria);
	}

	@Override
	public UproductVO read(int uProductId) {
		logger.info("read() ȣ�� : boardId = " + uProductId);
		return dao.select(uProductId);
	}

	@Override
	public int update(UproductVO vo) {
		logger.info("update() ȣ�� : vo = " + vo.toString());
		return dao.update(vo);
	}

	@Override
	public int delete(int uProductId) {
		logger.info("delete() ȣ�� : uProductId = " + uProductId);
		return dao.delete(uProductId);
	}

	@Override
	public int getTotalCounts() {
		logger.info("getTotalCounts() ȣ��");
		return dao.getTotalCount();
	}

	@Override
	public List<UproductVO> readByCategoriorName(PageCriteria criteria, String keyword) {
		logger.info("readByTitleOrContent() ȣ��");
		logger.info("start = " + criteria.getStart());
		logger.info("end = " + criteria.getEnd());
		logger.info("keyword = " + keyword);
		
		return dao.selectByCategoriorName(criteria, keyword);
	}

	@Override
	public int getTotalCountsByByCategoriorName(String keyword) {
		logger.info("getTotalCountsByTitleContent() ȣ��");
		return dao.getTotalCountsByCategoriorName(keyword);
	}

	@Override
	public List<UproductVO> readdate(PageCriteria criteria) {
		logger.info("readdate() ȣ��");
		logger.info("start = " + criteria.getStart());
		logger.info("end = " + criteria.getEnd());
		
		return dao.selectByUproductCreatedDate(criteria);
		
	}

	@Override
	public int getTotalCountsBydate() {
		logger.info("getTotalCountsBydate() ȣ��");
		return dao.getTotalCountsByUproductCreatedDate();
	}

	

}
