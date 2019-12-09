package grocerysys.model;

import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import grocerysys.model.Item;
import java.util.Date;

public class Order {
	private String orderID;
	private String customerID;
	private List<Item> cart;
	private String deliveryTime;
	private String deliveryDate;
	
	public Order (String orderID, String customerID, List<Item> cart, String deliveryTime, String deliveryDate) {
		this.orderID = orderID;
		this.customerID = customerID;
		this.cart = cart;
		this.deliveryTime = deliveryTime;
		this.deliveryDate = deliveryDate;
	}
	
	public void pushOrder() {
		Connection conn = null; // Establish db connection
		String url = "jdbc:mysql://localhost:3306/";
		String dbName = "user/customer";
		String driver = "com.mysql.jdbc.Driver";
		String userName = "root";
		String password = "";
		
		try {
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(url+dbName,userName,password);
			for (Item curItem : this.cart) {
				String query = "INSERT INTO orders (`custID`, `orderID`, `item_ID`, `Quantity`, `Price`, `deliveryTime`, `deliveryDate`) VALUES ('";
				query = query + this.customerID + "', '" + this.orderID + "', '" + curItem.getID() + "', '" + curItem.getQt() + "', '" + curItem.getPrice() + "', '" + this.deliveryTime + "', '" + this.deliveryDate + "')";
				Statement stmt = conn.createStatement();
				stmt.executeUpdate(query);
			}
		} catch(ClassNotFoundException e) 
		{
			e.printStackTrace();
			return;
		}
		catch(SQLException e) 
		{
			e.printStackTrace();
			return;
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			return;
		}
	}
	
	public void cleanCart() {
		Connection conn = null; // Establish db connection
		String url = "jdbc:mysql://localhost:3306/";
		String dbName = "user/customer";
		String driver = "com.mysql.jdbc.Driver";
		String userName = "root";
		String password = "";
		
		try {
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(url+dbName,userName,password);
			for (Item curItem : this.cart) {
				String query = "DELETE FROM cart WHERE userID = " + this.customerID;
				Statement stmt = conn.createStatement();
				stmt.executeUpdate(query);
			}
		} catch(ClassNotFoundException e) 
		{
			e.printStackTrace();
			return;
		}
		catch(SQLException e) 
		{
			e.printStackTrace();
			return;
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			return;
		}
	}
	
}
