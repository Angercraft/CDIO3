package test;

import static org.junit.Assert.*;

import org.junit.Test;

import juniorMatador.Die;
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
		assertTrue("The set amount does not equal expectations", testMoney.getAmount()==200);
	}
}
