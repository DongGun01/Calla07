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

import project.spring.calla.domain.ProductReplyVO;
import project.spring.calla.service.ProductReplyService;

@RestController
@RequestMapping(value="/product/productReplies")
public class ProductReplyRESTController {
	private static final Logger logger =
			LoggerFactory.getLogger(ProductReplyRESTController.class);
	
	@Autowired
	private ProductReplyService productReplyService;
	
	@PostMapping // POST : ��� �Է�
	public ResponseEntity<Integer> createReply(@RequestBody ProductReplyVO vo){
		logger.info("createReply() ȣ�� : vo = " + vo.toString());
		
		int result = 0;
		try {
			result = productReplyService.create(vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}
	
	@GetMapping("/all/{productCommentId}") // ��� ����(all)
	public ResponseEntity<List<ProductReplyVO>> readReplies(@PathVariable("productCommentId") int productCommentId){
		logger.info("readProductReplies() ȣ�� : productCommentId = " + productCommentId);
		
		List<ProductReplyVO> list = productReplyService.read(productCommentId);
		return new ResponseEntity<List<ProductReplyVO>>(list, HttpStatus.OK);
	}
	
	@PutMapping("/{productReplyId}") // PUT : ��� ����
	public ResponseEntity<Integer> updateReply(@PathVariable("productReplyId") int productReplyId, @RequestBody String productReplyContent){
		logger.info("updateReply() ȣ�� : productReplyId = " + productReplyId);
		int result = productReplyService.update(productReplyId, productReplyContent);
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}
	
	@DeleteMapping("/{productReplyId}") // Delete : ��� ����
	public ResponseEntity<Integer> deleteReply(@PathVariable("productReplyId") int productReplyId){
		logger.info("productReplyId = " + productReplyId);
		
		int result = 0;
		try {
			result = productReplyService.delete(productReplyId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}
} // end ProductReplyRESTControler
