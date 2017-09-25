package paystation.domain;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by tuvttran on 9/25/17.
 */
public class ClockBasedDecisionStrategy implements WeekendDecisionStrategy {
    public boolean isWeekend() {
        Date d = new Date();
        Calendar c = new GregorianCalendar();
        c.setTime(d);
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        return dayOfWeek == Calendar.SATURDAY ||
                dayOfWeek == Calendar.SUNDAY;
    }
}
