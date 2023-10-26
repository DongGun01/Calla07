package project.spring.calla.persistence;

import java.util.List;

import project.spring.calla.domain.ProductLikeVO;

public interface ProductLikeDAO {
	int insert(ProductLikeVO vo);  // ���ƿ� ���
	List<ProductLikeVO> select(String memberNickname); // ���ƿ� �˻�
	int delete(int productLikeId); // ���ƿ� ���
}
