package project.spring.calla.persistence;

import java.util.List;

import project.spring.calla.domain.QBoardVO;
import project.spring.calla.domain.UproductVO;
import project.spring.calla.pageutil.PageCriteria;

public interface UproductDAO {
	int insert(UproductVO vo); // ��ǰ ���
	List<UproductVO> select(); // ��ǰ ��ü �˻�
	UproductVO select(int uProductId); // ��ǰ �˻�	
	int update(UproductVO vo); // ��ǰ ����
	int delete(int uProductId); // ��ǰ ����
	List<UproductVO> select(PageCriteria criteria);
	int getTotalCount();
	List<UproductVO> select(String uProductName);
	List<UproductVO> selectByName(String keyword);
	int updateUproductCommentCount(int amount, int uProductId);
	List<UproductVO> selectAllByMemberNickname(String menberNickname);
	
}
