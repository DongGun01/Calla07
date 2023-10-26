package project.spring.calla.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import project.spring.calla.domain.UproductCommentVO;
import project.spring.calla.domain.UproductVO;
import project.spring.calla.pageutil.PageCriteria;
import project.spring.calla.persistence.FBoardCommentDAO;
import project.spring.calla.persistence.FBoardDAO;
import project.spring.calla.persistence.UProductCommentDAO;
import project.spring.calla.persistence.UproductDAO;

@Service
public class UproductCommentServiceImple implements UproductCommentService {

	private static final Logger logger = LoggerFactory.getLogger(UproductCommentServiceImple.class);

	@Autowired
	private UProductCommentDAO uProductCommentDAO;

	@Autowired
	private UproductDAO uProductDAO;

	@Transactional(value = "transactionManager")
	@Override
	public int create(UproductCommentVO vo) throws Exception {
		logger.info("create() ȣ�� : vo = " + vo.toString());
		int resultInsert = uProductCommentDAO.insert(vo);
		logger.info(resultInsert + " �� ��� �Է� ����");
		int result = uProductDAO.updateUproductCommentCount(1, vo.getuProductId());
		logger.info(result + " �� ���� ����");
		return 1;
	}

	@Transactional(value = "transactionManager")
	@Override
	public List<UproductCommentVO> read(int uProductId, HttpSession session) {
		logger.info("read() ȣ�� : uProductId = " + uProductId);
		UproductVO product = uProductDAO.select(uProductId);

		String memberNickname = (String) session.getAttribute("memberNickname");

		List<UproductCommentVO> items = uProductCommentDAO.select(uProductId);

		for (UproductCommentVO vo : items) {
			if(memberNickname == null) {
				vo.setuProductCommentContent("��� ����Դϴ�");
			} else {
				String commenter = vo.getMemberNickname();
				String writer = product.getMemberNickname();
				if(!memberNickname.equals(commenter) && !memberNickname.equals(writer)) {
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

}
