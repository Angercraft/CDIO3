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
		assertTrue("The required amount isn't equal to the intial amount", 100==testMoney.getAmount());
	}
	
	
}
