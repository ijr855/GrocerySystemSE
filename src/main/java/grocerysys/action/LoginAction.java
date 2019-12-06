package grocerysys.action;
import java.util.*;
import com.opensymphony.xwork2.interceptor.ParameterNameAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import java.sql.*;

public class LoginAction extends ActionSupport{

	
	private String username;
	private String password;


//	private Map<String, Object> userSession ;
//
//	public void setSession(Map<String, Object> session) {
//	   userSession = session ;
//	}
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
			sql = "SELECT username,password from customer";
			ResultSet rs = stmt.executeQuery(sql);
			
			System.out.println(username);
			System.out.println(password);
			
			while(rs.next())
			{
				uname = rs.getString("username");
				mypass = rs.getString("password");
				System.out.println();
				System.out.println(uname);
				System.out.println(mypass);
				
				if(username.equals(uname) && password.equals(mypass))
				{
					System.out.println("Success");
					return SUCCESS;
				}
			}
			conn.close();
		}catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		//if (username.equals("Username") && password.equals("Password")) 
			//return SUCCESS; 
		//else return ERROR;
		
		return ERROR;
	}
	
	public void validate() {
		if (username == null || username.trim().equals("")) {
			addFieldError("username", "A username is required");
		} 
		
		if (password == null || password.trim().equals("")) {
			addFieldError("password", "A password is required");
		}
	}
	
//	private void increaseHelloCount() {
//	    Integer helloCount = (Integer) userSession.get("helloCount");
//
//	    
//	    if (helloCount == null ) {
//	        helloCount = 1;
//	    } else {
//	        helloCount++;
//	    }
//
//	    userSession.put("helloCount", helloCount);
//	}
	
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
//	public Map<String, Object> getUserSession() {
//		return userSession;
//	}
//	public void setUserSession(Map<String, Object> userSession) {
//		this.userSession = userSession;
//	}
}
