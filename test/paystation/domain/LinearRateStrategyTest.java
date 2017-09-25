package paystation.domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by tuvttran on 9/25/17.
 */
public class LinearRateStrategyTest {

    private RateStrategy rs;

    @Before
    public void setUp() {
        rs = new LinearRateStrategy();
    }

    /**
     * Test should 80 mins
     * for 200 in coin values
     */
    @Test
    public void shouldReturnCorrectTimeFor200Coins() {
        assertEquals("200 in coin values is 80", 80,
                rs.calculateTime(200));
    }

}
