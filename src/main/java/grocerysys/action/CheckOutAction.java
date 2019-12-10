package grocerysys.action;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.math.*;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import grocerysys.model.Item;
import grocerysys.model.Order;

public class CheckOutAction extends ActionSupport implements SessionAware {
	
	private String[] deliveryTimes = {"10AM-11AM", 
									  "11AM-12PM",
									  "12PM-1PM",
									  "1PM-2PM",
									  "2PM-3PM",
									  "4PM-5PM",
									  "6PM-7PM"};
	
	private String selectedTime;
	
	private String[] deliveryOptions = { "Same day delivery ($3.99)",
										 "Next day delivery ($1.99)",
										 "Two day delivery (FREE)" };
	
	private String selectedDelivery;
	
	private Map<String, Object> userSession;
	private List<Item> cart;
	private double total, ship;
	private String code = "";
	
	public String confirmOrder() {
		// Get user items currently in cart, as all should be being purchased. Put in list.
		Connection conn = null; // Establish db connection
		String url = "jdbc:mysql://localhost:3306/";
		String dbName = "user/customer";
		String driver = "com.mysql.jdbc.Driver";
		String userName = "root";
		String password = "";
		
		String userID = (String) userSession.get("currentUserID"); // Get user ID from session
		String name, category; // Used later to build user cart from DB queries
		int itemID, quantity, newOrderID = -1, newQt, days = 0;
		double price;
		total = 0;
		
		try {
			// Populate user cart
			Class.forName(driver).newInstance(); 
			conn = DriverManager.getConnection(url+dbName,userName,password);
			String query = "Select * FROM cart WHERE `userId` = '" + userID + "'"; // First pull all rows from cart table belonging to current user.
			Statement cartStatement = conn.createStatement();
			ResultSet cartRS= cartStatement.executeQuery(query);
			
			// Get most recent user order number. Increment by one.
			Statement orderIDStatement = conn.createStatement();
			query = "Select Max(OrderID) FROM orders";
			ResultSet orderIDRS = orderIDStatement.executeQuery(query);
			orderIDRS.next();
			newOrderID = orderIDRS.getInt(1);
			newOrderID = newOrderID + 1;
			
			cart = new ArrayList();
			
			while (cartRS.next())
			{
				quantity = cartRS.getInt("itemQuantity");
				itemID = cartRS.getInt("itemid");
				query = "Select * FROM items WHERE `item_ID` = '" + itemID + "'"; // Pull item details from Item table
				System.out.println(query);
				Statement itemStatement = conn.createStatement();
				ResultSet itemRS = itemStatement.executeQuery(query);
				itemRS.next();
				newQt = itemRS.getInt("Quantity");
				newQt = newQt - quantity;
				if (newQt < 0) {
					quantity = quantity + newQt;
					newQt = 0;
				}
				// UPDATE item counts for item table by subtracting qt from each item in list from item table qt
				query = "UPDATE items SET `Quantity` = '" + newQt + "' WHERE `item_ID` = '" + itemID + "'";
				System.out.println(query);
				itemStatement = conn.createStatement();
				itemStatement.executeUpdate(query);
				price = itemRS.getDouble("Price");
				name = itemRS.getString("Name");
				category = itemRS.getString("Category");
				Item toAdd = new Item(name, category, price, quantity, itemID); // Create item details using info from query
				System.out.println(toAdd.toString());
				cart.add(toAdd);
				itemStatement.close();
			} //end while
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String date = sdf.format(new Date()); 
			Calendar c = Calendar.getInstance();
			c.setTime(sdf.parse(date));
			c.add(Calendar.DATE, days);
			date = sdf.format(c.getTime());
			Order newOrder = new Order(Integer.toString(newOrderID), userID, cart, selectedTime, date);
			for (int i = 0; i < deliveryOptions.length; i++) {
				if (selectedDelivery.equals(deliveryOptions[i])) {
					days = i;
					switch(i) {
					case 0:
						newOrder.setTotal(3.99);
						ship = 3.99;
						break;
					case 1:
						newOrder.setTotal(1.99);
						ship = 1.99;
						break;
					default:
						break;
					}
				}
				
			}
			
			newOrder.calcTotal();
			total = newOrder.getTotal();
			newOrder.pushOrder(selectedDelivery);
			newOrder.cleanCart();
			conn.close();
		} //end try
		catch(ClassNotFoundException e) 
		{
			e.printStackTrace();
		}
		catch(SQLException e) 
		{
			e.printStackTrace();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}						
		return SUCCESS;
	}
	
	public void validate() {
		Connection conn = null; // Establish db connection
		String url = "jdbc:mysql://localhost:3306/";
		String dbName = "user/customer";
		String driver = "com.mysql.jdbc.Driver";
		String userName = "root";
		String password = "";
		if (code.isEmpty()) return; 
		try {
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(url+dbName,userName,password);
			String query = "SELECT * FROM promos WHERE `Code` = '" + code + "'"; 
			System.out.println(query);
			Statement stmt = conn.createStatement();
			ResultSet codeRS = stmt.executeQuery(query);
			if (!codeRS.next()){
				addFieldError("code", "Invalid checkout code.");
			} else {
				; // Apply Discount
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
	
	public String checkOut() {
		return SUCCESS;
	}
	public String pay() {
		return SUCCESS;
	}

	public String[] getDeliveryTimes() {
		return deliveryTimes;
	}

	public void setDeliveryTimes(String[] deliveryTimes) {
		this.deliveryTimes = deliveryTimes;
	}

	public String getSelectedTime() {
		return selectedTime;
	}

	public void setSelectedTime(String selectedTime) {
		this.selectedTime = selectedTime;
	}

	public String[] getDeliveryOptions() {
		return deliveryOptions;
	}

	public void setDeliveryOptions(String[] deliveryOptions) {
		this.deliveryOptions = deliveryOptions;
	}

	public String getSelectedDelivery() {
		return selectedDelivery;
	}

	public void setSelectedDelivery(String selectedDelivery) {
		this.selectedDelivery = selectedDelivery;
	}
	
	public void setSession(Map<String, Object> session) {
		userSession = session ;
	}

	public Map<String, Object> getUserSession() {
		return userSession;
	}

	public List<Item> getCart() {
		return cart;
	}

	public void setCart(List<Item> cart) {
		this.cart = cart;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public double getShip() {
		return ship;
	}

	public void setShip(double ship) {
		this.ship = ship;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
