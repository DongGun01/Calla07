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
	public QBoardVO read(int boardId) {
		logger.info("read() ȣ�� : boardId = " + boardId);
		return dao.select(boardId);
	}

	@Override
	public int update(QBoardVO vo) {
		logger.info("update() ȣ�� : vo = " + vo.toString());
		return dao.update(vo);
	}

	@Override
	public int delete(int boardId) {
		logger.info("delete() ȣ�� : boardId = " + boardId);
		return dao.delete(boardId);
	}

	@Override
	public int getTotalCounts() {
		logger.info("getTotalCounts() ȣ�� ");
		return dao.getTotalCounts();
	}

}
