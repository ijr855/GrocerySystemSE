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
			System.out.println(query);
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
		
		try {
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(url+dbName,userName,password);
			
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
	
}
