package project.spring.calla.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

	

	@PutMapping("/{sellerNickname}") // PUT : ��� ����
	public ResponseEntity<Float> updatemanner(@PathVariable("sellerNickname") String sellerNickname) {
		logger.info("updatemanner() ȣ��");
		logger.info("sellerNickname : " + sellerNickname);
		float result = reviewservice.updatememberManner(sellerNickname);
		return new ResponseEntity<Float>(result, HttpStatus.OK);
	}

	

}
