package grocerysys.model;

public class Promo {

	private String code;
	private int itemID;
	private String itemName;
	private String type;
	private double discount;
	
	public Promo (String code, int itemID, String itemName, int type, double discount){
		this.code = code;
		this.itemID = itemID;
		this.itemName = itemName;
		this.discount = discount;
		if (type == 1) {
			this.type = "%";
		} else {
			this.type = "$";
		}
	}
	
	public String toString(){
		return this.code + " " + this.itemID + " " + itemName + " " + type + " " + discount;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getItemID() {
		return itemID;
	}

	public void setItemID(int itemID) {
		this.itemID = itemID;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}
}
