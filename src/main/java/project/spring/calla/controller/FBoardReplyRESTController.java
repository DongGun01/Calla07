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

import project.spring.calla.domain.FBoardCommentVO;
import project.spring.calla.domain.FBoardReplyVO;
import project.spring.calla.service.FBoardCommentService;
import project.spring.calla.service.FBoardReplyService;

@RestController
@RequestMapping(value="/fBoard/replies")
public class FBoardReplyRESTController {
	
	private static final Logger logger 
	= LoggerFactory.getLogger(FBoardReplyRESTController.class);

@Autowired
private FBoardReplyService fBoardReplyService;


@PostMapping // POST : ��� �Է�
public ResponseEntity<Integer> createReply(@RequestBody FBoardReplyVO vo) {
	// @RequestBody
	// - Ŭ���̾�Ʈ���� ���۹��� json �����͸�
	// �ڹ� ��ü�� ��ȯ���ִ� annotation
	logger.info("createReply() ȣ�� : vo = " + vo.toString());

	// ResponseEntity<T> : Rest ��Ŀ��� �����͸� ������ �� ���̴� ��ü
	// - ������ HttpStatus�� ����
	// - <T> : �������� �ϴ� ������ Ÿ��
	int result = 0;
	try {
		result = fBoardReplyService.create(vo);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return new ResponseEntity<Integer>(result, HttpStatus.OK);
}

@GetMapping("/all/{FBoardCommentId}") // GET : ��� ����(all)
public ResponseEntity<List<FBoardReplyVO>> readReplies(@PathVariable("FBoardCommentId") int fBoardCommentId) {
	// @PathVariable("fBoardId") : /all/{fBboardId} ���� ������ ������ ����
	logger.info("readReplies() ȣ�� : fBoardCommentId = " + fBoardCommentId);

	List<FBoardReplyVO> list = fBoardReplyService.read(fBoardCommentId);
	return new ResponseEntity<List<FBoardReplyVO>>(list, HttpStatus.OK);
}

@PutMapping("/{FBoardReplyId}") // PUT : ��� ����
public ResponseEntity<Integer> updateReply(@PathVariable("FBoardReplyId") int fBoardReplyId,
		@RequestBody String fBoardReplyContent) {
	int result = fBoardReplyService.update(fBoardReplyId, fBoardReplyContent);
	return new ResponseEntity<Integer>(result, HttpStatus.OK);
}

@DeleteMapping("/{FBoardReplyId}")
public ResponseEntity<Integer> deleteReply(@PathVariable("FBoardReplyId") int fBoardReplyId,
		@RequestBody int fBoardCommentId) {
	logger.info("FBoardReplyId = " + fBoardReplyId);

	int result = 0;
	try {
		result = fBoardReplyService.delete(fBoardReplyId, fBoardCommentId);
	} catch (Exception e) {
		e.printStackTrace();
	}
	return new ResponseEntity<Integer>(result, HttpStatus.OK);
}

}
