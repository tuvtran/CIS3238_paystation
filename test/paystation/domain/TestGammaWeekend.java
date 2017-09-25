package paystation.domain;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by tuvttran on 9/25/17.
 */
public class TestGammaWeekend {

    RateStrategy rs;

    @Before
    public void setUp() {
        rs = new AlternatingRateStrategy(
                new LinearRateStrategy(),
                new ProgressiveRateStrategy(),
                new FixedDecisionStrategy(true)
        );
    }

    /**
     * On weekdays, Gamma Pay Stations use linear strategy rate
     */
    @Test
    public void shouldReturn240MinsFor950() {
        assertEquals("Since it's weekday, should return 240 minutes for 950",
                240, rs.calculateTime(950));
    }
}
