package project.spring.calla.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.spring.calla.domain.UBoardVO;
import project.spring.calla.domain.UImageVO;
import project.spring.calla.persistence.UBoardDAO;

@Service // @Component
// * ���� ����(Service/Business Layer)
// - ǥ�� ����(Presentation Layer)�� ���� ����(Persistence Layer)���̸�
//	 �����Ͽ� �� ������ ���������� ������� �ʵ��� �ϴ� ����
// - Ʈ�����(transaction) ����
// - DB�� ������� �����͸� ó�� ����
//
public class UBoardServiceImple implements UBoardService {
	private static final Logger logger = LoggerFactory.getLogger(UBoardServiceImple.class);

	@Autowired
	private UBoardDAO dao;

	@Override
	public int create(UBoardVO vo) {
		logger.info("create() ȣ�� : vo = " + vo.toString());
		return dao.insert(vo);
	}

	@Override
	public int imagecreate(UImageVO vo) {
		logger.info("imagecreate() ȣ�� : vo = " + vo.toString());
		return dao.imageinsert(vo);
	}



}
