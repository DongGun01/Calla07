package project.spring.calla.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.spring.calla.domain.QBoardCommentVO;
import project.spring.calla.domain.QBoardReplyVO;
import project.spring.calla.persistence.QBoardReplyDAO;

@Service
public class QBoardReplyServiceImple implements QBoardReplyService {

	private static final Logger logger =
			LoggerFactory.getLogger(QBoardReplyServiceImple.class);
	
	@Autowired
	private QBoardReplyDAO qBoardReplyDAO;

	@Override // ���� ����
	public int create(QBoardReplyVO vo) throws Exception {
		logger.info("create() ȣ�� vo : " + vo.toString());
		int result = qBoardReplyDAO.insert(vo);
		return result;
	}

	@Override // ���� �ҷ�����
	public List<QBoardReplyVO> read(int qBoardCommentId) {
		logger.info("read ȣ�� qBoardCommentId : " + qBoardCommentId);
		return qBoardReplyDAO.select(qBoardCommentId);
	}

	@Override // ����
	public int update(int qBoardReplyId, String qBoardReplyContent) {
		logger.info("update() ȣ��");
		logger.info("qBoardReplyId : " + qBoardReplyId + ", qBoardReplyContent : " + qBoardReplyContent);
		return qBoardReplyDAO.update(qBoardReplyId, qBoardReplyContent);
	}

	@Override // ����
	public int delete(int qBoardReplyId) throws Exception {
		logger.info("delete() ȣ��");
		int result = qBoardReplyDAO.delete(qBoardReplyId);
		return result;
	}
	


}
