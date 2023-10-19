package project.spring.calla.controller;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import project.spring.calla.domain.FBoardVO;
import project.spring.calla.pageutil.PageCriteria;
import project.spring.calla.pageutil.PageMaker;
import project.spring.calla.service.FBoardService;

@Controller // @Component
// * ǥ�� ����(Presentation Layer)
// - view(������)�� service�� �����ϴ� ����
// - request�� ���� response�� �����ϴ� ����
@RequestMapping(value="/fBoard") // url : /ex02/board
public class FBoardController {
	private static final Logger logger = 
			LoggerFactory.getLogger(FBoardController.class);
	
	@Autowired
	private FBoardService fBoardService; 
	
	@GetMapping("/list")
	public void list(Model model, Integer page, Integer numsPerPage) {
		
		logger.info("list() ȣ��");
		logger.info("page = " + page + ", numsPerPage = " + numsPerPage);
		
		// Paging ó��
		PageCriteria criteria = new PageCriteria();
		if(page != null) {
			criteria.setPage(page);
		}
		
		if(numsPerPage != null) {
			criteria.setNumsPerPage(numsPerPage);
		}
		
		List<FBoardVO> list = fBoardService.read(criteria);
		model.addAttribute("list", list);
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCriteria(criteria);
		pageMaker.setTotalCount(fBoardService.getTotalCounts());
		pageMaker.setPageData();
		model.addAttribute("pageMaker", pageMaker);
	} // end list()
	
	@GetMapping("/register")
	public void registerGET() {
		logger.info("registerGET()");
	} // end registerGET()
	
	@PostMapping("/register")
	public String registerPOST(FBoardVO vo, RedirectAttributes reAttr) {
		// RedirectAttributes
		// - �����̷�Ʈ �� �����͸� �����ϱ� ���� �������̽�
		logger.info("registerPOST() ȣ��");
		logger.info(vo.toString());
		int result = fBoardService.create(vo);
		logger.info(result + "�� ����");
		if(result == 1) {
			reAttr.addFlashAttribute("insert_result", "success");
			return "redirect:/fBoard/list";
			// redirect�� request ������ ������...
		} else {
			return "redirect:/fBoard/register";
		}
	} // end registerPOST()
	
	@GetMapping("/detail")
	public String detail(Model model, Integer fBoardId, Integer page, 
			HttpServletRequest request, HttpServletResponse response) {

		String cookieName = "fBoard_" + fBoardId;
		
		Cookie[] cookies = request.getCookies();
		boolean cookieFound = false;
		
		if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(cookieName)) {
                    cookieFound = true;
                    break;
                }
            }
        }
		 if (!cookieFound) {
	            // ��Ű�� �������� �ʴ� ���, ��ȸ�� ���� �� ��Ű ����
	            int views = 1; // ù ��° ��ȸ
	            Cookie viewCookie = new Cookie(cookieName, String.valueOf(views));
	            viewCookie.setMaxAge(60); // ��Ű ��ȿ �ð� (1��)
	            response.addCookie(viewCookie);
	            
	            int result = fBoardService.updateViews(views, fBoardId);
	            if (result == 1) {
	            	logger.info("��ȸ�� ����");
	            	
	            } else { 
	            	logger.info("��ȸ�� ���� ����");
	            }
		 }
		 
		logger.info("detail() ȣ�� : fBoardId = " + fBoardId);
		FBoardVO vo = fBoardService.read(fBoardId);
		model.addAttribute("vo", vo);
		model.addAttribute("page", page);
		
		return "/fBoard/detail";
	} // end detail()
	
	@GetMapping("/update")
	public void updateGET(Model model, Integer fBoardId, Integer page) {
		logger.info("updateGET() ȣ�� : fBoardId = " + fBoardId);
		FBoardVO vo = fBoardService.read(fBoardId);
		model.addAttribute("vo", vo);
		model.addAttribute("page", page);
	} // end updateGET()
	
	@PostMapping("/update")
	public String updatePOST(FBoardVO vo, Integer page) {
		logger.info("updatePOST() ȣ�� : vo = " + vo.toString());
		int result = fBoardService.update(vo);
		
		if(result == 1) {
			return "redirect:/fBoard/list?page=" + page;
		} else {
			return "redirect:/fBoard/update?fBoardId=" + vo.getfBoardId();
		}
	} // end updatePOST()
	
	@PostMapping("/delete")
	public String delete(Integer fBoardId) {
		logger.info("delete() ȣ�� : boardId = " + fBoardId);
		int result = fBoardService.delete(fBoardId);
		if(result == 1) {
			return "redirect:/fBoard/list";
		} else {
			return "redirect:/fBoard/list";
		}
	} // end deletePOST()
} // end BoardController
