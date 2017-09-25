package paystation.domain;

/**
 * Created by tuvttran on 9/25/17.
 */
public class ProgressiveRateStrategy implements RateStrategy {

    /**
     * Calculate time based on the progressive strategy
     * which is 5 cents gives 2 minutes for the first hour,
     * 1.5 minutes for the second hour and 1 minute for the
     * third hour
     * @param coinValue amount in coins value
     * @return the time bought
     */
    public int calculateTime(int coinValue) {
        int time = 0;
        // from the third hour
        if (coinValue >= 350) {
            coinValue -= 350;
            time = 120 + coinValue / 5;
        } else if (coinValue >= 150) {
            coinValue -= 150;
            time = 60 + coinValue / 10 * 3;
        } else {
            time = coinValue / 5 * 2;
        }
        return time;
    }
}
