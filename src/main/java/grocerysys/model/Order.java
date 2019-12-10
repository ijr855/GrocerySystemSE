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
	private double total;
	private String deliverSpeed;
	
	public Order (String orderID, String customerID, List<Item> cart, String deliveryTime, String deliveryDate) {
		this.orderID = orderID;
		this.customerID = customerID;
		this.cart = cart;
		this.deliveryTime = deliveryTime;
		this.deliveryDate = deliveryDate;
	}
	
	public Order (String orderID, String customerID, String deliveryTime, String deliveryDate, String deliverSpeed, double total) {
		this.orderID = orderID;
		this.customerID = customerID;
		this.deliveryTime = deliveryTime;
		this.deliveryDate = deliveryDate;
		this.deliverSpeed = deliverSpeed;
		this.total = total;
	}
	
	public void pushOrder(double total, String selectedDelivery) {
		Connection conn = null; // Establish db connection
		String url = "jdbc:mysql://localhost:3306/";
		String dbName = "user/customer";
		String driver = "com.mysql.jdbc.Driver";
		String userName = "root";
		String password = "";
		
		try {
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(url+dbName,userName,password);
			Statement stmt;
			String query = null;
			for (Item curItem : this.cart) {
				query = "INSERT INTO orders (`custID`, `orderID`, `item_ID`, `Quantity`, `Price`, `deliveryTime`, `deliveryDate`, `Status`) VALUES ('";
				query = query + this.customerID + "', '" + this.orderID + "', '" + curItem.getID() + "', '" + curItem.getQt() + "', '" + curItem.getPrice() + "', '" + this.deliveryTime + "', '" + this.deliveryDate + "', 'Processing')";
				stmt = conn.createStatement();
				stmt.executeUpdate(query);
			}
			query = "INSERT INTO ordertracking (`total`, `customerID`, `orderID`, `status`, `deliverSpeed`, `deliveryTime`, `deliveryDate`) VALUES ('";
			query = query + total + "', '" + this.customerID + "', '" + this.orderID + "', 'Processing', '" + selectedDelivery + "', '" + this.deliveryTime + "', '" + this.deliveryDate + "')" ;
			System.out.println(query);
			stmt = conn.createStatement();
			stmt.executeUpdate(query);
			conn.close();
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

	public String getOrderID() {
		return orderID;
	}

	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}

	public String getCustomerID() {
		return customerID;
	}

	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}

	public List<Item> getCart() {
		return cart;
	}

	public void setCart(List<Item> cart) {
		this.cart = cart;
	}

	public String getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(String deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public String getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public String getDeliverSpeed() {
		return deliverSpeed;
	}

	public void setDeliverSpeed(String deliverSpeed) {
		this.deliverSpeed = deliverSpeed;
	}
	
}
