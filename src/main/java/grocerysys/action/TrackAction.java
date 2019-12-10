package grocerysys.action;
import java.sql.*;
import java.util.*;
import com.opensymphony.xwork2.ActionSupport;
import grocerysys.model.Item;
import org.apache.struts2.interceptor.SessionAware;
import grocerysys.model.Order;

public class TrackAction extends ActionSupport implements SessionAware{

	private Map<String, Object> userSession;
	
	public String getOrders() {
		return SUCCESS;
	}
	
	public String getOrderDetails() {
		return SUCCESS;
	}
	
	public void setSession(Map<String, Object> session) {
		userSession = session ;
	}
	
}
