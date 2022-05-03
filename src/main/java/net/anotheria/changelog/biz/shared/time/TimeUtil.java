package net.anotheria.changelog.biz.shared.time;

import java.util.Calendar;

/**
 *
 */
public class TimeUtil {

    public static TimeRange getMonthTimeRange(int year, int month) {
        if (year < 0) {
            throw new IllegalArgumentException("invalid year value: " + year);
        }

        if (month < 0 || month > 11) {
            throw new IllegalArgumentException("invalid month value: " + month);
        }

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        long startTime = calendar.getTimeInMillis();

        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);

        long endTime = calendar.getTimeInMillis();
        return new TimeRange(startTime, endTime);
    }

    public static TimeRange getYearTimeRange(int year) {
        if (year < 0) {
            throw new IllegalArgumentException("invalid year value: " + year);
        }

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        long startTime = calendar.getTimeInMillis();

        calendar.set(Calendar.MONTH, 11);
        calendar.set(Calendar.DAY_OF_MONTH, 31);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);

        long endTime = calendar.getTimeInMillis();

        return new TimeRange(startTime, endTime);
    }
}
