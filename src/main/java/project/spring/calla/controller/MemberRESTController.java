package project.spring.calla.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import project.spring.calla.domain.MemberVO;
import project.spring.calla.pageutil.RecentlyViewPageCriteria;
import project.spring.calla.pageutil.RecentlyViewPageMaker;
import project.spring.calla.service.MemberService;

@RestController
@RequestMapping(value="/member")
public class MemberRESTController {
	private static final Logger logger =
			LoggerFactory.getLogger(MemberRESTController.class);
	
	@Autowired
	private MemberService memberService;
	
	@PostMapping("/join")
	public ResponseEntity<Integer> createMember(@RequestBody MemberVO vo) {
		logger.info("createMember() ȣ�� : vo = " + vo.toString());
		int result = 0;
		result = memberService.create(vo);
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	} // end createMember
	
	@PostMapping("/checkId") // @RequestParam("member_Id")���� String id�� ����
	public int checkId(@RequestParam("memberId") String id) {
		logger.info("checkId() ȣ��");
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
		logger.info("checkNick() ȣ��");
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
	
//	@PutMapping("memberNickname/{memberNickname}") // PUT : ��� ����
//	public ResponseEntity<Integer> updateMemberNickname(@PathVariable("memberNickname") String memberNickname) {
//		int result = memberService.update(memberNickname);
//		return new ResponseEntity<Integer>(result, HttpStatus.OK);
//	}
	
	@PutMapping("/updatePw/{memberId}") // ��й�ȣ ����
	public ResponseEntity<Integer> updateMemberPw(@PathVariable("memberId") String memberId, @RequestBody Map<String, Object> args, HttpSession session) {
		logger.info("updateMemberPw() ȣ��");
		logger.info("memberId = " + memberId);
		logger.info(args.get("currentPw").toString());
		logger.info(args.get("newPw").toString());
		logger.info(args.get("newPwCheck").toString());
		MemberVO vo = memberService.read(memberId);
		String memberPw = vo.getMemberPw();
		String newPw = args.get("newPw").toString();
		int result = 0;
		if (memberPw.equals(args.get("currentPw"))) {
			logger.info("pw ��ġ");
			result = memberService.updatePw(memberId, newPw);
			
		} else {
			logger.info("pw ����ġ");
			
		}
			
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}// end updatePw
	
	@PutMapping("/updateNickname/{memberId}") // PUT : ��� ����
	public ResponseEntity<Integer> updateMemberNickname(@PathVariable("memberId") String memberId, @RequestBody String newNickname, HttpSession session) {
		logger.info("updateMemberNickname() ȣ��");
		logger.info("newNickname = " + newNickname);
		
		int result = memberService.updateNickname(memberId, newNickname);
			if (result==1) {
				session.setAttribute("memberNickname", newNickname);
			}
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}// end updateNickname
	
	
	@PutMapping("/updatePhone/{memberId}") // PUT : ��� ����
	public ResponseEntity<Integer> updateMemberPhone(@PathVariable("memberId") String memberId, @RequestBody String newPhone) {
		logger.info("updateMemberPhone() ȣ��");
		logger.info("newPhone = " + newPhone);
		
		int result = memberService.updatePhone(memberId, newPhone);
			
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}// end updatePhone
	
	@PutMapping("/updateEmail/{memberId}") // PUT : ��� ����
	public ResponseEntity<Integer> updateMemberEmail(@PathVariable("memberId") String memberId, @RequestBody String newEmail) {
		logger.info("updateMemberEmail() ȣ��");
		logger.info("newEmail = " + newEmail);
		
		int result = memberService.updateEmail(memberId, newEmail);
			
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}// end updateEmail
	
	@PutMapping("/updateInterest/{memberId}") // PUT : ��� ����
	public ResponseEntity<Integer> updateMemberInterest(@PathVariable("memberId") String memberId, @RequestBody String newInterest) {
		logger.info("updateMemberInterest() ȣ��");
		logger.info("newInterest = " + newInterest);
		
		int result = memberService.updateInterest(memberId, newInterest);
			
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}// end updateEmail
	
	@PutMapping("/updateAddress/{memberId}") // PUT : ��� ����
	public ResponseEntity<Integer> updateMemberAddress(@PathVariable("memberId") String memberId, @RequestBody String newAddress) {
		logger.info("updateMemberAddress() ȣ��");
		logger.info("newAddress = " + newAddress);
		
		int result = memberService.updateAddress(memberId, newAddress);
			
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}// end updateAddress
	
//	@GetMapping("/updateLevel/{memberId}") // GET : ��� ����(all)
//	public ResponseEntity<Integer> readReplies(@PathVariable("memberId") String memberId) {
//		// @PathVariable("fBoardId") : /all/{fBboardId} ���� ������ ������ ����
//		logger.info("updateLevelGET() ȣ�� : memberId = " + memberId);
//
//		List<FBoardReplyVO> list = fBoardReplyService.read(fBoardCommentId);
//		return new ResponseEntity<List<FBoardReplyVO>>(list, HttpStatus.OK);
//	}
	
	@PutMapping("/updateLevel/{memberId}") 
	public ResponseEntity<Integer> updateMemberLevel(@PathVariable("memberId") String memberId, @RequestBody String memberLevel, HttpSession session) {
		logger.info("updateMemberLevel() ȣ��");
		int amount = 0;
		if(Integer.parseInt(memberLevel) == 1) {
			amount = 1;
		} else if (Integer.parseInt(memberLevel) == 2) {
			amount = -1;
		}
		int updateResult = memberService.updateLevel(memberId, amount);
		MemberVO updatedVO = memberService.read(memberId);
		int newMemberLevel = updatedVO.getMemberLevel();
		logger.info(String.valueOf(newMemberLevel));

		return new ResponseEntity<Integer>(newMemberLevel, HttpStatus.OK);
	}// end updateAddress
	
	@GetMapping("/recentlyView/{memberId}/{page}") 
	public ResponseEntity<Map<String, Object>> recentlyViewGET(@PathVariable("memberId") String memberId, @PathVariable("page") int page) {
		logger.info("recentlyViewGET() ȣ�� : memberId = " + memberId);
		logger.info("recentlyViewGET() ȣ�� : page = " + page);
		
		RecentlyViewPageCriteria criteria = new RecentlyViewPageCriteria();
		RecentlyViewPageMaker pageMaker = new RecentlyViewPageMaker();
		
		Map<String, Object> lists = memberService.readRecentlyView(criteria, memberId);
		Map<String, Integer> counts = memberService.getTotalCountsByRecentlyView(memberId);
		criteria.setPage(page);
		pageMaker.setTotalCount(counts.get("productCount"));
		pageMaker.setCriteria(criteria);
		pageMaker.setPageData();
		logger.info(String.valueOf(pageMaker.isHasNext()));
		lists.put("pageMaker", pageMaker);
		return new ResponseEntity<Map<String, Object>>(lists, HttpStatus.OK);
	}
}
	
	

