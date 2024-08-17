package db_testing;

public class inventory {

	
	private int InventoryID;
	private	int ProductID;
	private int UpdateDate;
	private int CurrentQuantity;
	public int getInventoryID() {
		return InventoryID;
	}
	public void setInventoryID(int inventoryID) {
		InventoryID = inventoryID;
	}
	public int getProductID() {
		return ProductID;
	}
	public void setProductID(int productID) {
		ProductID = productID;
	}
	public int getUpdateDate() {
		return UpdateDate;
	}
	public void setUpdateDate(int updateDate) {
		UpdateDate = updateDate;
	}
	public int getCurrentQuantity() {
		return CurrentQuantity;
	}
	public void setCurrentQuantity(int currentQuantity) {
		CurrentQuantity = currentQuantity;
	}
	@Override
	public String toString() {
		return "inventory [InventoryID=" + InventoryID + ", ProductID=" + ProductID + ", UpdateDate=" + UpdateDate
				+ ", CurrentQuantity=" + CurrentQuantity + "]";
	}
	
	
	
	
	
	
	
	
	
	
}
