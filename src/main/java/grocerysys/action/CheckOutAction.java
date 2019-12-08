package grocerysys.action;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class CheckOutAction extends ActionSupport {
	
	private String[] deliveryTimes = {"10AM-11AM", 
									  "11AM-12PM",
									  "12PM-1PM",
									  "1PM-2PM",
									  "2PM-3PM",
									  "4PM-5PM",
									  "6PM-7PM"};
	
	private String selectedTime;
	
	private String[] deliveryOptions = { "Same day delivery ($3.99)",
										 "Next day delivery ($1.99)",
										 "Two day delivery (FREE)" };
	
	private String selectedDelivery;
	
	public String confirmOrder() {
		System.out.println(selectedDelivery + " " + selectedTime);
		// Get user items currently in cart, as all should be being purchased. Put in list.
		
		// Get most recent user order number. Increment by one.
		
		// Push all order info to the order table. Go through user item list gen'd earlier and insert one by one.
		
		// DROP all items for user from cart table
		
		// UPDATE item counts for item table by subtracting qt from each item in list from item table qt.
		
		// Compute total of order, assign to field. Display alongside iterated list on receipt page.
		return SUCCESS;
	}
	
	public String checkOut() {
		return SUCCESS;
	}

	public String[] getDeliveryTimes() {
		return deliveryTimes;
	}

	public void setDeliveryTimes(String[] deliveryTimes) {
		this.deliveryTimes = deliveryTimes;
	}

	public String getSelectedTime() {
		return selectedTime;
	}

	public void setSelectedTime(String selectedTime) {
		this.selectedTime = selectedTime;
	}

	public String[] getDeliveryOptions() {
		return deliveryOptions;
	}

	public void setDeliveryOptions(String[] deliveryOptions) {
		this.deliveryOptions = deliveryOptions;
	}

	public String getSelectedDelivery() {
		return selectedDelivery;
	}

	public void setSelectedDelivery(String selectedDelivery) {
		this.selectedDelivery = selectedDelivery;
	}

}
