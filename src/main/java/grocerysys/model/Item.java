package grocerysys.model;

public class Item {

	String name;
	int ID;
	String category;
	double price;
	
	
	public Item(String sName, int iID, String cat, double prc){
		this.name = sName;
		this.ID = iID;
		this.category = cat;
		this.price = prc;
	}

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getID() {
		return ID;
	}


	public void setID(int iD) {
		ID = iD;
	}


	public String getCategory() {
		return category;
	}


	public void setCategory(String category) {
		this.category = category;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}

	
	
}
