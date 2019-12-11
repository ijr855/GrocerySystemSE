package grocerysys.action;
import java.sql.*;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;
import com.opensymphony.xwork2.ActionSupport;

public class CreateAccountAction extends ActionSupport implements SessionAware{
	
	String firstName;
	String lastName;
	String addr;
	String payment;
	String crn;
	String uname;
	String pword;
	
	private Map<String, Object> userSession;

	// Simple concept. Takes values from fields on account creation and inserts them into the customer table. Assigns UID
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
				userSession.put("currentUserID", uid);
			}
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

	public void setSession(Map<String, Object> session) {
		   userSession = session ;
	}
	
	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getAddr() {
		return addr;
	}


	public void setAddr(String addr) {
		this.addr = addr;
	}


	public String getPayment() {
		return payment;
	}


	public void setPayment(String payment) {
		this.payment = payment;
	}


	public String getCrn() {
		return crn;
	}


	public void setCrn(String crn) {
		this.crn = crn;
	}


	public String getUname() {
		return uname;
	}


	public void setUname(String uname) {
		this.uname = uname;
	}


	public String getPword() {
		return pword;
	}


	public void setPword(String pword) {
		this.pword = pword;
	}
	
}
