package project.spring.calla.persistence;

import java.util.List;

import project.spring.calla.domain.UProductLikeVO;

public interface UProductLikeDAO {
	int insert(UProductLikeVO vo);  // ���ƿ� ���
	List<UProductLikeVO> select(String memberNickname); // ���ƿ� �˻�
	int delete(int uProductLikeId); // ���ƿ� ���
}
