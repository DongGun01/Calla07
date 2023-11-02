package project.spring.calla.persistence;

import java.util.List;

import project.spring.calla.domain.FBoardVO;
import project.spring.calla.domain.QBoardVO;
import project.spring.calla.domain.UProductVO;
import project.spring.calla.pageutil.PageCriteria;

public interface UProductDAO {
	int insert(UProductVO vo); // ��ǰ ���
	List<UProductVO> select(); // ��ǰ ��ü �˻�
	UProductVO select(int uProductId); // ��ǰ �˻�	
	int update(UProductVO vo); // ��ǰ ����
	int delete(int uProductId); // ��ǰ ����
	List<UProductVO> select(PageCriteria criteria);
	int getTotalCount();
	
	List<UProductVO> select(String uProductName);
	List<UProductVO> selectByName(String keyword);
	
	int updateUproductCommentCount(int amount, int uProductId);
	List<UProductVO> selectAllByMemberNickname(String menberNickname);
	
	List<UProductVO> selectByCategoriorName(PageCriteria criteria, String keyword); // ��ǰ �̸��Ǵ� ī�װ� �˻�
	int getTotalCountsByCategoriorName(String keyword);
	
	List<UProductVO> selectByUproductCreatedDate(PageCriteria criteria); // �Ż�ǰ �˻�
	int getTotalCountsByUproductCreatedDate();
	
	List<UProductVO> selectByAddress(PageCriteria criteria, String keyword); // ��ǰ �ּ� �˻�
	int getTotalCountsByAddress(String keyword);
	
	List<UProductVO> recommendCategori(String uProductCategori, int uProductId);
	
}
