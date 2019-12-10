package grocerysys.action;
import java.sql.*;
import java.util.*;
import com.opensymphony.xwork2.ActionSupport;
import grocerysys.model.Item;
import org.apache.struts2.interceptor.SessionAware;

public class CartAction extends ActionSupport implements SessionAware {

	private Map<String, Object> userSession;
	private List<Item> cart;
	private int selectedID;
	
	public String subtractItem() {
		Connection conn = null; // Establish db connection
		String url = "jdbc:mysql://localhost:3306/";
		String dbName = "user/customer";
		String driver = "com.mysql.jdbc.Driver";
		String userName = "root";
		String password = "";
		
		String userID = (String) userSession.get("currentUserID"); // Get user ID from session
		String name, category; // Used later to build user cart from DB queries
		int itemID, quantity;
		
		try {
			Class.forName(driver).newInstance(); 
			conn = DriverManager.getConnection(url+dbName,userName,password);
			String query = "Select `itemQuantity` FROM cart WHERE `userId` = '" + userID + "' AND `itemID` = '" + selectedID + "'"; 
			Statement itemStatement = conn.createStatement();
			ResultSet itemRS= itemStatement.executeQuery(query);
			
			while (itemRS.next())
			{
				quantity = itemRS.getInt(1);
				quantity = quantity - 1;
				if (quantity == 0) {
					Statement dropStmt = conn.createStatement();
					query = "DELETE FROM `cart` WHERE `itemID` = '" + selectedID + "'";
					dropStmt.executeUpdate(query);
				} else {
					Statement subStmt = conn.createStatement();
					query = "UPDATE cart SET `itemQuantity` = '" + quantity + "' WHERE `userID` = '" + userID + "' AND `itemID` = '" + selectedID + "'";
					subStmt.executeUpdate(query);
				}
			} //end while
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
	
	public String viewCart() {
		Connection conn = null; // Establish db connection
		String url = "jdbc:mysql://localhost:3306/";
		String dbName = "user/customer";
		String driver = "com.mysql.jdbc.Driver";
		String userName = "root";
		String password = "";
		
		String userID = (String) userSession.get("currentUserID"); // Get user ID from session
		String name, category; // Used later to build user cart from DB queries
		int itemID, quantity;
		double price;
		
		try {
			Class.forName(driver).newInstance(); 
			conn = DriverManager.getConnection(url+dbName,userName,password);
			String query = "Select * FROM cart WHERE `userId` = '" + userID + "'"; // First pull all rows from cart table belonging to current user.
			Statement cartStatement = conn.createStatement();
			ResultSet cartRS= cartStatement.executeQuery(query);
			
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
				price = itemRS.getDouble("Price");
				name = itemRS.getString("Name");
				category = itemRS.getString("Category");
				Item toAdd = new Item(name, category, price, quantity, itemID); // Create item details using info from query
				System.out.println(toAdd.toString());
				cart.add(toAdd);
				itemStatement.close();
			} //end while
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
	
	public void setSession(Map<String, Object> session) {
		userSession = session ;
	}

	public Map<String, Object> getUserSession() {
		return userSession;
	}

	public void setUserSession(Map<String, Object> userSession) {
		this.userSession = userSession;
	}

	public List<Item> getCart() {
		return cart;
	}

	public void setCart(List<Item> cart) {
		this.cart = cart;
	}

	public int getSelectedID() {
		return selectedID;
	}

	public void setSelectedID(int selectedID) {
		this.selectedID = selectedID;
	}
}
