package project.spring.calla.domain;

import java.util.Date;

public class ProductCommentVO {
	private int productCommentId; // ��ǰ ��� ��ȣ
	private int productId; // ��ǰ ��ȣ
	private String memberNickname; // ȸ�� �г���
	private String productCommentContent; // ��ǰ ��� ����
	private Date productCommentCreatedDate; // ��ǰ ��� ��� ��¥
	
	public ProductCommentVO() {
		
	}

	public ProductCommentVO(int productCommentId, int productId, String memberNickname, String productCommentContent,
			Date productCommentCreatedDate) {
		this.productCommentId = productCommentId;
		this.productId = productId;
		this.memberNickname = memberNickname;
		this.productCommentContent = productCommentContent;
		this.productCommentCreatedDate = productCommentCreatedDate;
	}

	public int getProductCommentId() {
		return productCommentId;
	}

	public void setProductCommentId(int productCommentId) {
		this.productCommentId = productCommentId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getMemberNickname() {
		return memberNickname;
	}

	public void setMemberNickname(String memberNickname) {
		this.memberNickname = memberNickname;
	}

	public String getProductCommentContent() {
		return productCommentContent;
	}

	public void setProductCommentContent(String productCommentContent) {
		this.productCommentContent = productCommentContent;
	}

	public Date getProductCommentCreatedDate() {
		return productCommentCreatedDate;
	}

	public void setProductCommentCreatedDate(Date productCommentCreatedDate) {
		this.productCommentCreatedDate = productCommentCreatedDate;
	}

	@Override
	public String toString() {
		return "ProductCommentVO [productCommentId=" + productCommentId + ", productId=" + productId
				+ ", memberNickname=" + memberNickname + ", productCommentContent=" + productCommentContent
				+ ", productCreatedDate=" + productCommentCreatedDate + "]";
	}
	
	
	
}
