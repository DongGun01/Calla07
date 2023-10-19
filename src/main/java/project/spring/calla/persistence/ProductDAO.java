package project.spring.calla.persistence;

import java.util.List;

import project.spring.calla.domain.ProductVO;
import project.spring.calla.pageutil.PageCriteria;

public interface ProductDAO {
	int insert(ProductVO vo); // ��ǰ ���
	List<ProductVO> select(); // ��ǰ ��ü �˻�
	ProductVO select(int productId); // ��ǰ �˻�	
	int update(ProductVO vo); // ��ǰ ����
	int delete(int productId); // ��ǰ ����
	List<ProductVO> select(PageCriteria criteria);
	int getTotalCount();
	List<ProductVO> select(String productName);
	List<ProductVO> selectByName(String keyword);
	
	
}
