package project.spring.calla.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.spring.calla.domain.FBoardVO;
import project.spring.calla.pageutil.PageCriteria;
import project.spring.calla.persistence.FBoardDAO;

@Service // @Component
// * ���� ����(Service/Business Layer)
// - ǥ�� ����(Presentation Layer)�� ���� ����(Persistence Layer)���̸� 
//	 �����Ͽ� �� ������ ���������� ������� �ʵ��� �ϴ� ����
// - Ʈ�����(transaction) ����
// - DB�� ������� �����͸� ó�� ����

public class FBoardServiceImple implements FBoardService{
	private static final Logger logger =
			LoggerFactory.getLogger(FBoardServiceImple.class);
	
	@Autowired
	private FBoardDAO dao;
	
	@Override
	public int create(FBoardVO vo) {
		logger.info("create() ȣ�� : vo = " + vo.toString());
		return dao.insert(vo);
	}

	@Override
	public List<FBoardVO> read(PageCriteria criteria) {
		logger.info("read() ȣ��");
		logger.info("start = " + criteria.getStart());
		logger.info("end = " + criteria.getEnd());

		return dao.select(criteria);
	}
	
	@Override
	public int getTotalCounts() {
		logger.info("getTotalCounts() ȣ��");
		return dao.getTotalCounts();
	}
	

	@Override
	public List<FBoardVO> readByMemberNickname(PageCriteria criteria, String keyword) {
		logger.info("readByMemberNickname() ȣ��");
		logger.info("start = " + criteria.getStart());
		logger.info("end = " + criteria.getEnd());
		logger.info("memberNickname = " + keyword);
		
		return dao.selectByMemberNickname(criteria, keyword);
	}

	@Override
	public int getTotalCountsByMemberNickname(String keyword) {
		logger.info("getTotalCountsByMemberNickname() ȣ��");
		return dao.getTotalCountsByMemberNickname(keyword);
	}

	@Override
	public List<FBoardVO> readByTitleOrContent(PageCriteria criteria, String keyword) {
		logger.info("readByTitleOrContent() ȣ��");
		logger.info("start = " + criteria.getStart());
		logger.info("end = " + criteria.getEnd());
		logger.info("keyword = " + keyword);
		
		return dao.selectByTitleOrContent(criteria, keyword);
	}
	
	@Override
	public int getTotalCountsByTitleContent(String keyword) {
		logger.info("getTotalCountsByTitleContent() ȣ��");
		return dao.getTotalCountsByTitleContent(keyword);
	}

	@Override
	public FBoardVO read(int fBoardId) {
		logger.info("read() ȣ�� : fBoardId = " + fBoardId);
		
		return dao.select(fBoardId);
	}

	@Override
	public int update(FBoardVO vo) {
		logger.info("update() ȣ�� : vo = " + vo.toString());
		return dao.update(vo);
	}

	@Override
	public int delete(int fBoardId) {
		logger.info("delete() ȣ�� : fBoardId = " + fBoardId);
		return dao.delete(fBoardId);
	}



	@Override
	public int updateViews(int views, int fBoardId) {
		logger.info("updateViews() ȣ��");
		return dao.updateViews(views, fBoardId);
	}





}
