package grocerysys.action;
import java.sql.*;
import com.opensymphony.xwork2.ActionSupport;

public class CreateAccountAction extends ActionSupport{
	
	String firstName;
	String lastName;
	String addr;
	String payment;
	String crn;
	String uname;
	String pword;
	

	public String createAccount() {
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
			String query = "INSERT INTO customer (Name, Address, Payment Info, CRN, UserName, Password) VALUES (";
			String fullName = firstName + lastName;
			query = query + fullName + ", " + addr + ", " + payment + ", " + crn + ", " + uname + ", " + pword + ")";
			System.out.println("Connected to the database");
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(query);
			conn.close();
		} //end try
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
	
}
