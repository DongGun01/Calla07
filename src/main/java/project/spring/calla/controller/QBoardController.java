package project.spring.calla.controller;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import project.spring.calla.domain.QBoardVO;
import project.spring.calla.pageutil.PageCriteria;
import project.spring.calla.pageutil.PageMaker;
import project.spring.calla.service.QBoardCommentService;
import project.spring.calla.service.QBoardService;
import project.spring.calla.util.FileUploadUtil;
import project.spring.calla.util.MediaUtil;

@Controller //@Component
// * ǥ�� ����(Presentation Layer)
// - view(������)�� service�� �����ϴ� ����
// - request�� ���� response�� �����ϴ� ����
@RequestMapping(value="/qBoard") // url : /ex02/board
public class QBoardController {
	private static final Logger logger = 
			LoggerFactory.getLogger(QBoardController.class);
	
	@Resource(name = "uploadpath")
	private String uploadpath;
	
	@Autowired
	private QBoardService qBoardService;
	
	@Autowired
	private QBoardCommentService qBoardCommentService;
	
	@GetMapping("/note")
	public void noteget() {
		
	}
	
	@GetMapping("/list")
	public void list(Model model, Integer page, Integer numsPerPage, String option, String keyword, RedirectAttributes reAttr) {
		logger.info("list() ȣ��");
		logger.info("page = " + page + ", numsPerPage = " + numsPerPage);
		List<QBoardVO> list = null;
		
		PageCriteria criteria = new PageCriteria();
		if(page != null) {
			criteria.setPage(page);
		}
		if(numsPerPage != null) {
			criteria.setNumsPerPage(numsPerPage);
		}
		
		PageMaker pageMaker = new PageMaker();
		if(option != null) {
			if(option.equals("searchMemberNickname")) {
				logger.info("ifif");
				list = qBoardService.readBymemberNickname(criteria, keyword);
				pageMaker.setTotalCount(qBoardService.getTotalCountsByMeberNickname(keyword));				
			} else if (option.equals("searchTitle")) {
				logger.info("if elseif");
				list = qBoardService.readByTitle(criteria, keyword);
				pageMaker.setTotalCount(qBoardService.getTotalCountsByTitle(keyword));
			} else {
				logger.info("if else");
				list = qBoardService.read(criteria);
				pageMaker.setTotalCount(qBoardService.getTotalCounts());
			}	
		} else {
			logger.info("else");
			list = qBoardService.read(criteria);
			pageMaker.setTotalCount(qBoardService.getTotalCounts());
		}
		logger.info("totalCount = " + pageMaker.getTotalCount());
		model.addAttribute("list", list);
		model.addAttribute("option", option);
		model.addAttribute("keyword", keyword);
		
		pageMaker.setCriteria(criteria);
		pageMaker.setPageData();
		model.addAttribute("pageMaker", pageMaker);
		reAttr.addFlashAttribute("status_result", "secret");
	} // end list()
	
	@GetMapping("/register")
	public void registerGET() {
		logger.info("registerGET()");
	} // end registerGET()
	
	@PostMapping("/register")
	public String registerPOST(QBoardVO vo, @RequestParam("customFile") MultipartFile file, RedirectAttributes reAttr) {
		// RedirectAttributes
		// - �����̷�Ʈ �� �����͸� �����ϱ� ���� �������̽�
		logger.info("registerPOST() ȣ��");
		logger.info(vo.toString());
		logger.info("���� �̸� : " + file.getOriginalFilename());
		logger.info("���� ũ�� : " + file.getSize());
	
		try {
	    
	      // ���� ����
	      String savedFileName = FileUploadUtil.saveUploadedFile(uploadpath, file.getOriginalFilename(), file.getBytes());
	      // �̹��� ��� ����
	      vo.setqBoardImagePath(savedFileName);
	      int result = qBoardService.create(vo);
	      logger.info("result = " + result);
	      logger.info(result + "�� ����"); // ���⼭ result 1�� ���;���

	      if (result == 1) {
	      reAttr.addFlashAttribute("insert_result", "success");
	      return "redirect:/qBoard/list"; 
	      } else {
	      return "redirect:/qBoard/register";
	      }

	      } catch (Exception e) {
	        logger.info("register POST ȣ��");
	        logger.info("catch�� ����");
	        e.printStackTrace();
	      }
	    

	    // ������ ����ְų� ���ε� �� ó�� �� ���� �߻� ��, ����� �����̷�Ʈ
	    return "redirect:/qBoard/register";
		
	} // end registerPOST
	

	
	
