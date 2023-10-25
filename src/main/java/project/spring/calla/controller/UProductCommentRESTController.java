package project.spring.calla.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

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

import project.spring.calla.domain.FBoardCommentVO;
import project.spring.calla.domain.UproductCommentVO;
import project.spring.calla.service.FBoardCommentService;
import project.spring.calla.service.UproductCommentService;

@RestController
@RequestMapping(value="/uProduct/uproductcomments")
public class UProductCommentRESTController {

	private static final Logger logger 
				= LoggerFactory.getLogger(UProductCommentRESTController.class);

	@Autowired
	private UproductCommentService uProductCommentService;

	@PostMapping // POST : ��� �Է�
	public ResponseEntity<Integer> createComment(@RequestBody UproductCommentVO vo) {
		// @RequestBody
		// - Ŭ���̾�Ʈ���� ���۹��� json �����͸�
		// �ڹ� ��ü�� ��ȯ���ִ� annotation
		logger.info("createComment() ȣ�� : vo = " + vo.toString());

		// ResponseEntity<T> : Rest ��Ŀ��� �����͸� ������ �� ���̴� ��ü
		// - ������ HttpStatus�� ����
		// - <T> : �������� �ϴ� ������ Ÿ��
		int result = 0;
		try {
			result = uProductCommentService.create(vo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}

	@GetMapping("/all/{uProductId}") // GET : ��� ����(all)
	public ResponseEntity<List<UproductCommentVO>> readComments(@PathVariable("uProductId") int uProductId, HttpSession session) {
		// @PathVariable("fBoardId") : /all/{fBboardId} ���� ������ ������ ����
		logger.info("readComments() ȣ�� : uProductId = " + uProductId);

		List<UproductCommentVO> list = uProductCommentService.read(uProductId, session);
		return new ResponseEntity<List<UproductCommentVO>>(list, HttpStatus.OK);
	}
	
	
	
	
	
	

	@PutMapping("/{uProductCommentId}") // PUT : ��� ����
	public ResponseEntity<Integer> updateComment(@PathVariable("uProductCommentId") int uProductCommentId,
			@RequestBody String uProductCommentContent) {
		int result = uProductCommentService.update(uProductCommentId, uProductCommentContent);
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}

	@DeleteMapping("/{uProductCommentId}") // ��� ���� 
	public ResponseEntity<Integer> deleteComment(@PathVariable("uProductCommentId") int uProductCommentId,
			@RequestBody int uProductId) {
		logger.info("FBoardCommentId = " + uProductCommentId);

		int result = 0;
		try {
			result = uProductCommentService.delete(uProductCommentId, uProductId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}

}
