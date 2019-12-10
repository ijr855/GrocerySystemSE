package grocerysys.action;
import java.sql.*;
import java.util.*;
import com.opensymphony.xwork2.ActionSupport;
import grocerysys.model.Item;
import org.apache.struts2.interceptor.SessionAware;
import grocerysys.model.Order;

public class TrackAction extends ActionSupport implements SessionAware{

	private Map<String, Object> userSession;
	private List<Order> userOrders;
	private List<Item> orderItems;
	private String selectedOrderID, selectedTotal, selectedDeliverSpeed, selectedDeliveryDate, selectedDeliveryTime;
	
	public String getOrders() {
		Connection conn = null;
		String url = "jdbc:mysql://localhost:3306/";
		String dbName = "user/customer";
		String driver = "com.mysql.jdbc.Driver";
		String userName = "root";
		String password = "";
		String userID = (String) userSession.get("currentUserID");
		System.out.println(userID);
		String query = null;
		userOrders = new ArrayList<Order>();
		
		try {
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(url+dbName,userName,password);
			query = "SELECT * FROM ordertracking WHERE customerID = " + userID;
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				double total = rs.getDouble(1);
				String orderID = rs.getString(3);
				String status = rs.getString(4);
				String deliverSpeed = rs.getString(5);
				String deliveryTime = rs.getString(6);
				String deliveryDate = rs.getString(7);
				Order curOrder = new Order(orderID, userID, deliveryTime, deliveryDate, deliverSpeed, total);
				userOrders.add(curOrder);
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
	
	public String getOrderDetails() {
		Connection conn = null;
		String url = "jdbc:mysql://localhost:3306/";
		String dbName = "user/customer";
		String driver = "com.mysql.jdbc.Driver";
		String userName = "root";
		String password = "";
		String userID = (String) userSession.get("currentUserID");
		System.out.println(userID);
		String query = null;
		orderItems = new ArrayList<Item>();
		
		try {
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(url+dbName,userName,password);
			query = "SELECT `Item_ID`, `Quantity`, `Price` FROM orders WHERE CustID = " + userID + " AND orderID = " + selectedOrderID;
			Statement orderStatement = conn.createStatement();
			ResultSet orderRS = orderStatement.executeQuery(query);
			while(orderRS.next()) {
				int itemID = orderRS.getInt(1);
				int qt = orderRS.getInt(2);
				double price = orderRS.getDouble(3);
				query = "SELECT `Name`, `Category` FROM items WHERE Item_ID = " + itemID;
				Statement itemStatement = conn.createStatement();
				ResultSet itemRS = itemStatement.executeQuery(query);
				itemRS.next();
				String name = itemRS.getString(1);
				if (name.length() >=8) name = name.substring(0, 7);
				String category = itemRS.getString(2);
				Item toAdd = new Item(name, category, price, qt, itemID);
				orderItems.add(toAdd);
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
	
	public void setSession(Map<String, Object> session) {
		userSession = session ;
	}

	public List<Order> getUserOrders() {
		return userOrders;
	}

	public void setUserOrders(List<Order> userOrders) {
		this.userOrders = userOrders;
	}

	public String getSelectedOrderID() {
		return selectedOrderID;
	}

	public void setSelectedOrderID(String selectedOrderID) {
		this.selectedOrderID = selectedOrderID;
	}

	public List<Item> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<Item> orderItems) {
		this.orderItems = orderItems;
	}

	public String getSelectedTotal() {
		return selectedTotal;
	}

	public void setSelectedTotal(String selectedTotal) {
		this.selectedTotal = selectedTotal;
	}

	public String getSelectedDeliverSpeed() {
		return selectedDeliverSpeed;
	}

	public void setSelectedDeliverSpeed(String selectedDeliverSpeed) {
		this.selectedDeliverSpeed = selectedDeliverSpeed;
	}

	public String getSelectedDeliveryDate() {
		return selectedDeliveryDate;
	}

	public void setSelectedDeliveryDate(String selectedDeliveryDate) {
		this.selectedDeliveryDate = selectedDeliveryDate;
	}

	public String getSelectedDeliveryTime() {
		return selectedDeliveryTime;
	}

	public void setSelectedDeliveryTime(String selectedDeliveryTime) {
		this.selectedDeliveryTime = selectedDeliveryTime;
	}
	
}
