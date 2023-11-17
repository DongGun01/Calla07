package project.spring.calla.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import project.spring.calla.domain.AlarmVO;
import project.spring.calla.domain.FBoardCommentVO;
import project.spring.calla.pageutil.PageCriteria;
import project.spring.calla.pageutil.PageMaker;
import project.spring.calla.service.AlarmService;

@RestController
@RequestMapping(value="/alarm")
public class AlarmRESTController {

	private static final Logger logger 
				= LoggerFactory.getLogger(AlarmRESTController.class);

	@Autowired
	private AlarmService alarmService;
	
	@GetMapping("all/{memberNickname}")
	public ResponseEntity<List<AlarmVO>> readAlarm(@PathVariable String memberNickname) {
		logger.info("readAlarm ȣ�� memberNicknmae = " + memberNickname);
		List<AlarmVO> lists = alarmService.read(memberNickname);
		logger.info(lists.toString());
		return new ResponseEntity<List<AlarmVO>>(lists, HttpStatus.OK);
		
	}
	@PutMapping("/{alarmId}") // PUT : ��� ����
	public ResponseEntity<Integer> updateComment(@PathVariable String memberNickname, @PathVariable int alarmId) {
		int result = alarmService.update(alarmId);
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}

}
