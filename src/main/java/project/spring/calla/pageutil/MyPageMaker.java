package project.spring.calla.pageutil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import project.spring.calla.controller.FBoardController;

// ������ ��ȣ���� ��ũ�� ����� ���� ��ƿ��Ƽ Ŭ����
public class MyPageMaker {
	private static final Logger logger = 
			LoggerFactory.getLogger(MyPageMaker.class);
	
	private MyPageCriteria criteria;
	private int totalCount; // ��ü �Խñ� ����
	private boolean hasPrev; // ȭ�鿡 ���̴� ���� ������ ��ȣ���� ���� ������ �������� �ִ� ��
	private boolean hasNext; // ȭ�鿡 ���̴� �� ������ ��ȣ���� ū ������ �������� �ִ� ��
	private int totalLinkNo;
	
	public MyPageMaker() {
	}
	
	public MyPageCriteria getCriteria() {
		return criteria;
	}
	
	public void setCriteria(MyPageCriteria criteria) {
		this.criteria = criteria;
	}
	
	public int getTotalCount() {
		return totalCount;
	}
	
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	
	public int getTotalLinkNo() {
		return totalLinkNo;
	}
	
	public void setTotalLinkNo(int totalLinkNo) {
		this.totalLinkNo = totalLinkNo;
	}
	
	public boolean isHasPrev() {
		return hasPrev;
	}
	
	public boolean isHasNext() {
		return hasNext;
	}
	
	// startPageNo, endPageNo, hasPrev, hasNext ���� ��� �� ����
	public void setPageData() {
		
		logger.info("setPageData ȣ��");
		logger.info("totalcount" + totalCount);
		logger.info("page" + criteria.getPage());
		int totalLinkNo = (int) Math.ceil((double) totalCount / criteria.getNumsPerPage());
		setTotalLinkNo(totalLinkNo);
		
		if (criteria.getPage() == 1) {
			hasPrev = false;
		} else {
			hasPrev = true;
		}
		
		if (criteria.getPage() < totalLinkNo) {
			hasNext = true;
		} else {
			hasNext = false;
		}
		
	}
	
} // end PageMaker
