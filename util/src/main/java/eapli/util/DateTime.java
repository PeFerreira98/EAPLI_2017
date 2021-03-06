/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package eapli.util;

import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import eapli.util.io.Console;

/**
 * utility class for Calendar manipulation and several date and time related
 * functions.
 *
 * @author Paulo Gandra Sousa
 */
public final class DateTime {

    private static final int DAYS_TILL_END_OF_WEEK = 6;

    /**
     * checks if the date in both calendar instances are the same. time is not
     * compared!
     *
     * @param a
     * @param b
     * @return
     */
    public static boolean isSameDate(final Calendar a, final Calendar b) {
        return (a.get(Calendar.YEAR) == b.get(Calendar.YEAR)) && (a.
                get(Calendar.MONTH) == b.get(Calendar.MONTH)) && (a.
                get(Calendar.DAY_OF_MONTH) == b.get(Calendar.DAY_OF_MONTH));
    }

    /**
     * Checks if a date is previous to another. Time is not compared!
     *
     * @param a date to be checked
     * @param b date to be checked against
     * @return
     */
    public static boolean isPreviousDate(final Calendar a, final Calendar b) {
        Calendar dateA = new GregorianCalendar(a.get(Calendar.YEAR), a.
                get(Calendar.MONTH), a.
                get(Calendar.DAY_OF_MONTH));
        Calendar dateB = new GregorianCalendar(b.get(Calendar.YEAR), b.
                get(Calendar.MONTH), b.
                get(Calendar.DAY_OF_MONTH));
        return (dateA.before(dateB));
    }

    private DateTime() {
	// to make sure this is an utility class
    }

    /**
     * checks if the two calendar instances represent dates of the same year.
     *
     * @param a
     * @param b
     * @return
     */
    public static boolean isSameYear(final Calendar a, final Calendar b) {
	return a.get(Calendar.YEAR) == b.get(Calendar.YEAR);
    }

    /**
     * checks if the two calendar instances represent dates of the same month
     * (regardless of the year).
     *
     * @param a
     * @param b
     * @return
     */
    public static boolean isSameMonth(final Calendar a, final Calendar b) {
	return a.get(Calendar.MONTH) == b.get(Calendar.MONTH);
    }

    /**
     * returns the current date of the system
     *
     * @return
     */
    public static Calendar now() {
	return new GregorianCalendar();
    }

    /**
     * returns the number of the week in the year given a certain date
     *
     * @param date
     * @return
     */
    public static int weekNumber(final Calendar date) {
	return date.get(Calendar.WEEK_OF_YEAR);
    }

    public static Calendar dateToCalendar(final Date date) {
	final Calendar cal = Calendar.getInstance();
	cal.setTime(date);
	return cal;
    }

    public static int currentWeekNumber() {
	return weekNumber(now());
    }

    /**
     * returns the date of the first day of a week
     *
     * @param year
     * @param week
     * @return
     */
    public static Calendar beginningOfWeek(final int year, final int week) {
	final Calendar date = new GregorianCalendar();
	date.clear();
	date.set(Calendar.YEAR, year);
	date.set(Calendar.WEEK_OF_YEAR, week);
	return date;
    }

    /**
     * returns the date of the last day of a week
     *
     * @param year
     * @param week
     * @return
     */
    public static Calendar endOfWeek(final int year, final int week) {
	final Calendar date = beginningOfWeek(year, week);
	date.add(Calendar.DATE, DAYS_TILL_END_OF_WEEK);
	return date;
    }

    /**
     * returns the date of the last day of the current month
     *
     * @return
     */
    public static Calendar endOfCurrentMonth() {
	return endOfMonth(now());
    }

    /**
     * returns the date of the last day of a certain month
     *
     * @param reference
     *            a date to be used as reference month
     * @return
     */
    public static Calendar endOfMonth(final Calendar reference) {
	final Calendar lastDay = new GregorianCalendar();
	lastDay.setTime(reference.getTime());
	final int last = lastDay.getActualMaximum(Calendar.DAY_OF_MONTH);
	lastDay.set(Calendar.DAY_OF_MONTH, last);
	return lastDay;
    }

    public static Calendar endOfMonth(final int year, final int month) {
	final Calendar date = newCalendar(year, month, year);
	return endOfMonth(date);
    }

    public static int currentYear() {
	return now().get(Calendar.YEAR);
    }

    /**
     * returns the current month of the year
     *
     * @return current month (1 - 12) of the year
     */
    public static int currentMonth() {
	return now().get(Calendar.MONTH) + 1;
    }

    /**
     * Creates a new Calendar object set to a specific date
     *
     * @param year
     *            the year
     * @param month
     *            the month (1 - 12)
     * @param day
     *            the day of the month
     * @return a newly create Calendar object
     */
    public static Calendar newCalendar(final int year, final int month, final int day) {
	return new GregorianCalendar(year, month - 1, day);
    }

    /**
     * parses a string that contains a date in a certain format
     *
     * @param aDateString
     * @param format
     * @return a Date object or null if there was an error parsing the string
     */
    public static Calendar parseDate(final String aDateString, final String format) {
	try {
	    final SimpleDateFormat df = new SimpleDateFormat(format);
	    final Date date = df.parse(aDateString);
	    return dateToCalendar(date);
	} catch (final ParseException ex) {
	    Logger.getLogger(Console.class.getName()).log(Level.SEVERE, null, ex);
	}
	return null;
    }

    public static Calendar parseDate(final String aDateString) {
	return parseDate(aDateString, "dd-MM-yyyy");
    }

    public static String format(final Calendar ocurrs) {
	return format(ocurrs, "YYYY/MM/dd");
    }

    public static String format(final Calendar ocurrs, String dateFormat) {
	final SimpleDateFormat formater = new SimpleDateFormat(dateFormat);
	return formater.format(ocurrs.getTime());
    }

    // TODO create unit tests
    public static String dayNameFromCalendar(Calendar calendar) {
	final String[] dayNames = new DateFormatSymbols().getWeekdays();

	return dayNames[calendar.get(Calendar.DAY_OF_WEEK)];
    }

    public static Calendar yesterday() {
	final Calendar yesterday = now();
	yesterday.add(Calendar.DATE, -1);
	return yesterday;
    }

    public static Calendar tomorrow() {
	final Calendar yesterday = now();
	yesterday.add(Calendar.DATE, 1);
	return yesterday;
    }

    /**
     * Retorna uma lista com os dias da semana que contem o dia indicado
     *
     * @param originalDay dia indicado
     * @return lista com 7 dias
     */
    public static List<Calendar> listDaysOfWeekOfGivenDay(Calendar originalDay) {
        int year = originalDay.get(Calendar.YEAR);
        int calendarWeek = weekNumber(originalDay);
        Calendar sunday = beginningOfWeek(year, calendarWeek);
        List<Calendar> daysInWeek = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            daysInWeek.add((Calendar) sunday.clone());
            sunday.add(Calendar.DAY_OF_MONTH, 1);
        }
        return daysInWeek;
    }
}
