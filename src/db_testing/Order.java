package db_testing;

import java.sql.Date;

public class Order {

	int order_Id;
	java.util.Date orederDate;
	String Shoping_Adress;
	int customer_id;
	public int getOrder_Id() {
		return order_Id;
	}
	public void setOrder_Id(int order_Id) {
		this.order_Id = order_Id;
	}
	public java.util.Date getOrederDate() {
		return orederDate;
	}
	public void setOrederDate(java.util.Date orederDate) {
		this.orederDate = orederDate;
	}
	public String getShoping_Adress() {
		return Shoping_Adress;
	}
	public void setShoping_Adress(String shoping_Adress) {
		Shoping_Adress = shoping_Adress;
	}
	public int getCustomer() {
		return customer_id;
	}
	public void setCustomer(int customer) {
		this.customer_id = customer;
	}
	@Override
	public String toString() {
		return "Order [order_Id=" + order_Id + ", orederDate=" + orederDate + ", Shoping_Adress=" + Shoping_Adress
				+ ", customer_id=" + customer_id + "]";
	}
	public Order(int order_Id, java.util.Date orederDate, String shoping_Adress,int cid) {
		super();
		this.order_Id = order_Id;
		this.orederDate = orederDate;
		Shoping_Adress = shoping_Adress;
		customer_id=cid;
	}
	
	
	
	
	
	
	
}
