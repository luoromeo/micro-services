/*
 * Copyright (c) 2017-2020 Feibo Group Group Holding Limited. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package com.luoromeo.tools;

import com.luoromeo.tools.commons.FormatConstants;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * The Class DateUtils.
 *
 * @author feibo
 */
public abstract class DateUtils extends org.apache.commons.lang3.time.DateUtils {

    public final static String YEAR = "year";

    public final static String QUARTER = "quarter";

    public final static String MONTH = "month";

    public final static String WEEK = "week";

    public final static String DAY = "day";

    public final static String HOUR = "hour";

    public final static String MINUTE = "minute";

    public final static String SECOND = "second";


    /**
     * Gets the current date time.
     *
     * @return the current date time
     */
    public static Date getCurrentDateTime() {
        Calendar calNow = Calendar.getInstance();
        Date dtNow = calNow.getTime();
        return dtNow;
    }

    /**
     * Gets the today.
     *
     * @return the today
     */
    public static Date getToday() {
        return truncate(new Date(), Calendar.DATE);
    }

    /**
     * Gets the today end.
     *
     * @return the today end
     */
    public static Date getTodayEnd() {
        return getDayEnd(new Date());
    }

    /**
     * Convert to date.
     *
     * @param dateString the date string
     * @return the date
     */
    public static Date convertToDate(String dateString) {
        try {
            return FormatConstants.DATE_FORMAT.parse(dateString);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * Check date string.
     *
     * @param dateString the date string
     * @return true, if successful
     */
    public static boolean checkDateString(String dateString) {
        return (convertToDate(dateString) != null);
    }

    /**
     * Convert to date time.
     *
     * @param dateTimeString the date time string
     * @return the date
     */
    public static Date convertToDateTime(String dateTimeString) {
        try {
            return FormatConstants.DATE_TIME_FORMAT.parse(dateTimeString);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * Check date time string.
     *
     * @param dateTimeString the date time string
     * @return true, if successful
     */
    public static boolean checkDateTimeString(String dateTimeString) {
        return (convertToDateTime(dateTimeString) != null);
    }

    /**
     * Gets the month end.
     *
     * @param year the year
     * @param month the month
     * @return the month end
     */
    public static Date getMonthEnd(int year, int month) {
        StringBuffer sb = new StringBuffer(10);
        Date date;
        if (month < 12) {
            sb.append(Integer.toString(year));
            sb.append("-");
            sb.append(month + 1);
            sb.append("-1");
            date = convertToDate(sb.toString());
        } else {
            sb.append(Integer.toString(year + 1));
            sb.append("-1-1");
            date = convertToDate(sb.toString());
        }
        date.setTime(date.getTime() - 1);
        return date;
    }

    /**
     * Gets the month end.
     *
     * @param when the when
     * @return the month end
     */
    public static Date getMonthEnd(Date when) {
        Validate.notNull(when, "date must not be null !");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(when);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        return getMonthEnd(year, month);
    }

    /**
     * Gets the day end.
     *
     * @param when the when
     * @return the day end
     */
    public static Date getDayEnd(Date when) {
        Date date = truncate(when, Calendar.DATE);
        date = addDays(date, 1);
        date.setTime(date.getTime() - 1);
        return date;
    }

    /**
     * Gets the day.
     *
     * @param when the when
     * @return the day
     */
    public static Date getDay(Date when) {
        Date date = truncate(when, Calendar.DATE);
        date = addDays(date, -1);
        date.setTime(date.getTime() + 1);
        return date;
    }

    /**
     * Adds the.
     *
     * @param when the when
     * @param field the field
     * @param amount the amount
     * @return the date
     */
    public static Date add(Date when, int field, int amount) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(when);
        calendar.add(field, amount);
        return calendar.getTime();
    }

    /**
     * Adds the days.
     *
     * @param when the when
     * @param amount the amount
     * @return the date
     */
    public static Date addDays(Date when, int amount) {
        return add(when, Calendar.DAY_OF_YEAR, amount);
    }

    /**
     * Adds the months.
     *
     * @param when the when
     * @param amount the amount
     * @return the date
     */
    public static Date addMonths(Date when, int amount) {
        return add(when, Calendar.MONTH, amount);
    }

    /**
     * Format date.
     *
     * @param date the date
     * @param format the format
     * @return the string
     */
    public static String formatDate(Date date, String format) {
        if (StringUtils.isNotBlank(format)) {
            DateFormat DATE_TIME_FORMAT = new SimpleDateFormat(format, java.util.Locale.CHINA);
            return DATE_TIME_FORMAT.format(date);
        }
        return FormatConstants.DATE_TIME_FORMAT.format(date);
    }

    /**
     * <pre>
     * 	calc date-{The time difference between two dates}
     * <pre>
     *
     * @param timeInterval
     * 	value(year、quarter、month、week、day、hour、minute、second)
     * @param date1
     * 	fist date
     * @param date2
     * 	second date
     * @return
     * 	date diff
     */
    public static long dateDiff(String timeInterval, Date date1, Date date2) {
        if (DateUtils.YEAR.equals(timeInterval)) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date1);
            int time = calendar.get(Calendar.YEAR);
            calendar.setTime(date2);
            return time - calendar.get(Calendar.YEAR);
        }

        if (DateUtils.QUARTER.equals(timeInterval)) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date1);
            int time = calendar.get(Calendar.YEAR) * 4;
            calendar.setTime(date2);
            time -= calendar.get(Calendar.YEAR) * 4;
            calendar.setTime(date1);
            time += calendar.get(Calendar.MONTH) / 4;
            calendar.setTime(date2);
            return time - calendar.get(Calendar.MONTH) / 4;
        }

