package db_testing;

public class order_detail {

	private int OrderDetailID;
	private int OrderID;
	private int ProductID;
	private int OrderedQuantity;
	private double price ;
	public int getOrderDetailID() {
		return OrderDetailID;
	}
	public void setOrderDetailID(int orderDetailID) {
		OrderDetailID = orderDetailID;
	}
	public int getOrderID() {
		return OrderID;
	}
	public void setOrderID(int orderID) {
		OrderID = orderID;
	}
	public int getProductID() {
		return ProductID;
	}
	public void setProductID(int productID) {
		ProductID = productID;
	}
	public int getOrderedQuantity() {
		return OrderedQuantity;
	}
	public void setOrderedQuantity(int orderedQuantity) {
		OrderedQuantity = orderedQuantity;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public order_detail(int orderDetailID, int orderID, int productID, int orderedQuantity, double price) {
		super();
		OrderDetailID = orderDetailID;
		OrderID = orderID;
		ProductID = productID;
		OrderedQuantity = orderedQuantity;
		this.price = price;
	}
	@Override
	public String toString() {
		return "order_detail [OrderDetailID=" + OrderDetailID + ", OrderID=" + OrderID + ", ProductID=" + ProductID
				+ ", OrderedQuantity=" + OrderedQuantity + ", price=" + price + "]";
	}
	
}
