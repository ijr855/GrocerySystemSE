package grocerysys.model;

/** The item class is a fundamental building block class. Items are used throughout the entire application.
 * They are stored as part of orders, promos require order objects to be applied properly, and they even have their own table in the db.
 * As such, their actual functionality is rather sparse, with only a constructor and toString function really existing.
 */
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
