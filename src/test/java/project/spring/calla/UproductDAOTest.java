package project.spring.calla;

import java.security.Provider.Service;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import oracle.jdbc.driver.OracleDriver;
import project.spring.calla.domain.UProductVO;
import project.spring.calla.pageutil.PageCriteria;
import project.spring.calla.persistence.MemberDAO;
import project.spring.calla.persistence.UProductDAO;
import project.spring.calla.service.MemberService;
import project.spring.calla.service.UProductService;

@RunWith(SpringJUnit4ClassRunner.class)

@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/**/*.xml" })
@WebAppConfiguration

public class UproductDAOTest {
	private static final Logger logger = LoggerFactory.getLogger(UproductDAOTest.class);

	@Autowired
	private UProductDAO dao;
	MemberDAO memberdao;
	
	UProductService service;
	MemberService memberservice;
	
	@Transactional
	@Test
	public void testDAO() {
//		testInsert();
//		testRecommand();
//		testSelectAll();
//		testSelectByBoardId();
//		testUpdate();
//		testDelete();
//		testSelectPaging();
//		testSelectDate();
		testuproduct();
	}

private void testuproduct() {
	PageCriteria criteria = new PageCriteria(1, 3);
	List<UProductVO> list = memberdao.selectmyuproduct(criteria, "���۰�����");
	for(UProductVO vo : list) {
		logger.info(vo.toString());
	}
		
	}

private void testRecommand() {
	List<UProductVO> list = dao.recommendCategori("��ä", 60);
	for(UProductVO vo : list) {
		logger.info(vo.toString());
	}
		
	}

//private void testSelectDate() {
//		// TODO Auto-generated method stub
//	PageCriteria criteria = new PageCriteria(1, 3);
//	List<UProductVO> list = dao.selectByUproductCreatedDate(criteria);
//	for(UProductVO vo : list) {
//		logger.info(vo.toString());
//	}
//		
//	}


//	private void testImageInsert() {
//		
//		UImageVO vo = new UImageVO("test2", "test2", "test2", 5);
//		
//		int result = dao.imageinsert(vo);
//		if (result == 1) {
//			logger.info("insert ����");
//		} else {
//			logger.info("insert ����");
//	
//		}
//		
//		
//	}

	
	private void testInsert() {
		UProductVO vo = new UProductVO(25, "���", "2000��", 0, 0, null, "��ä", "��⵵", "�ȳ��ϼ���", "����", "����", 0);

		
		int result = dao.insert(vo);
		if (result == 1) {
			logger.info("insert ����");
		} else {
			logger.info("insert ����");
	
		}
		
		System.out.println("��ϵ� VO : " + vo);
		
		
	}
	
	

}