package grocerysys.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

// Roughest class in the entire project as far as use of framework. Really just contains functions to visit homepage, login, and account creation.
public class IndexAction extends ActionSupport implements SessionAware {

	private Map<String, Object> userSession ;

	public void setSession(Map<String, Object> session) {
		userSession = session ;
	}

	// Open login
	public String loadLogin() {	
		return SUCCESS;
	}

	// Open account creation.
	public String loadCreate() {
		return SUCCESS;
	}

	public Map<String, Object> getUserSession() {
		return userSession;
	}

	public void setUserSession(Map<String, Object> userSession) {
		this.userSession = userSession;
	}

	// Open homepage
	public String goHome() {
		return SUCCESS;
	}
}
