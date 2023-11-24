package project.spring.calla.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import project.spring.calla.domain.QBoardCommentVO;
import project.spring.calla.pageutil.PageCriteria;
import project.spring.calla.persistence.QBoardCommentDAO;
import project.spring.calla.persistence.QBoardDAO;

@Service
public class QBoardCommentServiceImple implements QBoardCommentService {
	private static Logger logger =
			LoggerFactory.getLogger(QBoardCommentServiceImple.class);
	
	@Autowired
	private QBoardCommentDAO qBoardCommentDAO;
	
	// @Transactional
	// - �� ���� �����ͺ��̽��� ������ ���� ��,
	// ���� ������ ������ �� ���¿���, �Ʒ� ������ ������ �߻��ϸ�
	// ���� ������ rollback �Ǿ�� �Ѵ�.
	// �̷� ����� �������ִ� Spring annotation
	// - root-context.xml���� ����
	
	@Autowired
	private QBoardDAO qBoardDAO;
	
	@Transactional(value = "transactionManager")
	@Override
	public int create(QBoardCommentVO vo) throws Exception{
		// dao�� ���̺�� �ϳ�?
		// ���(test_reply)�� �����Ͱ� �����ϸ�
		// �Խ���(test_board)�� ��� ����(reply_cnt)�� ����Ǿ�� ��
		// test_reply ���̺� insert�� �����ϸ�
		// test_board ���̺� update�� �����Ѵ�.
		logger.info("create() ȣ�� : vo = " + vo.toString());
		int resultInsert = qBoardCommentDAO.insert(vo); // ��� �Է�
		logger.info(resultInsert + " �� ��� �Է� ����");
		int result = qBoardDAO.updateCommentCnt(1, vo.getqBoardId());
		logger.info(result + " �� ���� ����");
		return 1;
	}

	@Override
	public List<QBoardCommentVO> read(int qBoardId) {
		logger.info("read() ȣ�� : qBoardId = " + qBoardId);
		return qBoardCommentDAO.select(qBoardId);
	}

	@Override
	public int update(int qBoardCommentId, String qBoardCommentContent) {
		logger.info("update() ȣ��");
		logger.info("qBoardCommentId = " + qBoardCommentId + ", qBoardCommentContent = " + qBoardCommentContent);
		return qBoardCommentDAO.update(qBoardCommentId, qBoardCommentContent);
	}
	
	@Transactional(value = "transactionManager")
	@Override
	public int delete(int qBoardCommentId, int qBoardId) throws Exception{
		logger.info("delete() ȣ�� : qBoardCommentId = " + qBoardCommentId);
		int resultDelete = qBoardCommentDAO.delete(qBoardCommentId);
		logger.info(resultDelete + " �� ���� ����");
		int result = qBoardDAO.updateCommentCnt(-1, qBoardId);
		logger.info(result + "�� ���� ����");
		return 1;
	}

	@Override
	public List<QBoardCommentVO> read(PageCriteria criteria, int qBoardId) {
		logger.info("read() ȣ��");
		logger.info("start = " + criteria.getStart());
		logger.info("end = " + criteria.getEnd());
		logger.info("qBoardId = " + qBoardId);
		return qBoardCommentDAO.select(criteria, qBoardId);
	}

	@Override
	public int getTotalCounts(int qBoardId) {
		logger.info("getTotalCounts() ȣ��");
		return qBoardCommentDAO.getTotalCounts(qBoardId);
	}

}
