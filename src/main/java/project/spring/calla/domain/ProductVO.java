package project.spring.calla.domain;

import java.util.Date;

public class ProductVO {
	private int productId; // ��ǰ ��ȣ
	private String productName; // ��ǰ �̸�
	private int productPrice; // ��ǰ ����
	private int productViews; // ��ǰ ��ȸ��
	private int productLikes; // ��ǰ ���ƿ� ��
	private Date productCreatedDate; // ��ǰ ��� ��¥
	private String productCategori; // ��ǰ ī�װ�
	private String productImagePath; // ��ǰ �̹��� ���
	private String productContent; // ��ǰ ����
	private int productCommentCount; // ��ǰ ��� ����
	private String memberNickname; // ȸ�� �г���
	private int memberLevel; // ȸ�� ���
	private int productAmount;
	private int productLikeId;
	private int productRecentlyViewId;
	private int productAverageRated; // ��ǰ ���� ���
	
	
	
	public ProductVO() {}

	public ProductVO(int productId, String productName, int productPrice, int productViews, int productLikes,
			Date productCreatedDate, String productCategori, String productImagePath, String productContent,
			int productCommentCount, String memberNickname, int memberLevel, int productAmount, int productLikeId,
			int productRecentlyViewId, int productAverageRated) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.productPrice = productPrice;
		this.productViews = productViews;
		this.productLikes = productLikes;
		this.productCreatedDate = productCreatedDate;
		this.productCategori = productCategori;
		this.productImagePath = productImagePath;
		this.productContent = productContent;
		this.productCommentCount = productCommentCount;
		this.memberNickname = memberNickname;
		this.memberLevel = memberLevel;
		this.productAmount = productAmount;
		this.productLikeId = productLikeId;
		this.productRecentlyViewId = productRecentlyViewId;
		this.productAverageRated = productAverageRated;
	}

	public int getProductId() {
		return productId;
	}


	public void setProductId(int productId) {
		this.productId = productId;
	}


	public String getProductName() {
		return productName;
	}


	public void setProductName(String productName) {
		this.productName = productName;
	}


	public int getProductPrice() {
		return productPrice;
	}


	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}


	public int getProductViews() {
		return productViews;
	}


	public void setProductViews(int productViews) {
		this.productViews = productViews;
	}


	public int getProductLikes() {
		return productLikes;
	}


	public void setProductLikes(int productLikes) {
		this.productLikes = productLikes;
	}


	public Date getProductCreatedDate() {
		return productCreatedDate;
	}


	public void setProductCreatedDate(Date productCreatedDate) {
		this.productCreatedDate = productCreatedDate;
	}


	public String getProductCategori() {
		return productCategori;
	}


	public void setProductCategori(String productCategori) {
		this.productCategori = productCategori;
	}


	public String getProductImagePath() {
		return productImagePath;
	}


	public void setProductImagePath(String productImagePath) {
		this.productImagePath = productImagePath;
	}


	public String getProductContent() {
		return productContent;
	}


	public void setProductContent(String productContent) {
		this.productContent = productContent;
	}


	public int getProductCommentCount() {
		return productCommentCount;
	}


	public void setProductCommentCount(int productCommentCount) {
		this.productCommentCount = productCommentCount;
	}


	public String getMemberNickname() {
		return memberNickname;
	}


	public void setMemberNickname(String memberNickname) {
		this.memberNickname = memberNickname;
	}


	public int getMemberLevel() {
		return memberLevel;
	}


	public void setMemberLevel(int memberLevel) {
		this.memberLevel = memberLevel;
	}


	public int getProductAmount() {
		return productAmount;
	}


	public void setProductAmount(int productAmount) {
		this.productAmount = productAmount;
	}


	public int getProductLikeId() {
		return productLikeId;
	}


	public void setProductLikeId(int productLikeId) {
		this.productLikeId = productLikeId;
	}


	public int getProductRecentlyViewId() {
		return productRecentlyViewId;
	}


	public void setProductRecentlyViewId(int productRecentlyViewId) {
		this.productRecentlyViewId = productRecentlyViewId;
	}

	public int getProductAverageRated() {
		return productAverageRated;
	}

	public void setProductAverageRated(int productAverageRated) {
		this.productAverageRated = productAverageRated;
	}

	@Override
	public String toString() {
		return "ProductVO [productId=" + productId + ", productName=" + productName + ", productPrice=" + productPrice
				+ ", productViews=" + productViews + ", productLikes=" + productLikes + ", productCreatedDate="
				+ productCreatedDate + ", productCategori=" + productCategori + ", productImagePath=" + productImagePath
				+ ", productContent=" + productContent + ", productCommentCount=" + productCommentCount
				+ ", memberNickname=" + memberNickname + ", memberLevel=" + memberLevel + ", productAmount="
				+ productAmount + ", productLikeId=" + productLikeId + ", productRecentlyViewId="
				+ productRecentlyViewId + ", productAverageRated=" + productAverageRated + "]";
	}

	

	
	

	


	
	
	
}
