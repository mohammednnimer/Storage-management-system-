package db_testing;

public class Product {

	
	private int productId;
	private String productName;
	private String Category;
	private double price;
	private String maincompany;
	private int quantity;
	
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productName=" + productName + ", Category=" + Category
				+ ", price=" + price + ", maincompany=" + maincompany + ", quantity=" + quantity + "]";
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
	public String getCategory() {
		return Category;
	}
	public void setCategory(String category) {
		Category = category;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getMaincompany() {
		return maincompany;
	}
	public void setMaincompany(String maincompany) {
		this.maincompany = maincompany;
	}
	public Product(int productId, String productName, String category, double price, String maincompany,int qun) {
		super();
		this.productId = productId;
		this.productName = productName;
		Category = category;
		this.price = price;
		this.maincompany = maincompany;
		this.quantity=qun;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
