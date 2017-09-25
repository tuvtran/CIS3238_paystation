package paystation.domain;

/**
 * Created by tuvttran on 9/25/17.
 */
public class FixedDecisionStrategy
        implements WeekendDecisionStrategy {
    private boolean isWeekend;

    /**
     * Construct a test stub weekend decision strategy
     * @param isWeekend is the boolean value to return
     *                  in calls to isWeekend()
     */
    public FixedDecisionStrategy(boolean isWeekend) {
        this.isWeekend = isWeekend;
    }

    public boolean isWeekend() {
        return this.isWeekend;
    }
}
