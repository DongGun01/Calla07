package project.spring.calla.service;

import java.util.List;

import project.spring.calla.domain.ProductLikeVO;

public interface ProductLikeService {
	int create(ProductLikeVO vo);  // ���ƿ� ���
	List<ProductLikeVO> read(String memberNickname); // ���ƿ� �˻�
	int delete(int productLikeId); // ���ƿ� ���
}
