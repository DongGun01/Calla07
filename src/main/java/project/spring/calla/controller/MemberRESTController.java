package project.spring.calla.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import project.spring.calla.domain.MemberVO;
import project.spring.calla.service.MemberService;

@RestController
@RequestMapping(value="/member")
public class MemberRESTController {
	private static final Logger logger =
			LoggerFactory.getLogger(MemberRESTController.class);
	
	@Autowired
	private MemberService memberService;
	
	@PostMapping("/checkId") // @RequestParam("member_Id")값이 String id에 저장
	public int checkId(@RequestParam("memberId") String id) {
		logger.info("checkId() 호출");
		logger.info(id); // 
			try {
				id = URLDecoder.decode(id, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			logger.info(id); // 

		return memberService.checkId(id);
	} // end checkId
	
	@PostMapping("/checkNick")
	public int checkNick(@RequestParam("memberNickname") String nick) {
		logger.info("checkNick() 호출");
		logger.info(nick);
			try {
				nick = URLDecoder.decode(nick,"UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			logger.info(nick);
		int result = memberService.checkNick(nick);
		logger.info(String.valueOf(result));
		return result;
	} // end checkNick
	
//	@PutMapping("memberNickname/{memberNickname}") // PUT : 댓글 수정
//	public ResponseEntity<Integer> updateMemberNickname(@PathVariable("memberNickname") String memberNickname) {
//		int result = memberService.update(memberNickname);
//		return new ResponseEntity<Integer>(result, HttpStatus.OK);
//	}
	
	@PutMapping("/updatePw/{memberId}") // PUT : 댓글 수정
	public ResponseEntity<Integer> updateMemberPw(@PathVariable("memberId") String memberId, @RequestBody Map<String, Object> args) {
		logger.info("updateMemberPw() 호출");
		logger.info("memberId = " + memberId);
		logger.info(args.get("currentPw").toString());
		logger.info(args.get("newPw").toString());
		logger.info(args.get("newPwCheck").toString());
		MemberVO vo = memberService.read(memberId);
		String memberPw = vo.getMemberPw();
		String newPw = args.get("newPw").toString();
		int result = 0;
		if (memberPw.equals(args.get("currentPw"))) {
			logger.info("pw 일치");
			result = memberService.updatePw(memberId, newPw);
		} else {
			logger.info("pw 불일치");
			
		}
			
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}// end updatePw
	
	@PutMapping("/updateNickname/{memberId}") // PUT : 댓글 수정
	public ResponseEntity<Integer> updateMemberNickname(@PathVariable("memberId") String memberId, @RequestBody String newNickname) {
		logger.info("updateMemberNickname() 호출");
		logger.info("newNickname = " + newNickname);
		
		int result = memberService.updateNickname(memberId, newNickname);
			
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}// end updateNickname
	
	
	@PutMapping("/updatePhone/{memberId}") // PUT : 댓글 수정
	public ResponseEntity<Integer> updateMemberPhone(@PathVariable("memberId") String memberId, @RequestBody String newPhone) {
		logger.info("updateMemberPhone() 호출");
		logger.info("newPhone = " + newPhone);
		
		int result = memberService.updatePhone(memberId, newPhone);
			
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}// end updatePhone
	
//	@PutMapping("/updateEmail/{memberId}") // PUT : 댓글 수정
//	public ResponseEntity<Integer> updateMemberEmail(@PathVariable("memberId") String memberId, @RequestBody String newEmail) {
//		logger.info("updateMemberEmail() 호출");
//		logger.info("newPhone = " + newEmail);
//		
//		int result = memberService.updateEmail(memberId, newEmail);
//			
//		return new ResponseEntity<Integer>(result, HttpStatus.OK);
//	}// end updateEmail
//	
//	@PutMapping("/updateEmail/{memberId}") // PUT : 댓글 수정
//	public ResponseEntity<Integer> updateMemberInterest(@PathVariable("memberId") String memberId, @RequestBody String newInterest) {
//		logger.info("updateMemberInterest() 호출");
//		logger.info("newInterest = " + newInterest);
//		
//		int result = memberService.updateInterest(memberId, newInterest);
//			
//		return new ResponseEntity<Integer>(result, HttpStatus.OK);
//	}// end updateEmail
}
	
	

