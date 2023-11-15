package project.spring.calla.service;

import java.util.List;

import project.spring.calla.domain.ProductCommentVO;
import project.spring.calla.pageutil.PageCriteria;

public interface ProductCommentService {
	int create(ProductCommentVO vo) throws Exception; // ��� ���
	List<ProductCommentVO> read(int productId); // ��ǰ ��� �˻�
	int update(int productCommentId, String productCommentContent); // ��� ����
	int delete(int productCommentId, int productId) throws Exception; // ��� ����
	List<ProductCommentVO> read(PageCriteria criteria, int productId);
	int getTotalCounts(int productId);
	List<ProductCommentVO> read(int productId, int productRated);
	int getRatedCounts(int productRated, int productId);
}
