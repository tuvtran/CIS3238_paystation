package paystation.domain;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by tuvttran on 9/25/17.
 */
public class TestGammaWeekday {

    RateStrategy rs;

    @Before
    public void setUp() {
        rs = new AlternatingRateStrategy(
                new LinearRateStrategy(),
                new ProgressiveRateStrategy(),
                new FixedDecisionStrategy(false)
        );
    }

    /**
     * On weekdays, Gamma Pay Stations use linear strategy rate
     */
    @Test
    public void shouldReturn120MinsFor300() {
        assertEquals("Since it's weekday, should return 120 mins for 300",
                120, rs.calculateTime(300));
    }
}
