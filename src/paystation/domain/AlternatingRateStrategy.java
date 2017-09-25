package paystation.domain;

/**
 * Created by tuvttran on 9/25/17.
 */
public class AlternatingRateStrategy implements RateStrategy {

    private RateStrategy weekdayRS, weekendRS, currentState;
    private WeekendDecisionStrategy decisionStrategy;

    public AlternatingRateStrategy(RateStrategy weekdayRS,
                                   RateStrategy weekendRS,
                                   WeekendDecisionStrategy decisionStrategy) {
        this.weekdayRS = weekdayRS;
        this.weekendRS = weekendRS;
        this.currentState = null;
        this.decisionStrategy = decisionStrategy;
    }

    public int calculateTime(int coinValue) {
        if (decisionStrategy.isWeekend()) {
            currentState = weekendRS;
        } else {
            currentState = weekdayRS;
        }
        return currentState.calculateTime(coinValue);
    }

}