	@GetMapping("/detail")
	public String detail(Model model, Integer qBoardId, Integer page,
			HttpServletRequest request, HttpServletResponse response, RedirectAttributes reAttr) {
		String cookieName = "qBoard_" + qBoardId;
		Cookie[] cookies = request.getCookies();
		boolean cookieFound = false;
		if(cookies != null) {
			for(Cookie cookie : cookies) {
				if(cookie.getName().equals(cookieName)) {
					cookieFound = true;
					break;
				}
			}
		}
		if(!cookieFound) {
			// ��Ű�� �������� �ʴ� ���, ��ȸ�� ���� �� ��Ű ����
			int views = 1; // ù ��° ��ȸ
			Cookie viewCookie = new Cookie(cookieName, String.valueOf(views));
			viewCookie.setMaxAge(60); // ��Ű ��ȿ �ð� 1��
			response.addCookie(viewCookie);
			int result = qBoardService.updateViews(views, qBoardId);
			if(result == 1) {
				logger.info("��ȸ�� ����");
			} else {
				logger.info("��ȸ�� ���� ����");
			}	
		}
		logger.info("detail() ȣ�� : boardId = " + qBoardId);
		QBoardVO vo = qBoardService.read(qBoardId);
		HttpSession session = request.getSession();
		String memberNickname = (String) session.getAttribute("memberNickname");
		Integer memberLevel = (Integer) session.getAttribute("memberLevel");
		logger.info("�Խñ� ���� ���� : " + vo.getqBoardStatus()); // �Խñ��� ���� or �����
		
		PageMaker pageMaker = new PageMaker();
		PageCriteria criteria = new PageCriteria();
		if(page != null) {
			criteria.setPage(page);
			model.addAttribute("page", page);
		}
		
		pageMaker.setTotalCount(qBoardCommentService.getTotalCounts(qBoardId));
		
		pageMaker.setCriteria(criteria);
		pageMaker.setPageData();
		model.addAttribute("vo", vo);
		model.addAttribute("pageMaker", pageMaker);
		
		
		
	    if(vo.getqBoardStatus().equals("����")) { // ������ �Խñ��� �������θ� �������� ������ ��
	    	logger.info("�����Խñ���");
	    	logger.info("���� �α����� ��� �г��� : " + memberNickname);
			logger.info("���� �α����� ��� ���� : " + memberLevel);
			return "/qBoard/detail";
			
		} else {
			logger.info("������Խñ���");
			logger.info("�ۼ��� �г��� : " + vo.getMemberNickname());
			logger.info("���� �α����� ��� �г��� : " + memberNickname);
			logger.info("���� �α����� ��� ���� : " + memberLevel);
			if(memberNickname != null) {
				if(vo.getMemberNickname().equals(memberNickname) || memberLevel.equals(2) || memberLevel.equals(3)) {
					logger.info("������ε� �ۼ��� �г����̶� ����� �α����� ��� �г����� ����, ������ ����� �� ���� ����");
					return "/qBoard/detail";
					
				} else {
					logger.info("������ε� �α����� ���� ���");
					reAttr.addFlashAttribute("status_result", "secret");
					return "redirect:/qBoard/list";
					
				}
				
			}
			logger.info("������ε� �α����� ���� ���");
			reAttr.addFlashAttribute("status_result", "secret");
			return "redirect:/qBoard/list";
			
		}
		
		
	} // end detail
	
	@GetMapping("/update")
	public void updateGET(Model model, Integer qBoardId, String qBoardStatus, Integer page) {
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
	
	@GetMapping("/display")
	public ResponseEntity<byte[]> display(String fileName) {
		logger.info("display() ȣ��");
		ResponseEntity<byte[]> entity = null;
		InputStream in = null;
		
		String filePath = uploadpath + fileName;
		
		try {
			in = new FileInputStream(filePath);
			// ���� Ȯ����
			String extension = filePath.substring(filePath.lastIndexOf(".") + 1);
			logger.info(extension);
			// ���� ���(respponse header)�� Content-Type ����
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.setContentType(MediaUtil.getMediaType(extension));
			// ������ ����
			entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), // ���Ͽ��� ���� ������
					httpHeaders, // ���� ���
					HttpStatus.OK);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return entity;		
	}
	
} // end BoardController
