package project.spring.calla.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import project.spring.calla.domain.FBoardCommentVO;
import project.spring.calla.persistence.FBoardCommentDAO;
import project.spring.calla.persistence.FBoardDAO;

@Service
public class FBoardCommentServiceImple implements FBoardCommentService {
	
	private static final Logger logger = 
			LoggerFactory.getLogger(FBoardCommentServiceImple.class);
	
	@Autowired
	private FBoardCommentDAO fBoardCommentDAO;
	
	@Autowired
	private FBoardDAO fBoardDAO;
	
	@Transactional(value = "transactionManager")
	@Override
	public int create(FBoardCommentVO vo) throws Exception {
		logger.info("create() ȣ�� : vo = " + vo.toString());
		int resultInsert = fBoardCommentDAO.insert(vo);
		logger.info(resultInsert +  " �� ��� �Է� ����");
		int result = fBoardDAO.updateCommentCount(1, vo.getfBoardId());
		logger.info(result + " �� ���� ����");
		return 1;
	}

	@Override
	public List<FBoardCommentVO> read(int fBoardId) {
		logger.info("read() ȣ�� : fBoardId = " + fBoardId);
		return fBoardCommentDAO.select(fBoardId);
	}

	@Override
	public int update(int fBoardCommentId, String fBoardCommentContent) {
		logger.info("update() ȣ��");
		logger.info("fBoardCommentId = " + fBoardCommentId + ", fBoardCommentContent = " + fBoardCommentContent);
		return fBoardCommentDAO.update(fBoardCommentId, fBoardCommentContent);
	}

	@Transactional(value = "transactionManager")
	@Override
	public int delete(int fBoardCommentId, int fBoardId) throws Exception {
		logger.info("delete() ȣ�� : fBoardCommentId = " + fBoardCommentId);
		int resultDelete = fBoardCommentDAO.delete(fBoardCommentId);
		logger.info(resultDelete + " �� ���� ����");
		int result = fBoardDAO.updateCommentCount(-1, fBoardId);
		logger.info(result + "�� ���� ����");
		return 1;
	}

}
