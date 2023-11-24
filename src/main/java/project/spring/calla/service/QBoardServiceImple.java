package project.spring.calla.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.spring.calla.domain.QBoardVO;
import project.spring.calla.pageutil.PageCriteria;
import project.spring.calla.persistence.QBoardDAO;

@Service // @Component
// * ���� ����(Service/Business Layer)
// - ǥ�� ����(Presentation Layer)�� ���� ����(persistence Layer)���̸�
//	 �����Ͽ� �� ������ ���������� ������� �ʵ��� �ϴ� ����
// - Ʈ�����(transaction) ����
// - DB�� ������� �����͸� ó�� ����
// ���̺�� dao�� �ϳ��� �����
public class QBoardServiceImple implements QBoardService{
	private static final Logger logger = 
			LoggerFactory.getLogger(QBoardServiceImple.class);
	
	@Autowired
	private QBoardDAO dao;
	
	@Override
	public int create(QBoardVO vo) {
		logger.info("create() ȣ�� : vo = " + vo.toString());
		return dao.insert(vo);
	}

	@Override
	public List<QBoardVO> read(PageCriteria criteria) {
		logger.info("read() ȣ��");
		logger.info("start = " + criteria.getStart());
		logger.info("end = " + criteria.getEnd());
		return dao.select(criteria);
	}

	@Override
	public QBoardVO read(int qBoardId) {
		logger.info("read() ȣ�� : boardId = " + qBoardId + ", ");
		return dao.select(qBoardId);
	}

	@Override
	public int update(QBoardVO vo) {
		logger.info("update() ȣ�� : vo = " + vo.toString());
		return dao.update(vo);
	}

	@Override
	public int delete(int qBoardId) {
		logger.info("delete() ȣ�� : qBoardId = " + qBoardId);
		return dao.delete(qBoardId);
	}

	@Override
	public int getTotalCounts() {
		logger.info("getTotalCounts() ȣ�� ");
		return dao.getTotalCounts();
	}

	@Override
	public int updateViews(int views, int qBoardId) {
		logger.info("updateViews() ȣ��");
		return dao.updateViews(views, qBoardId);
	}

	@Override
	public List<QBoardVO> readBymemberNickname(PageCriteria criteria, String keyword) {
		logger.info("readByMemberNickname() ȣ��");
		return dao.selectByMemberNickname(criteria, keyword);
	}

	@Override
	public int getTotalCountsByMeberNickname(String keyword) {
		logger.info("getTotalCountsByMeberNickname() ȣ��" );
		return dao.getTotalCountsByMemberNickname(keyword);
	}

	@Override
	public List<QBoardVO> readByTitle(PageCriteria criteria, String keyword) {
		logger.info("readByTitle() ȣ��");
		return dao.selectByTitle(criteria, keyword);
	}

	@Override
	public int getTotalCountsByTitle(String keyword) {
		logger.info("getTotalCountsByTitle() ȣ��");
		return dao.getTotalCountsByTitle(keyword);
	}

}
