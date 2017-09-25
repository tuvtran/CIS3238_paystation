package paystation.domain;

/**
 * Created by tuvttran on 9/25/17.
 */
public class LinearRateStrategy implements RateStrategy {

    /**
     * Calculate time based on the number of coins
     * @param coinValue amount inserted
     * @return time bought
     */
    public int calculateTime(int coinValue) {
        return coinValue / 5 * 2;
    }
}
