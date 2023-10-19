package project.spring.calla.persistence;

import java.util.List;

import project.spring.calla.domain.ProductCommentVO;

public interface ProductCommentDAO {
	int insert(ProductCommentVO vo); // ��� ���
	List<ProductCommentVO> select(int productId); // ��ǰ�� ��� �˻�
	int update(int productCommentId, String productCommentContent); // ��� ����
	int delete(int productCommentId); // ��� ����
}
