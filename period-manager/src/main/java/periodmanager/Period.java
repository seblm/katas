package periodmanager;

import java.util.Date;

public class Period {

    private Date beginDate;
    
    private Date endDate;
    
    private final int id;

    Period(int id, final Date beginDate, final Date endDate) {
        this(id, beginDate);
        if (beginDate.after(endDate)) {
            throw new IllegalArgumentException();
        }
        this.endDate = endDate;
    }

    Period(int id, Date beginDate) {
        this.id = id;
        if (beginDate == null) {
            throw new IllegalArgumentException();
        }
        this.beginDate = beginDate;
    }
    
    public Date getBeginDate() {
        return beginDate;
    }
    
    public void setBeginDate(final Date newBeginDate) {
        if (newBeginDate == null) {
            throw new IllegalArgumentException();
        }
        beginDate = newBeginDate;
    }
    
    public Date getEndDate() {
        return endDate;
    }
    
    public void setEndDate(final Date newEndDate) {
        endDate = newEndDate;
    }
    
    public int getId() {
        return id;
    }
    
    @Override
    public String toString() {
        final StringBuilder out = new StringBuilder();
        out.append(id).append(". ");
        out.append(PeriodManager.DATE_FORMAT.format(beginDate));
        if (endDate != null) {
            out.append(" - ");
            out.append(PeriodManager.DATE_FORMAT.format(endDate));
        }
        return out.toString();
    }
}
