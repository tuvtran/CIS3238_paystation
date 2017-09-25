package paystation.domain;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by tuvttran on 9/25/17.
 */
public class ProgressiveRateStrategyTest {

    private RateStrategy rs;

    @Before
    public void setUp() {
        rs = new ProgressiveRateStrategy();
    }

    /**
     * Testing for the first hour
     */
    @Test
    public void shouldReturn56MinsFor140() {
        assertEquals("140 should yields 56 minutes", 56,
                rs.calculateTime(140));
    }

    /**
     * Testing for the second hour
     */
    @Test
    public void shouldReturn75minutesFor200() {
        assertEquals("200 should yields 75 minutes", 75,
                rs.calculateTime(200));
    }

    /**
     * Testing for three hours
     * */
    @Test
    public void shouldReturn180MinFor650cent() {
        assertEquals("650 yields 3 hours",180,
                rs.calculateTime(650));
    }
}
