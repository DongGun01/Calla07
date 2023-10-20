package project.spring.calla.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.imageio.ImageIO;

import org.apache.commons.io.IOUtils;
import org.springframework.http.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.coobird.thumbnailator.Thumbnails;
import project.spring.calla.util.FileUploadUtil;
import org.springframework.http.MediaType;
import project.spring.calla.domain.UproductVO;
import project.spring.calla.domain.UImageVO;
import project.spring.calla.service.UBoardService;

@Controller
public class UregisterController {

	private static final Logger logger = LoggerFactory.getLogger(UregisterController.class);

	// ���� ������ �̵�
	@RequestMapping(value = "main", method = RequestMethod.GET)
	public void mainPageGET() {

		logger.info("���� ������ ����");

	}

	@Autowired
	private UBoardService uboardService;

	@GetMapping("/uregister")
	public void uregisterGET() {
		logger.info("uregisterGET()");
	} // end uregisterGET()

	@PostMapping("/uregister")
	public String registerPOST(UproductVO vo, RedirectAttributes reAttr) {
		// RedirectAttributes
		// - �����̷�Ʈ �� �����͸� �����ϱ� ���� �������̽�
		logger.info("uregisterPOST() ȣ��");
		logger.info(vo.toString());
		int result = uboardService.create(vo);
		logger.info(result + "�� ����");
		if (result == 1) {
			reAttr.addFlashAttribute("insert_result", "success");
			return "redirect:/main";
		} else {
			return "redirect:/uregister";
		}

	} // end registerPOST

	/* ÷�� ���� ���ε� */
	@PostMapping(value = "/uploadAjaxAction", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<UImageVO>> uploadAjaxActionPOST(MultipartFile[] uploadFile) {

		logger.info("uploadAjaxActionPOST..........");

		/* �̹��� ���� üũ */
		for (MultipartFile multipartFile : uploadFile) {

			File checkfile = new File(multipartFile.getOriginalFilename());
			String type = null;

			try {
				type = Files.probeContentType(checkfile.toPath());
				logger.info("MIME TYPE : " + type);
			} catch (IOException e) {
				e.printStackTrace();
			}

			if (!type.startsWith("image")) {

				List<UImageVO> list = null;
				return new ResponseEntity<>(list, HttpStatus.BAD_REQUEST);

			}

		} // for

		String uploadFolder = "C:\\upload";

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		Date date = new Date();

		String str = sdf.format(date);

		String datePath = str.replace("-", File.separator);

		/* ���� ���� */
		File uploadPath = new File(uploadFolder, datePath);

		if (uploadPath.exists() == false) {
			uploadPath.mkdirs();
		}

		List<UImageVO> list = new ArrayList();

		for (MultipartFile multipartFile : uploadFile) {
			logger.info("-----------------------------------------------");
			logger.info("���� �̸� : " + multipartFile.getOriginalFilename());
			logger.info("���� Ÿ�� : " + multipartFile.getContentType());
			logger.info("���� ũ�� : " + multipartFile.getSize());

			UImageVO vo = new UImageVO();

			/* ���� �̸� */
			String uploadFileName = multipartFile.getOriginalFilename();
			vo.setFileName(uploadFileName);
			vo.setUploadPath(datePath);

			String uuid = UUID.randomUUID().toString();
			vo.setUuid(uuid);

			uploadFileName = uuid + "_" + uploadFileName;

			/* ���� ��ġ, ���� �̸��� ��ģ File ��ü */
			File saveFile = new File(uploadPath, uploadFileName);

			/* ���� ���� */
			try {
				multipartFile.transferTo(saveFile);

				File thumbnailFile = new File(uploadPath, "s_" + uploadFileName);

				BufferedImage bo_image = ImageIO.read(saveFile);
				BufferedImage bt_image = new BufferedImage(300, 500, BufferedImage.TYPE_3BYTE_BGR);

				Graphics2D graphic = bt_image.createGraphics();

				graphic.drawImage(bo_image, 0, 0, 300, 500, null);

				ImageIO.write(bt_image, "jpg", thumbnailFile);

			} catch (Exception e) {
				e.printStackTrace();
			}

			list.add(vo);
		}

		ResponseEntity<List<UImageVO>> result = new ResponseEntity<List<UImageVO>>(list, HttpStatus.OK);

		return result;
	}

	@GetMapping("/display")
	public ResponseEntity<byte[]> getImage(String fileName) {

		File file = new File("c:\\upload\\" + fileName);

		ResponseEntity<byte[]> result = null;

		try {
			
			HttpHeaders header = new HttpHeaders();
			header.add("Content-type", Files.probeContentType(file.toPath()));
			result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK);

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return result;

	}

}
