package test;

import static org.junit.Assert.*;

import org.junit.Test;
import juniorMatador.Money;

public class MoneyTest {
	
	Money testMoney;
	
    @org.junit.Before
    public void setUp() throws Exception {
    	testMoney = new Money(100);
    }
    
	@Test
	public void testGet() {
		assertTrue("The aquired amount isn't equal to the intial amount", 100==testMoney.getAmount());
	}
	
	@Test
	public void testSet() {
		testMoney.setAmount(200);
		assertTrue("The set amount does not meet expectations", testMoney.getAmount()==200);
	}
	
	//Test if addAmount() correctly adds the desired amount to the account if the total balance is above 0.
	@Test
	public void testAdd() {
		testMoney.addAmount(100);
		assertTrue("The total balance of money does not meet expectations", testMoney.getAmount()==200);
	}
	
	//Test if addAmount() sets the balance to 0 if the total balance is <0.
	@Test
	public void testNegAdd() {
		testMoney.addAmount(-200);
		assertTrue("The total balance is below 0", testMoney.getAmount()==0);
	}
	
	//Test if resetMoney() sets the balance to the initial starting balance
	@Test
	public void testReset() {
		testMoney.setAmount(324);
		testMoney.resetMoney();
		assertTrue("The balance wasn't reset", testMoney.getAmount()==100);
	}
}
