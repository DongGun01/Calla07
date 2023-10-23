package project.spring.calla.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.spring.calla.domain.FBoardReplyVO;
import project.spring.calla.persistence.FBoardReplyDAO;

@Service
public class FBoardReplyServiceImple implements FBoardReplyService {

	
	private static final Logger logger = 
			LoggerFactory.getLogger(FBoardReplyServiceImple.class);
	
	@Autowired
	private FBoardReplyDAO fBoardReplyDAO;
	
	@Override
	public int create(FBoardReplyVO vo) throws Exception {
		logger.info("create() ȣ�� : vo = " + vo.toString());
		int resultInsert = fBoardReplyDAO.insert(vo);
		logger.info(resultInsert +  " �� ��� �Է� ����");
		return 1;
	}

	@Override
	public List<FBoardReplyVO> read(int fBoardCommentId) {
		logger.info("read() ȣ�� : fBoardCommentId = " + fBoardCommentId);
		return fBoardReplyDAO.select(fBoardCommentId);
	}

	@Override
	public int update(int fBoardReplyId, String fBoardReplyContent) {
		logger.info("update() ȣ��");
		logger.info("fBoardReplyId = " + fBoardReplyId + ", fBoardReplyContent = " + fBoardReplyContent);
		return fBoardReplyDAO.update(fBoardReplyId, fBoardReplyContent);
	}

	@Override
	public int delete(int fBoardReplyId) throws Exception {
		logger.info("delete() ȣ�� : fBoardReplyId = " + fBoardReplyId);
		int resultDelete = fBoardReplyDAO.delete(fBoardReplyId);
		logger.info(resultDelete + " �� ���� ����");
		return 1;
	}

}
