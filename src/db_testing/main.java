package db_testing;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.sql.*;

public class main {

	public static void main(String[] args) throws ClassNotFoundException {
		try {
			ArrayList<customer> newn = new ArrayList<>();
			Class.forName("com.mysql.jdbc.Driver");
			ArrayList<Order> newm = new ArrayList<>();
			Connection con = (Connection) DriverManager
					.getConnection("jdbc:mysql://localhost:3306/project?characterEncoding=utf8", "root", "1234");
			Statement stmt = (Statement) con.createStatement();
			String query = "select * from customer";
			ResultSet rs = stmt.executeQuery(query);
//			  String insertQuery = "INSERT INTO customer (CustomerID, CustomerName, CustomerAddress, CustomerPhone, age) VALUES (?, ?, ?, ?, ?)";
//	          PreparedStatement preparedStatement = con.prepareStatement(insertQuery);
//	            
//	          preparedStatement.setInt(1,1112);
//	          preparedStatement.setString(2,"mohammad nemer");
//	          preparedStatement.setString(3,"mdmdsds");
//	          preparedStatement.setInt(4,03332322);
//	          preparedStatement.setInt(5,15);
//	          preparedStatement.executeUpdate();
	            
	            
			while (rs.next()) {

				customer n = new customer(rs.getInt("CustomerID"), rs.getString("CustomerName"),
						rs.getString("CustomerAddress"), rs.getString("CustomerPhone"), rs.getInt("age"));

				newn.add(n);

			}
			
			

			String v1 = "select * from order_Request";
			ResultSet r1 = stmt.executeQuery(v1);
			while (r1.next()) {

//				int customerID = r1.getInt("CustomerID");
//				int orderID = r1.getInt("OrderID");
//				java.sql.Date orderDate = r1.getDate("OrderDate");
//				String shippingAddress = r1.getString("ShippingAddress");
//				Order order = new Order(orderID, orderDate, shippingAddress);
//				order.setCustomer(customerID);

			}
			
		String pro="select * from product";
		
			
			

			for (int i = 0; i < newn.size(); i++) {
				System.out.println(newn.get(i).toString());
			}
//		          
//		            
//		            
//		            
//		            preparedStatement.setInt(1, 1222); 
//		            preparedStatement.setString(2, "mohammad nemer"); 
//		            preparedStatement.setString(3, "aaaaaaaaa");  
//		            preparedStatement.setString(4, "12121"); 
//		            preparedStatement.setInt(5, 22); 
//		            
//		            // Execute the insertion
//		            int rowsInserted = preparedStatement.executeUpdate();
//		             query = "select * from customer";
//		             rs = stmt.executeQuery(query);
//
//		            while (rs.next()) {
//		                System.out.println("CustomerID : " + rs.getString("CustomerID"));
//		            }

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
