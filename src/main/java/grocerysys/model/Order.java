package grocerysys.model;

import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import grocerysys.model.Item;
import java.util.Date;
/**
 * The order class is interesting and has plenty of information within it. It is primarily composed of a cart, alongside 
 * a few key details selected mostly at checkout/assigned to the user as they use the application. Of note are multiple constructors,
 * for building orders and carts alike, and functions to facilitate neat transfer of carts to order tables.
 *
 */
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
	
	// Calculates total of the order. Useful for when we need to apply promos.
	public void calcTotal(){
		for (Item curItem : this.cart){
			total = total + (curItem.getPrice() * curItem.qt);
			System.out.println(this.total);
		}
		
	}
	
	// Pushes order information to two databases: ordertracking and orders. ordertracking is higher level, orders is individual items in orders.
	public void pushOrder(String selectedDelivery) {
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
			for (Item curItem : this.cart) { // Push every item to the orders table
				query = "INSERT INTO orders (`custID`, `orderID`, `item_ID`, `Quantity`, `Price`, `deliveryTime`, `deliveryDate`, `Status`) VALUES ('";
				query = query + this.customerID + "', '" + this.orderID + "', '" + curItem.getID() + "', '" + curItem.getQt() + "', '" + curItem.getPrice() + "', '" + this.deliveryTime + "', '" + this.deliveryDate + "', 'Processing')";
				stmt = conn.createStatement();
				stmt.executeUpdate(query);
			} // Push the order into ordertracking.
			query = "INSERT INTO ordertracking (`total`, `customerID`, `orderID`, `status`, `deliverSpeed`, `deliveryTime`, `deliveryDate`) VALUES ('";
			query = query + this.total + "', '" + this.customerID + "', '" + this.orderID + "', 'Processing', '" + selectedDelivery + "', '" + this.deliveryTime + "', '" + this.deliveryDate + "')" ;
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
	
	// cleanCart purges the cart table from items relating to this user, effectively clearing the user's cart.
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

	/**
	 *  Apply discount is a static class used when validating discount codes. If a discount code is valid, this is called
	 *  and it attempts to apply the proper discount to the proper item. Returns true on success, returns false on failure. 
	 *  Failures can be connection/query failures, but logically they are intended to be instances where item which code
	 *  applies to does not exist in cart. Discount is applied by updating item's price in user's cart.
	 *  Parameters mainly describe the details of the discount code:
	 *  discount = degree of discount
	 *  itemID = item to apply discount to
	 *  type = type of discount. 1 is a percentage based discount, 2 is a flat subtraction discount.
	 *  userID = user to apply discount to
	 */
	public static boolean applyDiscount(double discount, int itemID, int type, String userID){
		Connection conn = null; // Establish db connection
		String url = "jdbc:mysql://localhost:3306/";
		String dbName = "user/customer";
		String driver = "com.mysql.jdbc.Driver";
		String userName = "root";
		String password = ""; 
		try {
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(url+dbName,userName,password);
			String query = "SELECT price FROM cart WHERE `itemId` = '" + itemID + "'"; // Get item from cart.
			Statement stmt = conn.createStatement();
			ResultSet codeRS = stmt.executeQuery(query);
			if(codeRS.next()){ // If item is in cart, then apply discount with UPDATE
				double newPrice = codeRS.getDouble(1);
				if (type == 2){
					// Flat discount
					newPrice = newPrice - discount;
				} else {
					// Percent discount		
					discount = discount / 100;
					newPrice = newPrice * (1-discount);
				}
				query = "UPDATE cart SET `price` = '" + newPrice + "' WHERE `itemId` = '" + itemID + "' AND userId = '" + userID + "'";
				stmt = conn.createStatement();
				stmt.executeUpdate(query);
			} else { // Otherwise...
				return false; // Item was not in cart.
			}
			
			conn.close();
		} catch(ClassNotFoundException e) 
		{
			e.printStackTrace();
			return false;
		}
		catch(SQLException e) 
		{
			e.printStackTrace();
			return false;
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			return false;
		}
		return true;
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
