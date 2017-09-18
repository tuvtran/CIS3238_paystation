/**
 * Testcases for the Pay Station system.
 *
 * This source code is from the book "Flexible, Reliable Software: Using
 * Patterns and Agile Development" published 2010 by CRC Press. Author: Henrik B
 * Christensen Computer Science Department Aarhus University
 *
 * This source code is provided WITHOUT ANY WARRANTY either expressed or
 * implied. You may study, use, modify, and distribute it for non-commercial
 * purposes. For any commercial use, see http://www.baerbak.com/
 */
package paystation.domain;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

import java.util.HashMap;
import java.util.Map;

public class PayStationImplTest {

    PayStation ps;
    Map<Integer, Integer> testMap;

    @Before
    public void setup() {
        ps = new PayStationImpl();
        testMap = new HashMap<>();
    }

    /**
     * Entering 5 cents should make the display report 2 minutes parking time.
     */
    @Test
    public void shouldDisplay2MinFor5Cents()
            throws IllegalCoinException {
        ps.addPayment(5);
        assertEquals("Should display 2 min for 5 cents",
                2, ps.readDisplay());
    }

    /**
     * Entering 25 cents should make the display report 10 minutes parking time.
     */
    @Test
    public void shouldDisplay10MinFor25Cents() throws IllegalCoinException {
        ps.addPayment(25);
        assertEquals("Should display 10 min for 25 cents",
                10, ps.readDisplay());
    }

    /**
     * Verify that illegal coin values are rejected.
     */
    @Test(expected = IllegalCoinException.class)
    public void shouldRejectIllegalCoin() throws IllegalCoinException {
        ps.addPayment(17);
    }

    /**
     * Entering 10 and 25 cents should be valid and return 14 minutes parking
     */
    @Test
    public void shouldDisplay14MinFor10And25Cents()
            throws IllegalCoinException {
        ps.addPayment(10);
        ps.addPayment(25);
        assertEquals("Should display 14 min for 10+25 cents",
                14, ps.readDisplay());
    }

    /**
     * Buy should return a valid receipt of the proper amount of parking time
     */
    @Test
    public void shouldReturnCorrectReceiptWhenBuy()
            throws IllegalCoinException {
        ps.addPayment(5);
        ps.addPayment(10);
        ps.addPayment(25);
        Receipt receipt;
        receipt = ps.buy();
        assertNotNull("Receipt reference cannot be null",
                receipt);
        assertEquals("Receipt value must be 16 min.",
                16, receipt.value());
    }

    /**
     * Buy for 100 cents and verify the receipt
     */
    @Test
    public void shouldReturnReceiptWhenBuy100c()
            throws IllegalCoinException {
        ps.addPayment(10);
        ps.addPayment(10);
        ps.addPayment(10);
        ps.addPayment(10);
        ps.addPayment(10);
        ps.addPayment(25);
        ps.addPayment(25);

        Receipt receipt;
        receipt = ps.buy();
        assertEquals(40, receipt.value());
    }

    /**
     * Verify that the pay station is cleared after a buy scenario
     */
    @Test
    public void shouldClearAfterBuy()
            throws IllegalCoinException {
        ps.addPayment(25);
        ps.buy(); // I do not care about the result
        // verify that the display reads 0
        assertEquals("Display should have been cleared",
                0, ps.readDisplay());
        // verify that a following buy scenario behaves properly
        ps.addPayment(10);
        ps.addPayment(25);
        assertEquals("Next add payment should display correct time",
                14, ps.readDisplay());
        Receipt r = ps.buy();
        assertEquals("Next buy should return valid receipt",
                14, r.value());
        assertEquals("Again, display should be cleared",
                0, ps.readDisplay());
    }

    /**
     * Verify that cancel clears the pay station
     */
    @Test
    public void shouldClearAfterCancel()
            throws IllegalCoinException {
        ps.addPayment(10);
        ps.cancel();
        assertEquals("Cancel should clear display",
                0, ps.readDisplay());
        ps.addPayment(25);
        assertEquals("Insert after cancel should work",
                10, ps.readDisplay());
    }

    /**
     * Call to empty returns the total amount entered
     */
    @Test
    public void shouldReturnTotalAmountAfterEmpty()
            throws IllegalCoinException {
        ps.addPayment(25);
        assertEquals("Empty should return total amount",
                25, ps.empty());
    }

    /**
     * Call to empty resets the total to zero
     */
    @Test
    public void shouldResetTotalToZeroAfterEmpty()
            throws IllegalCoinException {
        ps.addPayment(5);
        ps.addPayment(10);
        ps.empty();
        assertEquals("Empty should resets the total to zero",
                0, ps.readDisplay());
    }

    /**
     * Canceled entry does not add to the amount returned by empty
     */
    @Test
    public void shouldNotAddToAmountReturnedByEmpty()
            throws IllegalCoinException {
        ps.addPayment(25);
        ps.addPayment(5);
        ps.cancel();
        assertEquals("Canceled entry does not add up to the result",
                0, ps.empty());
        ps.addPayment(10);
        assertEquals("After calling empty, it still works",
                10, ps.empty());
    }

    /**
     * Call to cancel returns a map containing one coin entered
     */
    @Test
    public void shouldContainOneCoinInMap()
            throws IllegalCoinException {
        ps.addPayment(10);
        testMap.put(10, 1);
        assertEquals("Should return a map containing only one coin entered",
                testMap, ps.cancel());
    }

    /**
     * Call to cancel returns a map containing a mixture of coins entered
     */
    @Test
    public void shouldContainMixtureOfCoins()
            throws IllegalCoinException {
        ps.addPayment(10);
        ps.addPayment(5);
        ps.addPayment(5);
        ps.addPayment(25);
        testMap.put(10, 1);
        testMap.put(5, 2);
        testMap.put(25, 1);
        assertEquals("Should return a map containing a mixture of coins entered",
                testMap, ps.cancel());
    }

    /**
     * Call to cancel returns a map that does not contain a key for a coin not entered
     */
    @Test
    public void shouldNotContainCoinNotEntered()
            throws IllegalCoinException {
        ps.addPayment(10);
        assertFalse("Should not contain a key for a coin not entered", ps.cancel().containsKey(25));
    }

    /**
     * Call to cancel clears the map
     */
    @Test
    public void shouldClearTheMapAfterCancel()
            throws IllegalCoinException {
        ps.addPayment(25);
        ps.addPayment(10);
        ps.cancel();
        assertTrue("The map should be empty after cancel() is called", ps.cancel().isEmpty());
    }

    /**
     * Call to buy clears the map
     */
    @Test
    public void shouldClearTheMapAfterBuy()
        throws IllegalCoinException {
        ps.addPayment(25);
        ps.addPayment(5);
        ps.addPayment(10);
        ps.buy();
        assertTrue("The map should be empty after buy() is called", ps.cancel().isEmpty());
    }
}
