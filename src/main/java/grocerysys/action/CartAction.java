package grocerysys.action;
import java.sql.*;
import java.util.*;
import com.opensymphony.xwork2.ActionSupport;
import grocerysys.model.Item;
import org.apache.struts2.interceptor.SessionAware;

public class CartAction extends ActionSupport implements SessionAware {

	private Map<String, Object> userSession;
	
	public String viewCart() {
		return SUCCESS;
	}
	
	public void setSession(Map<String, Object> session) {
		userSession = session ;
	}
}
