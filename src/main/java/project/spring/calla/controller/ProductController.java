package project.spring.calla.controller;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

import javax.annotation.Resource;

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

import project.spring.calla.domain.ProductCommentVO;
import project.spring.calla.domain.ProductVO;
import project.spring.calla.pageutil.PageCriteria;
import project.spring.calla.pageutil.PageMaker;
import project.spring.calla.service.ProductCommentService;
import project.spring.calla.service.ProductService;
import project.spring.calla.util.FileUploadUtil;
import project.spring.calla.util.MediaUtil;

@Controller

@RequestMapping(value="/product") // url : /calla/product
public class ProductController {
	private static final Logger logger =
			LoggerFactory.getLogger(ProductController.class);
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private ProductCommentService productCommentService;
	
	@Resource(name = "uploadpath")
	private String uploadpath;
	
	@GetMapping("/list")
	public void list(Model model, Integer page, Integer numsPerPage, String option, String keyword) {
		logger.info("list() ȣ��");
		logger.info("page = " + page + " , numsPerPage = " + numsPerPage);
		List<ProductVO> list = null;
		
		//Paginf ó��
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
				logger.info("if");
				list = productService.readByProductNameOrProductContent(criteria, keyword);
				pageMaker.setTotalCount(productService.getTotalCountsByProductNameOrProductContent(keyword));	
			} else {
				logger.info("if else");
				list = productService.read(criteria);
				pageMaker.setTotalCount(productService.getTotalCounts());
			}
		}else {
			logger.info("else");
			list = productService.read(criteria);
			pageMaker.setTotalCount(productService.getTotalCounts());
		}
		logger.info("totalCount = " + pageMaker.getTotalCount());
		model.addAttribute("list",list);
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
	public String registerPost(ProductVO vo,  @RequestParam("productImage") MultipartFile file, RedirectAttributes reAttr) {
		logger.info("registerPOST() ȣ��");
		logger.info(vo.toString());		
		logger.info("���� �̸� : " + file.getOriginalFilename());
		logger.info("���� ũ�� : " + file.getSize());
		
		try {
			// ���� ����
			String savedFileName = FileUploadUtil.saveUploadedFile(uploadpath, file.getOriginalFilename(), file.getBytes());
			// �̹��� ��� ����
			vo.setProductImagePath(savedFileName);
			int result = productService.create(vo);
			logger.info(result + "�� ����");
			
			if(result == 1) {
				reAttr.addFlashAttribute("insert_result", "success");
				return "redirect:/product/list";
			} else {
				return "redirect:/product/register";
			}
		} catch (Exception e) {
			return "redirect:/product/register";
		}
		
		
		
	}  // end registerPOST()
	
	@GetMapping("/detail")
	public void detail(Model model, Integer productId, Integer page) {
		logger.info("detail() ȣ�� : productId = " + productId);
		ProductVO vo = productService.read(productId);
		logger.info("ȣ�� : prdocutVO = " + vo);
		model.addAttribute("vo", vo);
		model.addAttribute("page", page);
	} // end detail()

	
	@GetMapping("/update")
	public void updateGET(Model model, Integer productId, Integer page) {
		logger.info("updateGET() ȣ�� : productId = " + productId);
		ProductVO vo = productService.read(productId);
		logger.info("updateGET() ȣ�� : vo = " + vo.toString());
		model.addAttribute("vo", vo);
		model.addAttribute("page", page);		
	} // end updateGET()
	
	@PostMapping("/update")
	public String updatePOST(ProductVO vo, Integer page, @RequestParam("productImage") MultipartFile file) {
		logger.info("updatePOST() ȣ�� : vo = " + vo.toString());			
		logger.info("���� �̸� : " + file.getOriginalFilename());
		logger.info("���� ũ�� : " + file.getSize());
		try {	
			if(file != null && !file.isEmpty()) {
				String savedFileName = FileUploadUtil.saveUploadedFile(uploadpath, file.getOriginalFilename(), file.getBytes());
				vo.setProductImagePath(savedFileName);
			}
			
			int result = productService.update(vo);
			
			if(result == 1) {
				return "redirect:/product/list?page=" + page;
			} else {
				return "redirect:product/update?productId=" + vo.getProductId();
			}	
		} catch (Exception e) {
			return "redirect:product/update?productId=" + vo.getProductId();
		}
		
		
	} // end updatePOST()
	
	@PostMapping("/delete")
	public String delete(Integer productId) {
		logger.info("delete() ȣ�� : productId = " + productId);
		int result = productService.delete(productId);
		
		if(result == 1) {
			return "redirect:/product/list";
		} else {
			return "redirect:/product/list";
		}
	} // end delete()
	
	@GetMapping("/display")
	public ResponseEntity<byte[]> display(String fileName){
		logger.info("display() ȣ��");
		
		ResponseEntity<byte[]> entity = null;
		InputStream in = null;
		
		String filePath = uploadpath + fileName;
		
		try {
			in = new FileInputStream(filePath);
			
			// ���� Ȯ����
			String extension =
					filePath.substring(filePath.lastIndexOf(".") + 1);
			logger.info(extension);
			
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
			e.printStackTrace();
		}
		
		return entity;
	}
	

	
	
	
} 
