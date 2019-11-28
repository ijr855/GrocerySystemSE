package grocerysys.action;

import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport{

	private String username;
	private String password;

	public String checkLogin() {
		if (username.equals("Username") && password.equals("Password")) return SUCCESS; 
		else return ERROR;
	}
	
	public void validate() {
		if (username == null || username.trim().equals("")) {
			addFieldError("username", "A username is required");
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
}
