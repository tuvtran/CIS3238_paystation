package paystation.domain;

/**
 * Created by tuvttran on 9/25/17.
 */
public interface RateStrategy {

    /**
     * Calculate the time based on the number of coins
     */
    int calculateTime(int coinValue);

}
