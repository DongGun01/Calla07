package project.spring.calla.service;

import java.util.List;

import project.spring.calla.domain.ProductVO;
import project.spring.calla.pageutil.PageCriteria;

public interface ProductService {
	int create(ProductVO vo); // ��ǰ ����
	List<ProductVO> read(PageCriteria criteria); // ��ǰ��� �о����
	ProductVO read(int productId); // ��ǰID �о����
	int update(ProductVO vo); // ��ǰ ���� ����
	int delete(int productId); // ��ǰ ����
	int getTotalCounts(); // ��ǰ ��ü ����

}
