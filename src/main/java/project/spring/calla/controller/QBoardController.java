package project.spring.calla.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import project.spring.calla.domain.QBoardVO;
import project.spring.calla.pageutil.PageCriteria;
import project.spring.calla.pageutil.PageMaker;
import project.spring.calla.service.QBoardService;

@Controller //@Component
// * ǥ�� ����(Presentation Layer)
// - view(������)�� service�� �����ϴ� ����
// - request�� ���� response�� �����ϴ� ����
@RequestMapping(value="/qBoard") // url : /ex02/board
public class QBoardController {
	private static final Logger logger = 
			LoggerFactory.getLogger(QBoardController.class);

	@Autowired
	private QBoardService qBoardService;
	
	@GetMapping("/list")
	public void list(Model model, Integer page, Integer numsPerPage) {
		logger.info("list() ȣ��");
		logger.info("page = " + page + ", numsPerPage = " + numsPerPage);
		
		PageCriteria criteria = new PageCriteria();
		if(page != null) {
			criteria.setPage(page);
		}
		if(numsPerPage != null) {
			criteria.setNumsPerPage(numsPerPage);
		}
		
		List<QBoardVO> list = qBoardService.read(criteria);
		model.addAttribute("list", list);
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCriteria(criteria);
		pageMaker.setTotalCount(qBoardService.getTotalCounts());
		pageMaker.setPageData();
		model.addAttribute("pageMaker", pageMaker);
	
	} // end list()
	
	@GetMapping("/register")
	public void registerGET() {
		logger.info("registerGET()");
	} // end registerGET()
	
	@PostMapping("/register")
	public String registerPOST(QBoardVO vo, RedirectAttributes reAttr) {
		// RedirectAttributes
		// - �����̷�Ʈ �� �����͸� �����ϱ� ���� �������̽�
		logger.info("registerPOST() ȣ��");
		logger.info(vo.toString());
		int result = qBoardService.create(vo);
		logger.info(result + "�� ����"); // ���⼭ result 1�� ���;���
		if(result == 1) { 
			reAttr.addFlashAttribute("insert_result", "success");
			return "redirect:/qBoard/list"; // ���� ����
		} else {
			return "redirect:/qBoard/register";
		}
		
	} // end registerPOST
	
	@GetMapping("/detail")
	public void detail(Model model, Integer qBoardId, Integer page) {
		logger.info("detail() ȣ�� : boardId = " + qBoardId);
		QBoardVO vo = qBoardService.read(qBoardId);
		model.addAttribute("vo", vo);
		model.addAttribute("page", page);
	} // end detail
	
	@GetMapping("/update")
	public void updateGET(Model model, Integer qBoardId, Integer page) {
		logger.info("updateGET() ȣ�� : boardId = " + qBoardId);
		QBoardVO vo = qBoardService.read(qBoardId);
		model.addAttribute("vo", vo);
		model.addAttribute("page", page);
	} // end updateGET()
	
	@PostMapping("/update")
	public String updatePOST(QBoardVO vo, Integer page) {
		logger.info("updatePOST() ȣ�� : vo = " + vo.toString());
		int result = qBoardService.update(vo);
		
		if(result == 1) {
			return "redirect:/qBoard/list?page=" + page;
		} else {
			return "redirect:/qBoard/update?boardId=" + vo.getqBoardId();
		}
	} // end updatePOST()
	
	@PostMapping("/delete")
	public String delete(Integer qBoardId) {
		logger.info("delete() ȣ�� : boardId = " + qBoardId);
		int result = qBoardService.delete(qBoardId);
		if(result == 1) {
			return "redirect:/qBoard/list";
		} else {
			return "redirect:/qBoard/list";
		}
	}
	
} // end BoardController
