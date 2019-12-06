package grocerysys.action;
import java.sql.*;
import java.util.*;
import com.opensymphony.xwork2.ActionSupport;
import grocerysys.model.Item;


public class GroceryAction extends ActionSupport {
	
	String Name;
	int ID;
	String cate;
	int quan;
	double price;
	private List products;
	
	
	
	public String GetGrocery() {
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
		//in the query, its Select * FROM (table you wish to read from)
		String query = "Select * FROM items";
		System.out.println("Connected to the database");
		Statement stmt = conn.createStatement();
		ResultSet rs= stmt.executeQuery(query);
		
		products = new ArrayList();
		
		//value per collumn in the table, can change based on how many columns there are
		while (rs.next())
		{
			Name = rs.getString(1);
			ID = rs.getInt(2);
			cate = rs.getString(3);
			quan = rs.getInt(4);
			price = rs.getDouble(5);
			
			products.add(new Item(Name,cate, price,quan));
			
			System.out.println(Name + " " + ID + " " + cate + " " + quan + " " + price); 
		} //end while
		conn.close();
//		System.out.println("Disconnected from database");
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

	public void setProducts(List products) {
		this.products = products;
	}
	

}
