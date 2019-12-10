package grocerysys.action;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.opensymphony.xwork2.ActionSupport;

import grocerysys.model.Promo;


public class AdsAction extends ActionSupport{
	
	private List<Promo> promoList;
	
	public String loadAds(){
		promoList = new ArrayList<Promo>();
		Connection conn = null; // Establish db connection
		String url = "jdbc:mysql://localhost:3306/";
		String dbName = "user/customer";
		String driver = "com.mysql.jdbc.Driver";
		String userName = "root";
		String password = "";
		try {
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(url+dbName,userName,password);
			String query = "SELECT * FROM promos"; 
			Statement stmt = conn.createStatement();
			ResultSet codeRS = stmt.executeQuery(query);
			while(codeRS.next()){
				String curCode = codeRS.getString(1);
				double curDisc = codeRS.getDouble(2);
				int itemID = codeRS.getInt(3);
				int type = codeRS.getInt(4);
				query = "SELECT `Name` FROM items WHERE Item_ID = '" + itemID + "'";
				Statement curStmt = conn.createStatement();
				ResultSet curRS = curStmt.executeQuery(query);
				curRS.next();
				String itemName = curRS.getString(1);
				Promo curPromo = new Promo(curCode, itemID, itemName, type, curDisc);
				//System.out.println(curPromo.toString());
				promoList.add(curPromo);
			}
			conn.close();
		} catch(ClassNotFoundException e) 
		{
			e.printStackTrace();
			return ERROR;
		}
		catch(SQLException e) 
		{
			e.printStackTrace();
			return ERROR;
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			return ERROR;
		}
		
		return SUCCESS;
	}

	public List<Promo> getPromoList() {
		return promoList;
	}

	public void setPromoList(List<Promo> promoList) {
		this.promoList = promoList;
	}
}
