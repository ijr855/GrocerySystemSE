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
