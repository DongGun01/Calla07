package project.spring.calla.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import project.spring.calla.service.JoinService;

@RestController
@RequestMapping(value="/member")
public class JoinRESTController {
	private static final Logger logger =
			LoggerFactory.getLogger(JoinRESTController.class);
	
	@Autowired
	private JoinService joinService;
	
	@PostMapping // 1 ���Ͻ� �ߺ�Ȯ�� ���� @RequestParam("member_Id")�� String id�� �ִ� key-value����ε�
	public int checkId(@RequestParam("memberId") String id) {
		logger.info("checkIdȣ��");
		logger.info(id); // ��Ʈ�ѷ��� �Ѿ���鼭 = ����
		try {
			id = URLDecoder.decode(id, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info(id); // JSP���� �Է��� ���̵� " = " �پ �ֿܼ����� �׷��� �ߺ�üũ�� ����� �ȵ�

		return joinService.checkId(id);
	} // end checkId
		
	
	
	
	
	}
	
	

