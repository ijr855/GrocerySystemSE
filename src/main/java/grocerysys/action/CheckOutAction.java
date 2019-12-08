package grocerysys.action;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class CheckOutAction extends ActionSupport {
	
	public String checkOut() {
		return SUCCESS;
	}

}
