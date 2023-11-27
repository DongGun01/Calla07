package project.spring.calla.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import project.spring.calla.domain.UProductMannerVO;
import project.spring.calla.service.UProductReviewService;

// * RESTful url�� �ǹ�
// /replies (POST) : ��� �߰�(insert)
// /replies/all/���� (GET) : �ش� �� ��ȣ(boardId)�� ��� ���� �˻�(select)
// /replies/���� (PUT) : �ش� ��� ��ȣ(replyId)�� ������ ����(update)
// /replies/���� (DELETE) : �ش� ��� ��ȣ(replyId)�� ����� ����(delete)

@RestController
@RequestMapping(value = "/uProduct/manner")
public class UProductReviewRESTController {
	private static final Logger logger = LoggerFactory.getLogger(UProductReviewRESTController.class);

	@Autowired
	private UProductReviewService reviewservice;
	
	@Transactional(value = "transactionManager")
	@PostMapping // POST : ��� �Է�
	public ResponseEntity<Integer> createReply(@RequestBody UProductMannerVO vo) {
		// @RequestBody
		// - Ŭ���̾�Ʈ���� ���۹��� json epdlxjfmf
		// �ڹ� ��ü�� ��ȯ���ִ� annotation
		logger.info("createReply() ȣ�� : vo = " + vo.toString());

	
		int result = 0;
		String sellerNickname = vo.getSellerNickname();
		logger.info("createReply() ȣ�� : uProductId = " + vo.getuProductId());

		try {
			
			int count = reviewservice.count(vo.getuProductId());
			logger.info("reviewservice() ȣ�� : count = " + count);
			if(count == 0) {
			
			result = reviewservice.insertmanner(vo);
		
			reviewservice.updatememberManner(sellerNickname);
			return new ResponseEntity<Integer>(result, HttpStatus.OK);
			
		} else {
			return new ResponseEntity<Integer>(result, HttpStatus.OK);
		}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}

	

}
