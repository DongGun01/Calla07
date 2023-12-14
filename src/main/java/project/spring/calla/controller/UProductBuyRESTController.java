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

import project.spring.calla.domain.ProductOrderVO;
import project.spring.calla.domain.UProductLikeVO;
import project.spring.calla.domain.UProductMannerVO;
import project.spring.calla.domain.UProductVO;
import project.spring.calla.service.MemberService;
import project.spring.calla.service.UProductReviewService;
import project.spring.calla.service.UProductService;

// * RESTful url�� �ǹ�
// /replies (POST) : ��� �߰�(insert)
// /replies/all/���� (GET) : �ش� �� ��ȣ(boardId)�� ��� ���� �˻�(select)
// /replies/���� (PUT) : �ش� ��� ��ȣ(replyId)�� ������ ����(update)
// /replies/���� (DELETE) : �ش� ��� ��ȣ(replyId)�� ����� ����(delete)

@RestController
@RequestMapping(value = "/uProduct/buy")
public class UProductBuyRESTController {
	private static final Logger logger = LoggerFactory.getLogger(UProductBuyRESTController.class);

	@Autowired
	private UProductService uproductService;
	
	@Autowired
	private UProductReviewService uproductreviewservice;
	
	@Autowired
	private MemberService memberService;

	
	
	@DeleteMapping("/{uProductBuyId}")
	public ResponseEntity<Integer> deleteuProductbuy(@PathVariable("uProductBuyId") int uProductBuyId) {
		logger.info("uProductBuyId = " + uProductBuyId);
		
		int result = 0;
		try {
			result = uproductService.deleteUProductbuy(uProductBuyId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}
	
	
	
	
}