        if (DateUtils.MONTH.equals(timeInterval)) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date1);
            int time = calendar.get(Calendar.YEAR) * 12;
            calendar.setTime(date2);
            time -= calendar.get(Calendar.YEAR) * 12;
            calendar.setTime(date1);
            time += calendar.get(Calendar.MONTH);
            calendar.setTime(date2);
            return time - calendar.get(Calendar.MONTH);
        }

        if (DateUtils.WEEK.equals(timeInterval)) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date1);
            int time = calendar.get(Calendar.YEAR) * 52;
            calendar.setTime(date2);
            time -= calendar.get(Calendar.YEAR) * 52;
            calendar.setTime(date1);
            time += calendar.get(Calendar.WEEK_OF_YEAR);
            calendar.setTime(date2);
            return time - calendar.get(Calendar.WEEK_OF_YEAR);
        }

        if (DateUtils.DAY.equals(timeInterval)) {
            long time = date1.getTime() / 1000 / 60 / 60 / 24;
            return time - date2.getTime() / 1000 / 60 / 60 / 24;
        }

        if (DateUtils.HOUR.equals(timeInterval)) {
            long time = date1.getTime() / 1000 / 60 / 60;
            return time - date2.getTime() / 1000 / 60 / 60;
        }

        if (DateUtils.MINUTE.equals(timeInterval)) {
            long time = date1.getTime() / 1000 / 60;
            return time - date2.getTime() / 1000 / 60;
        }

        if (DateUtils.SECOND.equals(timeInterval)) {
            long time = date1.getTime() / 1000;
            return time - date2.getTime() / 1000;
        }

        return date1.getTime() - date2.getTime();
    }

    /**
     * <pre>
     * 	timestamp transfer to date string
     * <pre>
     *
     * @param seconds
     * @param format
     * @return
     */
    public static String timeStamp2Date(String seconds, String format) {
        if (seconds == null || seconds.isEmpty() || seconds.equals("null")) {
            return "";
        }
        if (format == null || format.isEmpty()) {
            format = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(Long.valueOf(seconds + "000")));
    }
}
