package net.anotheria.changelog.biz.shared.time;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.Date;

/**
 *
 */
public class TimeRange implements Serializable {

    private static final long serialVersionUID = -7703395249066033433L;

    private Long start;
    private Long end;

    public TimeRange() {
    }

    public TimeRange(Long start, Long end) {
        this.start = start;
        this.end = end;
    }

    @JsonIgnore
    public Date getStartDate() {
        return start == null ? null : new Date(start);
    }

    @JsonIgnore
    public Date getEndDate() {
        return end == null ? null : new Date(end);
    }

    public Long getStart() {
        return start;
    }

    public void setStart(Long start) {
        this.start = start;
    }

    public Long getEnd() {
        return end;
    }

    public void setEnd(Long end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return "TimeRange{" +
                "start=" + start +
                ", end=" + end +
                '}';
    }
}
