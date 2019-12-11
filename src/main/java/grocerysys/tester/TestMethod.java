package grocerysys.tester;

import org.junit.Test;

import grocerysys.exampleCode.CodeToTest;



class TestMethod {

	CodeToTest example = new CodeToTest();
	
	@Test
	void testLog() 
	{
		//test good input
		boolean didTheyLogIn = example.checkLogin("lolol", "lolol");
		assert(didTheyLogIn == true):"Should be true";
		
		//test no input
		didTheyLogIn = example.checkLogin("","");
		assert(didTheyLogIn == false):"Should be false";
		
		//Test a correct username but wrong password
		didTheyLogIn = example.checkLogin("lolol","nop");
		assert(didTheyLogIn == false):"Should be false";
	}
	
	@Test
	void testAddingToCart()
	{
		//user id in the system can't be wrong because we always have a true ID
		//item Id isn't wrong either due to the system always having true id's due to our list
		
		//Good input
		String  didTheyAddtoCart = example.testAddToCart("0", "1112", "3.0");
		assert(didTheyAddtoCart == "Added to cart"):"Should have been able to add to cart";
		
		//incorrect price entered
		didTheyAddtoCart = example.testAddToCart("0", "1112", "nooooo");
		assert(didTheyAddtoCart == "Did not add"):"Should not have been able to add to cart";
		
		//null values
		//this should not allow things to happen
		didTheyAddtoCart = example.testAddToCart(null, null, null);
		assert(didTheyAddtoCart == "Did not add"):"Should not have been able to add to cart";
	}
	
	@Test
	void testCreatingAccount()
	{
		//CreateAccount takes in string values then creates the account
		//CreateAccount only takes in Strings so you can't pass in something not a String or null
		
		//proper input
		String accountMade = example.testCreateAccount("Jackson", "Mickeal", "1029 HeeHee", "120394", "123", "Jessie", "Jackson");
		assert(accountMade == "Created"):"Should have said \"Created\"";
		
		//values null
		accountMade = example.testCreateAccount(null, null, null, null, null, null, null);
		assert(accountMade == "Did not add"):"Should have said \"Did not add\"";
		
	}
	
	@Test
	void testOurCheckOut()
	{
		//checkout always has the proper userId, it cannot be an error in the system
		
		//good values
		String checker = example.testCheckOut(0, 0, "0");
		assert(checker == "cartted"):"should be cartted";
		
		//different times
		checker = example.testCheckOut(1, 2, "0");
		assert(checker == "cartted"):"should be cartted";
		
		
		//different id
		checker = example.testCheckOut(0, 0, "785963260");
		assert(checker == "cartted"):"should be cartted";
		
		
		//time and option out of bounds
		checker = example.testCheckOut(30, 20, "0");
		assert(checker == "not carted"):"should be not carted";
	}
	
	
}
