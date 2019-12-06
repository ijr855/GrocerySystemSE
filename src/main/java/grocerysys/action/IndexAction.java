package grocerysys.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class IndexAction extends ActionSupport implements SessionAware {

	private Map<String, Object> userSession ;

	public void setSession(Map<String, Object> session) {
		userSession = session ;
	}

	public String loadLogin() {	
		increaseHelloCount();
		return SUCCESS;
	}

	public String loadCreate() {
		return SUCCESS;
	}

	public Map<String, Object> getUserSession() {
		return userSession;
	}

	public void setUserSession(Map<String, Object> userSession) {
		this.userSession = userSession;
	}
	private void increaseHelloCount() {
		Integer helloCount = (Integer) userSession.get("helloCount");


		if (helloCount == null ) {
			helloCount = 1;
		} else {
			helloCount++;
		}

		userSession.put("helloCount", helloCount);
	}

}
