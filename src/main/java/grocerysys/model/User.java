package grocerysys.model;

import java.util.Map;

public class User {
	private String username;
	private String password;
	private String address;
	
	private Map<String, Object> userSession ;

	public void setSession(Map<String, Object> session) {
		userSession = session ;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Map<String, Object> getUserSession() {
		return userSession;
	}

	public void setUserSession(Map<String, Object> userSession) {
		this.userSession = userSession;
	}
	
}
