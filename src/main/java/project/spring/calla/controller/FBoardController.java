package project.spring.calla.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import project.spring.calla.domain.FBoardVO;
import project.spring.calla.pageutil.PageCriteria;
import project.spring.calla.pageutil.PageMaker;
import project.spring.calla.service.FBoardCommentService;
import project.spring.calla.service.FBoardService;
import project.spring.calla.util.FileUploadUtil;
import project.spring.calla.util.MediaUtil;

@Controller // @Component
// * ǥ�� ����(Presentation Layer)
// - view(������)�� service�� �����ϴ� ����
// - request�� ���� response�� �����ϴ� ����
@RequestMapping(value="/fBoard") // url : /ex02/board
public class FBoardController {
	private static final Logger logger = 
			LoggerFactory.getLogger(FBoardController.class);
	
	@Resource(name = "uploadpath")
	private String uploadpath;
	
	@Autowired
	private FBoardService fBoardService; 
	
	@Autowired
	private FBoardCommentService fBoardCommentService;
	
	@GetMapping("/list")
	public void list(Model model, Integer page, Integer numsPerPage, String option, String keyword) {
		
		logger.info("list() ȣ��");
		logger.info("page = " + page + ", numsPerPage = " + numsPerPage);
		List<FBoardVO> list = null;
		// Paging ó��
		
		PageCriteria criteria = new PageCriteria();
		if(page != null) {
			criteria.setPage(page);
		}
		
		if(numsPerPage != null) {
			criteria.setNumsPerPage(numsPerPage);
		}
		PageMaker pageMaker = new PageMaker();
		if (option != null) {
			if (option.equals("searchMemberNickname")) {
				list = fBoardService.readByMemberNickname(criteria, keyword);
				pageMaker.setTotalCount(fBoardService.getTotalCountsLikeMemberNickname(keyword));
			} else if (option.equals("searchTitleOrContent")) {
				list = fBoardService.readByTitleOrContent(criteria, keyword);
				pageMaker.setTotalCount(fBoardService.getTotalCountsByTitleContent(keyword));
			} else {
				list = fBoardService.read(criteria);
				pageMaker.setTotalCount(fBoardService.getTotalCounts());
			}
		} else {
			logger.info("else");
			list = fBoardService.read(criteria);
			pageMaker.setTotalCount(fBoardService.getTotalCounts());
		}
		logger.info("totalCount = " + pageMaker.getTotalCount());
		model.addAttribute("list", list);
		model.addAttribute("option", option);
		model.addAttribute("keyword", keyword);
		
		pageMaker.setCriteria(criteria);
		pageMaker.setPageData();
		model.addAttribute("pageMaker", pageMaker);
	} // end list()
	
	@GetMapping("/register")
	public void registerGET() {
		logger.info("registerGET()");
	} // end registerGET()
	
	@PostMapping("/register")
	public String registerPOST(FBoardVO vo, RedirectAttributes reAttr, MultipartFile file) {
		// RedirectAttributes
		// - �����̷�Ʈ �� �����͸� �����ϱ� ���� �������̽�
		
		logger.info("registerPost() ȣ�� : vo = " + vo.toString());
		logger.info("���� �̸� : " + file.getOriginalFilename());
		logger.info("���� ũ�� : " + file.getSize());
		try {
			String savedFileName = FileUploadUtil.saveUploadedFile(uploadpath, file.getOriginalFilename(), file.getBytes());
			vo.setfBoardImagePath(savedFileName);
		} catch (IOException e) {
			return "redirect:/fBoard/register";
		}
		
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
	
	@GetMapping("/display")
	public ResponseEntity<byte[]> display(String fileName) {
		logger.info("display() ȣ��");
		
		ResponseEntity<byte[]> entity = null;
		InputStream in = null;
		String filePath = uploadpath + fileName;
		try {
			in = new FileInputStream(filePath);
			// ���� Ȯ����
			String extension =
					filePath.substring(filePath.lastIndexOf(".") + 1);
			
			// ���� ���(response header)�� Content-Type ����
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.setContentType(MediaUtil.getMediaType(extension));
			// ������ ����
			entity = new ResponseEntity<byte[]>(
					IOUtils.toByteArray(in), // ���Ͽ��� ���� ������
					httpHeaders, // ���� ���
					HttpStatus.OK
					);
					
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return entity;
	}
	
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
		
		PageMaker pageMaker = new PageMaker();
		PageCriteria criteria = new PageCriteria();
		if(page != null) {
			criteria.setPage(page);
			
		}
		pageMaker.setTotalCount(fBoardCommentService.getTotalCounts(fBoardId));
		
		pageMaker.setCriteria(criteria);
		pageMaker.setPageData();
		model.addAttribute("vo", vo);
		model.addAttribute("page", page); // ��� ����¡ �ʱ� ������ ��ȣ
		model.addAttribute("pageMaker", pageMaker);
		
		return "/fBoard/detail";
	} // end detail()
	
	@GetMapping("/update")
	public void updateGET(Model model, Integer fBoardId) {
		logger.info("updateGET() ȣ�� : fBoardId = " + fBoardId);
		FBoardVO vo = fBoardService.read(fBoardId);
		model.addAttribute("vo", vo);
	} // end updateGET()
	
	@PostMapping("/update")
	public String updatePOST(FBoardVO vo) {
		logger.info("updatePOST() ȣ�� : vo = " + vo.toString());
		int result = fBoardService.update(vo);
		
		if(result == 1) {
			return "redirect:/fBoard/list";
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
