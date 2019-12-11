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

// This class handles user order confirmation and details and shipping the information where it needs to be.
public class CheckOutAction extends ActionSupport implements SessionAware {
	
	private String[] deliveryTimes = {"10AM-11AM", // Used for time range dropdown menu on order confirmation
									  "11AM-12PM",
									  "12PM-1PM",
									  "1PM-2PM",
									  "2PM-3PM",
									  "4PM-5PM",
									  "6PM-7PM"}; 
	
	private String selectedTime; // Stores dropdown menu choice
	
	private String[] paymentOptions = {"Credit Card", // Used for payment option dropdown menu on order confirmation page
										"Paypal",
										"Store Credit"};
	private String selectedPayment; // Store user payment choice
	
	private String[] deliveryOptions = { "Same day delivery ($3.99)", // Radio button options on order confirmation page. Notice a pattern yet?
										 "Next day delivery ($1.99)",
										 "Two day delivery (FREE)" };
	
	private String selectedDelivery; // Stores... you guessed it! User radio button choice
	
	private Map<String, Object> userSession; // Used to access session variable, userID
	private List<Item> cart; // List of items for use when creating our order object 
	private double total, ship; // Order cost total, shipping charge.
	private String code = ""; // Discount code storage.
	
	/* This method is called when the user finishes selecting their form fields on order confirmation.
	 * It queries the "cart" table for all items matching current user's ID, puts them in the cart list, then uses
	 * this to build an order object, which then stores information about itself in the order and orderTracking tables.
	 */
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
			query = "Select Max(OrderID) FROM orders"; // Get current highest order ID to determine new order ID
			ResultSet orderIDRS = orderIDStatement.executeQuery(query);
			orderIDRS.next();
			newOrderID = orderIDRS.getInt(1);
			newOrderID = newOrderID + 1;
			
			cart = new ArrayList();
			
			while (cartRS.next())
			{
				quantity = cartRS.getInt("itemQuantity");
				itemID = cartRS.getInt("itemid");
				query = "Select * FROM items WHERE `item_ID` = '" + itemID + "'"; // Pull item details from Item table for populating objects.
				System.out.println(query);
				Statement itemStatement = conn.createStatement();
				ResultSet itemRS = itemStatement.executeQuery(query);
				itemRS.next();
				newQt = itemRS.getInt("Quantity"); // newQt is here to be used for updating our availability count of an item in the "item" table
				newQt = newQt - quantity;
				if (newQt < 0) {
					quantity = quantity + newQt;
					newQt = 0;
				}
				query = "UPDATE items SET `Quantity` = '" + newQt + "' WHERE `item_ID` = '" + itemID + "'";
				itemStatement = conn.createStatement();
				itemStatement.executeUpdate(query);
				price = cartRS.getDouble("price");
				name = itemRS.getString("Name");
				category = itemRS.getString("Category");
				Item toAdd = new Item(name, category, price, quantity, itemID); // Create item details using info from query
				cart.add(toAdd); // Add new item to cart
				itemStatement.close();
			} //end while
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // Pulling date to determine our estimated delivery date.
			String date = sdf.format(new Date()); 
			Calendar c = Calendar.getInstance();
			c.setTime(sdf.parse(date));
			c.add(Calendar.DATE, days);
			date = sdf.format(c.getTime());
			Order newOrder = new Order(Integer.toString(newOrderID), userID, cart, selectedTime, date); // Build order with cart and other info.
			for (int i = 0; i < deliveryOptions.length; i++) { // Determine what to charge for shipping.
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
			newOrder.pushOrder(selectedDelivery); // Stores order info in tables
			newOrder.cleanCart(); // Purges order from cart table
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
	
	// Validate function here is used exclusively to do two things: Check the validity of a discount code, and apply it to the cart.
	public void validate() {
		Connection conn = null; // Establish db connection
		String url = "jdbc:mysql://localhost:3306/";
		String dbName = "user/customer";
		String driver = "com.mysql.jdbc.Driver";
		String userName = "root";
		String password = "";
		String userID = (String) userSession.get("currentUserID"); // Get user ID from session
		if (code.isEmpty()) return; // If we don't have a code, ignore validation
		try {
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(url+dbName,userName,password);
			String query = "SELECT * FROM promos WHERE `Code` = '" + code + "'"; // Check for code
			System.out.println(query);
			Statement stmt = conn.createStatement();
			ResultSet codeRS = stmt.executeQuery(query);
			if (!codeRS.next()){ // If code is not valid, add a field error for it.
				addFieldError("code", "Invalid checkout code.");
			} else { // Apply if the code exists
				boolean inCart = Order.applyDiscount(codeRS.getDouble(2), codeRS.getInt(3), codeRS.getInt(4), userID); // Apply Discount
				if (!inCart){
					conn.close();
					addFieldError("code", "Item this code applies to is not in your cart.");
				}
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
	
	public String[] getPaymentOptions() {
		return paymentOptions;
	}

	public void setPaymentOptions(String[] paymentOptions) {
		this.paymentOptions = paymentOptions;
	}

	public String getSelectedPayment() {
		return selectedPayment;
	}

	public void setSelectedPayment(String selectedPayment) {
		this.selectedPayment = selectedPayment;
	}

}
