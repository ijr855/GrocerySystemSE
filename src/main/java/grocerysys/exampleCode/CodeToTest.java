package grocerysys.exampleCode;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import grocerysys.model.Item;
import grocerysys.model.Order;


public class CodeToTest
{
	
	public boolean checkLogin(String username, String password)
	{
		Connection conn = null;
		String url = "jdbc:mysql://localhost:3306/";
		String dbName = "user/customer";
		String driver = "com.mysql.jdbc.Driver";
		String userName = "root";
		String pass = "";
		String uname,mypass;
		
		try {
			Class.forName(driver).newInstance();
			conn =DriverManager.getConnection(url+dbName,userName,pass);
			Statement stmt =conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql;
			sql = "SELECT username,password from customer";
			ResultSet rs = stmt.executeQuery(sql);
			
			System.out.println(username);
			System.out.println(password);
			
			while(rs.next())
			{
				uname = rs.getString("username");
				mypass = rs.getString("password");

				if(username.equals(uname) && password.equals(mypass))
				{
					return true;
				}
			}
			conn.close();
		}catch (Exception e) 
		{
			e.printStackTrace();
		}
	
		return false;
	}
	
	public String testAddToCart(String userID, String selectedID, String selectedPrice)
	{
		Connection conn = null;
		String url = "jdbc:mysql://localhost:3306/";
		String dbName = "user/customer";
		String driver = "com.mysql.jdbc.Driver";
		String userName = "root";
		String password = "";
		System.out.println(userID);
		String query = null;	
		try {
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(url+dbName,userName,password);
			query = "Select * FROM cart WHERE `userId` = '" + userID + "' AND `itemID` = '" + selectedID + "'";
			Statement cartStatement = conn.createStatement();
			ResultSet cartRS= cartStatement.executeQuery(query);
			
			if (!cartRS.next()) { // If the selected item is NOT present in the cart
				query = "INSERT INTO cart (`userID`, `itemID`, `itemQuantity`, `price`) VALUES ("; // Add 1 to the cart
				query = query + "'" + userID + "', '" + selectedID + "', '1', '" + selectedPrice +"')";
				Statement stmt = conn.createStatement();
				stmt.executeUpdate(query);
			} else { // If it is present, update the count
				int newQuant = cartRS.getInt("itemQuantity");
				newQuant = newQuant + 1;
				query = "UPDATE cart SET `itemQuantity` = " + newQuant + " WHERE `userId` = '" + userID + "' AND `itemID` = '" + selectedID + "'";
				Statement stmt = conn.createStatement();
				stmt.executeUpdate(query);
			}
			conn.close();
		} 
		catch(ClassNotFoundException e) 
		{
			e.printStackTrace();
			return "Did not add";
		}
		catch(SQLException e) 
		{
			e.printStackTrace();
			return "Did not add";
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			return "Did not add";
		}
		
		try
		{
			double checkPrice = Double.parseDouble(selectedPrice);
		}
		catch(NumberFormatException error)
		{
			return "Did not add";
		}
		
		
		return "Added to cart";
	}
	
	public String testCreateAccount(String firstName, String lastName, String addr, String payment, String crn, String uname, String pword)
	{
		
		System.out.println("MySQL Connect Example.");
		Connection conn = null;String url = "jdbc:mysql://localhost:3306/";
		//user/customer is the connection to the overall database
		String dbName = "user/customer";
		String driver = "com.mysql.jdbc.Driver";
		String userName = "root";
		String password = "";
		try {
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(url+dbName,userName,password);
			// Add user to user table
			String query = "INSERT INTO customer (`Name`, `Address`, `Payment Info`, `CRN`, `UserName`, `Password`) VALUES (";
			String fullName = "'" + firstName + " " +  lastName + "'";
			query = query + fullName + ", '" + addr + "', '" + payment + "', " + crn + ", '" + uname + "', '" + pword + "')";
			System.out.println("Connected to the database");
			Statement stmt = conn.createStatement();
			System.out.println(query);
			System.out.println(addr);
			stmt.executeUpdate(query);
			
			// Get newest user ID
			query = "SELECT `ID` FROM `customer` WHERE `UserName` = '" + uname + "'";
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next())
			{
				String uid = rs.getString("ID");
			}
			conn.close();
		} //end try
		catch(ClassNotFoundException e) 
		{
			e.printStackTrace();
			return "Did not add";
		}
		catch(SQLException e) 
		{
			e.printStackTrace();
			return "Did not add";
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			return "Did not add";
		}
		
		return "Created";
	}
	
	public String testCheckOut(int delivType, int delivTime, String userID)
	{
	
		
		String[] deliveryTimes = {"10AM-11AM", 
				  "11AM-12PM",
				  "12PM-1PM",
				  "1PM-2PM",
				  "2PM-3PM",
				  "4PM-5PM",
				  "6PM-7PM"};

		

		String[] deliveryOptions = { "Same day delivery ($3.99)",
					 "Next day delivery ($1.99)",
					 "Two day delivery (FREE)" };

		

		if(delivTime >= deliveryTimes.length || delivType > deliveryOptions.length )
			return "not carted";
		
		String selectedTime = deliveryTimes[delivTime];
		String selectedDelivery = deliveryOptions[delivType];
		Map<String, Object> userSession;
		List<Item> cart;
		double total, ship;
		Connection conn = null; // Establish db connection
		String url = "jdbc:mysql://localhost:3306/";
		String dbName = "user/customer";
		String driver = "com.mysql.jdbc.Driver";
		String userName = "root";
		String password = "";
		
		//String userID = (String) userSession.get("currentUserID"); // Get user ID from session
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
				price = itemRS.getDouble("Price");
				name = itemRS.getString("Name");
				category = itemRS.getString("Category");
				total = total + (price * quantity);
				Item toAdd = new Item(name, category, price, quantity, itemID); // Create item details using info from query
				System.out.println(toAdd.toString());
				cart.add(toAdd);
				itemStatement.close();
			} //end while
			for (int i = 0; i < deliveryOptions.length; i++) {
				if (selectedDelivery.equals(deliveryOptions[i])) {
					days = i;
					switch(i) {
					case 0:
						total = total + 3.99;
						ship = 3.99;
						break;
					case 1:
						total = total + 1.99;
						ship = 1.99;
						break;
					default:
						break;
					}
				}
				
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String date = sdf.format(new Date()); 
			Calendar c = Calendar.getInstance();
			c.setTime(sdf.parse(date));
			c.add(Calendar.DATE, days);
			date = sdf.format(c.getTime());
			Order newOrder = new Order(Integer.toString(newOrderID), userID, cart, selectedTime, date);
			newOrder.pushOrder(selectedDelivery);
			newOrder.cleanCart();
			conn.close();
		} //end try
		catch(ClassNotFoundException e) 
		{
			e.printStackTrace();
			return "not carted";
		}
		catch(SQLException e) 
		{
			e.printStackTrace();
			return "not carted";
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			return "not carted";
		}						
		return "cartted";
		
	}
}
