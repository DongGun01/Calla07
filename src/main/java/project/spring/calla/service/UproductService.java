package project.spring.calla.service;

import java.util.List;

import project.spring.calla.domain.FBoardVO;
import project.spring.calla.domain.ProductVO;
import project.spring.calla.domain.UproductVO;
import project.spring.calla.pageutil.PageCriteria;

public interface UproductService {
	int create(UproductVO vo) throws Exception; // ��ǰ ����
	List<UproductVO> read(PageCriteria criteria); // ��ǰ��� �о����
	UproductVO read(int uProductId); // ��ǰID �о����
	int update(UproductVO vo); // ��ǰ ���� ����
	int delete(int uProductId); // ��ǰ ����
	int getTotalCounts(); // ��ǰ ��ü ����
	List<UproductVO> readByCategoriorName(PageCriteria criteria, String keyword);
	int getTotalCountsByByCategoriorName(String keyword);
	List<UproductVO> readdate(PageCriteria criteria); // ��ǰ��� �о����
	int getTotalCountsBydate();

}
