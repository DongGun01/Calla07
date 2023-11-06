package project.spring.calla.pageutil;

// ���������� ������ ������ ��ȣ��
// �� ���������� ������ �Խñ��� ������ �����ϴ� Ŭ����
// -> paging ó���� �ʿ��� start�� end ��ȣ�� �� �� ����
public class RecentlyViewPageCriteria {
	private int page; // ���� ������ ��ȣ
	private int numsPerPage; // �� �������� �Խñ� ����
	
	public RecentlyViewPageCriteria() {
		this.page = 1;
		this.numsPerPage = 4;
	}
	
	public RecentlyViewPageCriteria(int page, int numsPerPage) {
		this.page = page;
		this.numsPerPage = numsPerPage;
	}

	// getter/setter
	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getNumsPerPage() {
		return numsPerPage;
	}

	public void setNumsPerPage(int numsPerPage) {
		this.numsPerPage = numsPerPage;
	}
	
	// ���� �������� �������� ���� �� �Ϸù�ȣ(rn)
	public int getStart() {
		return (this.page - 1) * this.numsPerPage + 1;
	}
	
	// ���� �������� �������� ������ �� �Ϸù�ȣ(rn)
	public int getEnd() {
		return this.page * this.numsPerPage;
	}
}

