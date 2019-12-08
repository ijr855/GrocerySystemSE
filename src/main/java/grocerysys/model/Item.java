package grocerysys.model;

public class Item {

	String name;
	int ID;
	String category;
	double price;
	int qt;
	
	
	public Item(String sName, String cat, double prc, int quant, int id){
		this.name = sName;
		this.qt = quant;
		this.category = cat;
		this.price = prc;
		this.ID = id;
	}
	
	public String toString() {
		return this.name + " " + this.qt + " " + this.category + " " + this.price + " " + this.ID;
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

	public int getQt() {
		return qt;
	}

	public void setQt(int qt) {
		this.qt = qt;
	}

	
}
