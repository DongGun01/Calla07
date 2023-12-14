package project.spring.calla.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.spring.calla.domain.UProductCommentVO;
import project.spring.calla.domain.UProductReplyVO;
import project.spring.calla.domain.UProductVO;
import project.spring.calla.persistence.UProductCommentDAO;
import project.spring.calla.persistence.UProductDAO;
import project.spring.calla.persistence.UProductReplyDAO;

@Service
public class UProductReplyServiceImple implements UProductReplyService{
	private static final Logger logger = 
			LoggerFactory.getLogger(UProductReplyServiceImple.class);
	
	@Autowired
	private UProductReplyDAO uproductReplyDAO;
	
	@Autowired
	private UProductDAO uProductDAO;
	
	@Autowired
	private UProductCommentDAO uProductCommentDAO;

	@Override
	public int create(UProductReplyVO vo) throws Exception {
		logger.info("create() ȣ�� : vo = " + vo.toString());;
		int resultInsert = uproductReplyDAO.insert(vo);
		logger.info(resultInsert + " �� ��� �Է� ����");
		return 1;
	}

	@Override
	public List<UProductReplyVO> read(int uProductCommentId, HttpSession session) {
		logger.info("read() ȣ�� : uProductCommentId = " + uProductCommentId);
		UProductCommentVO product = uProductCommentDAO.selectcomment(uProductCommentId); 
		
		UProductVO uproduct = uProductDAO.select(product.getuProductId());

		String memberNickname = (String) session.getAttribute("memberNickname");

		List<UProductReplyVO> items = uproductReplyDAO.select(uProductCommentId);
		
		logger.info("list ȣ�� : " + items);
		

		for (UProductReplyVO vo : items) {
			if(memberNickname == null) { // �α��� �ȵ� ����
				vo.setuProductReplyContent("��� ����Դϴ�");
			} else { 
				String commenter = product.getMemberNickname(); // ��� �ۼ���
				String writer = uproduct.getMemberNickname(); // ���� �Ǹ��� �г���
				if(!memberNickname.equals(commenter) && !memberNickname.equals(writer)) { // ��� �ۼ��ڰ� �ƴϰų� ���� �Ǹ��ڰ� �ƴ� ���
					vo.setuProductReplyContent("��� ����Դϴ�");
				}
			}
		}
		
		return items;
	}
	

	@Override
	public int update(int uProductReplyId, String uProductReplyContent) {
		logger.info("update() ȣ��");
		logger.info("uProductReplyId = " + uProductReplyId + ", uProductReplyContent = " + uProductReplyContent);
		return uproductReplyDAO.update(uProductReplyId, uProductReplyContent);
	}

	@Override
	public int delete(int uProductReplyId) throws Exception {
		logger.info("delete() ȣ�� : uProductReplyId = " + uProductReplyId);
		int resultDelete = uproductReplyDAO.delete(uProductReplyId);
		logger.info(resultDelete + " �� ���� ����");
		return 1;
	}




	

}
