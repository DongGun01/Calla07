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

import project.spring.calla.domain.QBoardCommentVO;
import project.spring.calla.service.QBoardCommentService;

// * RESTful url�� �ǹ�
// /replies (POST) : ��� �߰�(insert)
// /replies/all/���� (GET) : �ش� �� ��ȣ(boardId)�� ��� ��� �˻�(select)
// /replies/���� (PUT) : �ش� ��� ��ȣ(replyId)�� ������ ����(update)
// /replies/���� (DELETE) : �ش� ��� ��ȣ(replyId)�� ����� ����(delete)

@RestController // controller responsebody ����
@RequestMapping(value="/qBoard/comments")
public class QBoardCommentRESTController {
	private static final Logger logger = 
			LoggerFactory.getLogger(QBoardCommentRESTController.class);
	
	@Autowired
	private QBoardCommentService qBoardCommentService;
	//@Mapping
	// @PostMapping �� �޼���� HTTP POST ��û�� ó���մϴ�.
	@PostMapping // POST : ��� �Է�
	public ResponseEntity<Integer> createqBoardComment(@RequestBody QBoardCommentVO vo) {
		// @RequestBody
		// - Ŭ���̾�Ʈ���� ���۹��� json �����͸�
		//	�ڹ� ��ü�� ��ȯ���ִ� annotation
		logger.info("createqBoardComment() ȣ�� : vo = " + vo.toString());
		
		// ResponseEntity<T> : Rest ��Ŀ��� �����͸� ������ �� ���̴� ��ü
		// - �����͸� HttStatus�� ����
		// - <T> : �������� �ϴ� ������ Ÿ��
		int result = 0; // �ʱ�ȭ�� �̸� �������
		try {
			result = qBoardCommentService.create(vo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}
	// @GetMapping �� �޼���� HTTP GET ��û�� ó���մϴ�.
	@GetMapping("/all/{qBoardId}") // GET : ��� ����(all)
	public ResponseEntity<List<QBoardCommentVO>> readComments(
			@PathVariable("qBoardId") int qBoardId) {
		//@PathVariable("boardId") : /all/{boardId} ���� ������ ������ ����
		logger.info("readComments() ȣ�� : qBoardId = " + qBoardId);
		
		List<QBoardCommentVO> list = qBoardCommentService.read(qBoardId);
		return new ResponseEntity<List<QBoardCommentVO>>(list, HttpStatus.OK);
	}
	
	@PutMapping("/{qBoardCommentId}") // PUT : ��� ����
	public ResponseEntity<Integer> updateComment(
			@PathVariable("qBoardCommentId") int qBoardCommentId,
			@RequestBody String qBoardCommentContent
			){
		int result = qBoardCommentService.update(qBoardCommentId, qBoardCommentContent);
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}
	
	@DeleteMapping("/{qBoardCommentId}")
	public ResponseEntity<Integer> deleteReply(
			@PathVariable("qBoardCommentId") int qBoardCommentId,
			@RequestBody int qBoardId){
		logger.info("qBoardCommentId = " + qBoardCommentId);
		
		int result = 0;
		try {
			result = qBoardCommentService.delete(qBoardCommentId, qBoardId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}
	
}
