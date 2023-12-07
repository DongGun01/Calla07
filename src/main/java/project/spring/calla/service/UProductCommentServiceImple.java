package project.spring.calla.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import project.spring.calla.domain.UProductCommentVO;
import project.spring.calla.domain.UProductVO;
import project.spring.calla.pageutil.PageCriteria;
import project.spring.calla.persistence.FBoardCommentDAO;
import project.spring.calla.persistence.FBoardDAO;
import project.spring.calla.persistence.UProductCommentDAO;
import project.spring.calla.persistence.UProductDAO;

@Service
public class UProductCommentServiceImple implements UProductCommentService {

	private static final Logger logger = LoggerFactory.getLogger(UProductCommentServiceImple.class);

	@Autowired
	private UProductCommentDAO uProductCommentDAO;

	@Autowired
	private UProductDAO uProductDAO;

	@Transactional(value = "transactionManager")
	@Override
	public int create(UProductCommentVO vo) throws Exception {
		logger.info("create() ȣ�� : vo = " + vo.toString());
		int resultInsert = uProductCommentDAO.insert(vo);
		logger.info(resultInsert + " �� ��� �Է� ����");
		int result = uProductDAO.updateUproductCommentCount(1, vo.getuProductId());
		logger.info(result + " �� ���� ����");
		return 1;
	}
	
	@Override
	public UProductCommentVO selectvo(int uProductId) {
		logger.info("read() ȣ�� : boardId = " + uProductId);
		return uProductCommentDAO.selectvo(uProductId);
	}

	@Transactional(value = "transactionManager")
	@Override
	public List<UProductCommentVO> read(int uProductId, HttpSession session) {
		logger.info("read() ȣ�� : uProductId = " + uProductId);
		UProductVO product = uProductDAO.select(uProductId);
		

		String memberNickname = (String) session.getAttribute("memberNickname");

		List<UProductCommentVO> items = uProductCommentDAO.select(uProductId);
		
		logger.info("list ȣ�� : " + items);
		

		for (UProductCommentVO vo : items) {
			if(memberNickname == null) { // �α��� �ȵ� ����
				vo.setuProductCommentContent("��� ����Դϴ�");
			} else { 
				String commenter = vo.getMemberNickname();
				String writer = product.getMemberNickname();
				if(!memberNickname.equals(commenter) && !memberNickname.equals(writer)) { // �� �ۼ��ڰ� �ƴϰų� ���� �Ǹ��ڰ� �ƴ� ���
					if(vo.getuProductSecretComment().equals("y")) // ��д�� ǥ�ø� ���� ���
					vo.setuProductCommentContent("��� ����Դϴ�");
				}
			}
		}
		
		return items;
	}

	@Override
	public int update(int uProductCommentId, String uProductCommentContent) {
		logger.info("update() ȣ��");
		logger.info("fBoardCommentId = " + uProductCommentId + ", fBoardCommentContent = " + uProductCommentContent);
		return uProductCommentDAO.update(uProductCommentId, uProductCommentContent);
	}

	@Transactional(value = "transactionManager")
	@Override
	public int delete(int uProductCommentId, int uProductId) throws Exception {
		logger.info("delete() ȣ�� : uProductCommentId = " + uProductCommentId);
		int resultDelete = uProductCommentDAO.delete(uProductCommentId);
		logger.info(resultDelete + " �� ���� ����");
		int result = uProductDAO.updateUproductCommentCount(-1, uProductId);
		logger.info(result + "�� ���� ����");
		return 1;
	}

	@Override
	public List<UProductCommentVO> read(int uProductId) {
		logger.info("read() ȣ�� : uProductId = " + uProductId);
		return uProductCommentDAO.selected(uProductId);
	}

	

}
