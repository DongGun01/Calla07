package project.spring.calla.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // @Component
// * ǥ�� ����(Presentation Layer)
// - view(������)�� service�� �����ϴ� ����
// - request�� ���� response�� �����ϴ� ����
 // url : /ex02/board
public class FBoardCommentController {
	@GetMapping("/detail")
	public void comment() {
		
	}
	
} // end BoardController
