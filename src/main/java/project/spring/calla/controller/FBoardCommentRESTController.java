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
import project.spring.calla.service.FBoardCommentService;

@RestController
@RequestMapping(value="/fBoard/comments")
public class FBoardCommentRESTController {

	private static final Logger logger 
				= LoggerFactory.getLogger(FBoardCommentRESTController.class);

	@Autowired
	private FBoardCommentService fBoardCommentService;

	@PostMapping // POST : ��� �Է�
	public ResponseEntity<Integer> createComment(@RequestBody FBoardCommentVO vo) {
		// @RequestBody
		// - Ŭ���̾�Ʈ���� ���۹��� json �����͸�
		// �ڹ� ��ü�� ��ȯ���ִ� annotation
		logger.info("createComment() ȣ�� : vo = " + vo.toString());

		// ResponseEntity<T> : Rest ��Ŀ��� �����͸� ������ �� ���̴� ��ü
		// - ������ HttpStatus�� ����
		// - <T> : �������� �ϴ� ������ Ÿ��
		int result = 0;
		try {
			result = fBoardCommentService.create(vo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}

	@GetMapping("/all/{FBoardId}") // GET : ��� ����(all)
	public ResponseEntity<List<FBoardCommentVO>> readComments(@PathVariable("FBoardId") int fBoardId) {
		// @PathVariable("fBoardId") : /all/{fBboardId} ���� ������ ������ ����
		logger.info("readComments() ȣ�� : fBoardId = " + fBoardId);

		List<FBoardCommentVO> list = fBoardCommentService.read(fBoardId);
		return new ResponseEntity<List<FBoardCommentVO>>(list, HttpStatus.OK);
	}

	@PutMapping("/{FBoardCommentId}") // PUT : ��� ����
	public ResponseEntity<Integer> updateComment(@PathVariable("FBoardCommentId") int fBoardCommentId,
			@RequestBody String fBoardCommentContent) {
		int result = fBoardCommentService.update(fBoardCommentId, fBoardCommentContent);
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}

	@DeleteMapping("/{FBoardCommentId}")
	public ResponseEntity<Integer> deleteComment(@PathVariable("FBoardCommentId") int fBoardCommentId,
			@RequestBody int fBoardId) {
		logger.info("FBoardCommentId = " + fBoardCommentId);

		int result = 0;
		try {
			result = fBoardCommentService.delete(fBoardCommentId, fBoardId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}

}
