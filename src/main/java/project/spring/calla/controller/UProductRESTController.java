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
import project.spring.calla.domain.UProductCommentVO;
import project.spring.calla.domain.UProductLikeVO;
import project.spring.calla.domain.UProductMannerVO;
import project.spring.calla.domain.UProductVO;
import project.spring.calla.service.MemberService;
import project.spring.calla.service.UProductCommentService;
import project.spring.calla.service.UProductReviewService;
import project.spring.calla.service.UProductService;

// * RESTful url�� �ǹ�
// /replies (POST) : ��� �߰�(insert)
// /replies/all/���� (GET) : �ش� �� ��ȣ(boardId)�� ��� ���� �˻�(select)
// /replies/���� (PUT) : �ش� ��� ��ȣ(replyId)�� ������ ����(update)
// /replies/���� (DELETE) : �ش� ��� ��ȣ(replyId)�� ����� ����(delete)

@RestController
@RequestMapping(value = "/activity/statement")
public class UProductRESTController {
	private static final Logger logger = LoggerFactory.getLogger(UProductRESTController.class);

	@Autowired
	private UProductService uproductService;
	
	@Autowired
	private UProductCommentService uproductcommentservice;
	
	@Autowired
	private UProductReviewService uproductreviewservice;
	
	@Autowired
	private MemberService memberService;

	@Transactional(value = "transactionManager")
	@PutMapping("/{uProductId}") // PUT : ��� ����
	public ResponseEntity<Integer> updateStatement(@PathVariable("uProductId") int uProductId) {
		 int result = 0;
		 int count = uproductreviewservice.count(uProductId);
		 
		 UProductVO vo = uproductService.read(uProductId);
		 
		 String statement = vo.getuProductStatement();
		 
		 logger.info("countȣ�� : " + count);
	
	      try {
	    	  
	    	  if(statement.equals("�ŷ�����")) {
	    		 
	    		  result = uproductService.statementupdate(uProductId);
	    		  return new ResponseEntity<>(result, HttpStatus.OK);
	    		  
	    		  
	    	  } else if(statement.equals("������")) {
	    		  result = uproductService.statementupdates(uProductId);
	    		  return new ResponseEntity<>(result, HttpStatus.OK);
	    	  }
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	       
	    return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	
	@DeleteMapping("/{uProductId}")
	public ResponseEntity<Integer> deleteuProductsell(@PathVariable("uProductId") int uProductId) {
		logger.info("uProductId = " + uProductId);
		
		int result = 0;
		try {
			result = memberService.deleteUProduct(uProductId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}
	
	
	
	
}















