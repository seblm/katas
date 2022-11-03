package periodmanager;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.SortedSet;
import java.util.TreeSet;

public class PeriodManager {

    static final DateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
    
    private SortedSet<Period> periods;

    PeriodManager(Date... beginDates) {
        this.periods = new TreeSet<Period>(new Comparator<Period>() {
            @Override
            public int compare(Period firstPeriod, Period secondPeriod) {
                return firstPeriod.getBeginDate().compareTo(secondPeriod.getBeginDate());
            }
        });
        for (int i = 0; i < beginDates.length - 1; i++) {
            periods.add(new Period(i, beginDates[i], beginDates[i + 1]));
        }
        periods.add(new Period(beginDates.length - 1, beginDates[beginDates.length - 1]));
    }

    void move(final int periodId, final Date newBeginDate) {
        final Period periodToMove = removePeriod(periodId);
        if (periodToMove == null) {
            return;
        }
        periodToMove.setBeginDate(newBeginDate);
        periodToMove.setEndDate(null);
        for (final Period period : periods) {
            if (period.getBeginDate().after(newBeginDate)) {
                periodToMove.setEndDate(period.getBeginDate());
                break;
            }
        }
        periods.add(periodToMove);
        Period previousPeriod = null;
        boolean first = true;
        for (final Period period : periods) {
            if (first) {
                first = false;
            } else if (period.getBeginDate() != previousPeriod.getEndDate()) {
                previousPeriod.setEndDate(period.getBeginDate());
            }
            previousPeriod = period;
        }
    }

    private Period removePeriod(final int periodId) {
        for (final Period period : periods) {
            if (period.getId() == periodId) {
                periods.remove(period);
                return period;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        final StringBuilder out = new StringBuilder();
        for (final Period period : periods) {
            out.append(period).append('\n');
        }
        return out.toString();
    }

}
