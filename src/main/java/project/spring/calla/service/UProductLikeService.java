package project.spring.calla.service;

import java.util.List;

import project.spring.calla.domain.UProductLikeVO;

public interface UProductLikeService {
	int create(UProductLikeVO vo);  // ���ƿ� ���
	List<UProductLikeVO> read(String memberNickname); // ���ƿ� �˻�
	int delete(int uProductLikeId); // ���ƿ� ���
}
