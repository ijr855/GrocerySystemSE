package grocerysys.action;
import java.sql.*;

public class GroceryAction {
	
	public GroceryAction() {
	System.out.println("MySQL Connect Example.");
	Connection conn = null;String url = "jdbc:mysql://localhost:3306/";
	//user/customer is the connection to the overall database
	String dbName = "user/customer";
	String driver = "com.mysql.jdbc.Driver";
	String userName = "root";
	String password = "";
	String f1,f2,f3,f4,f5;
	try {
		Class.forName(driver).newInstance();
		conn = DriverManager.getConnection(url+dbName,userName,password);
		//in the query, its Select * FROM (table you wish to read from)
		String query = "Select * FROM items";
		System.out.println("Connected to the database");
		Statement stmt = conn.createStatement();
		ResultSet rs= stmt.executeQuery(query);
		
		//value per collumn in the table, can change based on how many columns there are
		while (rs.next())
		{
			f1 = rs.getString(1);
			f2 = rs.getString(2);
			f3 = rs.getString(3);
			f4 = rs.getString(4);
			f5 = rs.getString(5);
			
			System.out.println(f1 + " " + f2 + " " + f3+ " " + f4+ " " + f5); 
		} //end while
		conn.close();
		System.out.println("Disconnected from database");
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
	}

}
