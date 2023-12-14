package project.spring.calla.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import project.spring.calla.domain.UProductMannerDownVO;
import project.spring.calla.service.UProductReviewService;

@Controller

@RestController
@RequestMapping(value = "/uProduct/mannerdown") // url : /calla/product
public class UProductMannerRESTController {
	private static final Logger logger = LoggerFactory.getLogger(UProductMannerRESTController.class);

	@Autowired
	private UProductReviewService reviewservice;
	
	@Transactional(value = "transactionManager")
	@PostMapping // POST : ��� �Է�
	public ResponseEntity<Integer> mannerDown(@RequestBody UProductMannerDownVO vo) {
		// @RequestBody
		// - Ŭ���̾�Ʈ���� ���۹��� json
		// �ڹ� ��ü�� ��ȯ���ִ� annotation
		logger.info("createReply() ȣ�� : vo = " + vo.toString());

	
		int result = 0;
		String sellerNickname = vo.getSellerNickname();
		logger.info("mannerDown() ȣ�� : uProductId = " + vo.getuProductId());

		try {
			
			int count = reviewservice.countmannerdown(vo.getuProductId());
			logger.info("reviewservice() ȣ�� : count = " + count);
			if(count == 0) {
			
			result = reviewservice.insertmannerdown(vo);
		
			reviewservice.updatememberManners(sellerNickname);
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