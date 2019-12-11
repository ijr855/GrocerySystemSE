package grocerysys.action;

import com.opensymphony.xwork2.ActionSupport;

// All this class really does is contain the class to send the user back to the index screen.
public class HomeAction extends ActionSupport{

	// Very complex algorithm, be careful in reading and comprehending it.
	public String logOut() {
		return SUCCESS;
	}
	
}
