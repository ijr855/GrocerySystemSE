package grocerysys.action;
import java.sql.*;
import java.util.*;
import com.opensymphony.xwork2.ActionSupport;
import grocerysys.model.Item;
import org.apache.struts2.interceptor.SessionAware;

// Class in charge of pulling from the item table to display to the user so that they may shop. Also handles searches.
public class GroceryAction extends ActionSupport implements SessionAware{
	
	private String Name; // Vars used for display.
	private int ID;
	private String cate;
	private int quan;
	private double price;
	private List<Item> products; // Full list of item objects for display.
	private String selectedItem, selectedCategory, selectedPrice, selectedQuantity, selectedID, searchVar = ""; // Used to know what the user wants to purchase/search for
	private Map<String, Object> userSession; // Used to access session variables.
	private boolean hasCart = false; // Used to determine whether to give the user access to viewing their cart.
	
	// Takes details of item where button was clicked and adds them to cart with simple insert/update statement varying based on whether item already exists in cart.
	public String addToCart() {
		Connection conn = null;
		String url = "jdbc:mysql://localhost:3306/";
		String dbName = "user/customer";
		String driver = "com.mysql.jdbc.Driver";
		String userName = "root";
		String password = "";
		String userID = (String) userSession.get("currentUserID");
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
			hasCart = true;
			conn.close();
		} 
		catch(ClassNotFoundException e) 
		{
			e.printStackTrace();
			return ERROR;
		}
		catch(SQLException e) 
		{
			e.printStackTrace();
			return ERROR;
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			return ERROR;
		}
		return SUCCESS;
	}
	
	// Checks the cart for any items belonging to user to determine if link to cart should be displayed.
	public String checkCart(){
		Connection conn = null;
		String url = "jdbc:mysql://localhost:3306/";
		String dbName = "user/customer";
		String driver = "com.mysql.jdbc.Driver";
		String userName = "root";
		String password = "";
		String userID = (String) userSession.get("currentUserID");
		System.out.println(userID);
		String query = null;
		
		try {
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(url+dbName,userName,password);
			query = "Select * FROM cart WHERE `userId` = '" + userID + "'";
			Statement cartStatement = conn.createStatement();
			ResultSet cartRS= cartStatement.executeQuery(query);		
			if (!cartRS.next()) { // Empty query results
				hasCart = false;
			} else { // If it is present, update the count
				hasCart = true;
			}
			conn.close();
		} 
		catch(ClassNotFoundException e) 
		{
			e.printStackTrace();
			return ERROR;
		}
		catch(SQLException e) 
		{
			e.printStackTrace();
			return ERROR;
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			return ERROR;
		}
		return SUCCESS;
	}
	
	// Simply pulls all item from the item database and prepares them for display.
	public String GetGrocery() {
	System.out.println("MySQL Connect Example.");
	Connection conn = null;
	String url = "jdbc:mysql://localhost:3306/";
	//user/customer is the connection to the overall database
	String dbName = "user/customer";
	String driver = "com.mysql.jdbc.Driver";
	String userName = "root";
	String password = "";
	
	try {
		Class.forName(driver).newInstance();
		conn = DriverManager.getConnection(url+dbName,userName,password);
		//in the query, its Select * FROM (table you wish to read from)
		String query = "Select * FROM items WHERE Name LIKE '" + searchVar + "%'";
		System.out.println("Connected to the database");
		Statement stmt = conn.createStatement();
		ResultSet rs= stmt.executeQuery(query);
		
		products = new ArrayList();
		while (rs.next())
		{
			Name = rs.getString(1);
			ID = rs.getInt(2);
			cate = rs.getString(3);
			quan = rs.getInt(4);
			price = rs.getDouble(5);
			
			products.add(new Item(Name,cate, price,quan, ID));
			
		} //end while
		conn.close();
		checkCart();
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

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getCate() {
		return cate;
	}

	public void setCate(String cate) {
		this.cate = cate;
	}

	public int getQuan() {
		return quan;
	}

	public void setQuan(int quan) {
		this.quan = quan;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public List getProducts() {
		return products;
	}

	public String getSelectedItem() {
		return selectedItem;
	}
	
	public void setSelectedID(String selectedID) {
		this.selectedID = selectedID;
	}

	public String getSelectedID() {
		return selectedID;
	}

	public void setSelectedItem(String selectedItem) {
		this.selectedItem = selectedItem;
	}

	public String getSelectedCategory() {
		return selectedCategory;
	}

	public void setSelectedCategory(String selectedCategory) {
		this.selectedCategory = selectedCategory;
	}

	public String getSelectedPrice() {
		return selectedPrice;
	}

	public void setSelectedPrice(String selectedPrice) {
		this.selectedPrice = selectedPrice;
	}

	public String getSelectedQuantity() {
		return selectedQuantity;
	}

	public void setSelectedQuantity(String selectedQuantity) {
		this.selectedQuantity = selectedQuantity;
	}
	
	public void setSession(Map<String, Object> session) {
		userSession = session ;
	}

	public boolean isHasCart() {
		return hasCart;
	}

	public void setHasCart(boolean hasCart) {
		this.hasCart = hasCart;
	}

	public void setProducts(List<Item> products) {
		this.products = products;
	}

	public String getSearchVar() {
		return searchVar;
	}

	public void setSearchVar(String searchVar) {
		this.searchVar = searchVar;
	}

}
