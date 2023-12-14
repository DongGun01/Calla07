package project.spring.calla.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.spring.calla.domain.MemberVO;
import project.spring.calla.domain.UProductMannerDownVO;
import project.spring.calla.domain.UProductMannerVO;
import project.spring.calla.domain.UProductReviewVO;
import project.spring.calla.domain.UProductVO;
import project.spring.calla.persistence.UProductReviewDAO;
import project.spring.calla.util.PageCriteria;

@Service
public class UProductReviewServiceImple implements UProductReviewService {
	private static final Logger logger = LoggerFactory.getLogger(UProductReviewServiceImple.class);

	@Autowired
	private UProductReviewDAO dao;

	@Override
	public int create(UProductReviewVO vo) throws Exception {
		logger.info("create() ȣ�� : vo = " + vo.toString());
		return dao.insert(vo);
	}
	
	@Override
	public UProductReviewVO read(int uProductReviewId) {
		logger.info("read() ȣ�� : boardId = " + uProductReviewId);
		return dao.select(uProductReviewId);
	}

	@Override
	public List<UProductReviewVO> read(PageCriteria criteria) {
		logger.info("read() ȣ��");
		logger.info("start = " + criteria.getStart());
		logger.info("end = " + criteria.getEnd());
		return dao.select(criteria);
	}

	@Override
	public int getTotalCounts() {
		logger.info("getTotalCounts() ȣ��");
		return dao.getTotalCount();
	}

	@Override
	public List<UProductReviewVO> readysellNickname(PageCriteria criteria, String sellerNickname) {
		logger.info("readybuyuproduct() ȣ��");
		logger.info("start = " + criteria.getStart());
		logger.info("end = " + criteria.getEnd());
		logger.info("sellerNickname = " + sellerNickname);

		return dao.selectbySellnickname(criteria, sellerNickname);
	}

	@Override
	public int getTotalCountssellNickname(String sellerNickname) {
		logger.info("getTotalCountsbuyuproduct() ȣ��");
		return dao.getTotalCountbySellnickname(sellerNickname);
	}

	@Override
	public UProductReviewVO read(String sellerNickname) {
		logger.info("read() ȣ�� : sellerNickname = " + sellerNickname);
		return dao.select(sellerNickname);
	}

	@Override
	public UProductVO readnickname(String sellerNickname) {
		logger.info("read() ȣ�� : sellerNickname = " + sellerNickname);
		return dao.selectnickname(sellerNickname);
	}

	@Override
	public MemberVO readMembermanner(String memberNickname) {
		logger.info("read() ȣ�� : memberNickname = " + memberNickname);
		return dao.selectmemberManner(memberNickname);
	}
	
	@Override
	public float updatememberManner(String memberNickname) {
		logger.info("updatememberManner() ȣ�� : memberNickname = " + memberNickname);
		return dao.updatememberManner(memberNickname);
	}

	@Override
	public float updatememberManners(String memberNickname) {
		logger.info("updatememberManner() ȣ�� : memberNickname = " + memberNickname);
		return dao.updatememberManners(memberNickname);
	}
	
	@Override
	public int insertmanner(UProductMannerVO vo) {
		logger.info("updatememberManner() ȣ�� : vo = " + vo);
		return dao.insertmanner(vo);
	}

	@Override
	public int count(int uProductId) {
		logger.info("count() ȣ�� : uProductId = " + uProductId);
		return dao.count(uProductId);
	}

	@Override
	public int insertmannerdown(UProductMannerDownVO vo) {
		logger.info("updatememberManner() ȣ�� : vo = " + vo);
		return dao.insertmannerdown(vo);
	}

	@Override
	public int countmannerdown(int uProductId) {
		logger.info("count() ȣ�� : uProductId = " + uProductId);
		return dao.countmannerdown(uProductId);
	}

	

	

	

}
