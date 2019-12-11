package grocerysys.action;
import java.util.*;
//import com.opensymphony.xwork2.interceptor.ParameterNameAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import java.sql.*;

// Simple class made to simply validate if the user has entered their information correctly on login
public class LoginAction extends ActionSupport implements SessionAware{

	
	private String username;
	private String password;
	private boolean loggedin = false; //this is for testing purposes in junit

	private Map<String, Object> userSession ;

	public void setSession(Map<String, Object> session) {
	   userSession = session ;
	}
	public String checkLogin() 
	{
		//check if username and password match
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
			sql = "SELECT username,password,id from customer";
			ResultSet rs = stmt.executeQuery(sql);
			
			System.out.println(username);
			System.out.println(password);
			// Would be nice to securely hash and salt these but... not in our scope right now!
			while(rs.next())
			{
				uname = rs.getString("username");
				mypass = rs.getString("password");

				if(username.equals(uname) && password.equals(mypass))
				{
					System.out.println("Success");
					userSession.put("currentUserID", rs.getString(3));
					System.out.println("Success");
					loggedin = true;
					return SUCCESS;
				}
			}
			conn.close();
		}catch (Exception e) 
		{
			e.printStackTrace();
		}
	
		return ERROR;
	}
	
	public void validate() {
		if (username == null || username.trim().equals("")) {
			addFieldError("username", "A username is required");
		} 
		
		if (checkLogin() == ERROR){
			addFieldError("username", "Username or Password is invalid");
		}
		
		if (password == null || password.trim().equals("")) {
			addFieldError("password", "A password is required");
		}
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	public Map<String, Object> getUserSession() {
		return userSession;
	}
	public boolean isLoggedin() {
		return loggedin;
	}
	public void setLoggedin(boolean loggedin) {
		this.loggedin = loggedin;
	}

}
