/*
 * This class is a logical model of a calendar.
 * Specifically a Gregorian Calendar, in fact I 
 * happen to use both the Calendar
 * and GregorianCalendar API in this class
 */
package Calendar_Roberto_Aguinaga;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class CalendarModel {
    /*
     * Name of the calendar, plus all the info needed to successfully
     * create a Gregorian calendar. Java's GregorianCalendar API will
     * generate the right starting date for the calendar given the year
     * and month.
     */
    protected String calendarName;
    protected int year;
    protected int month;
    protected int offset;
    protected int daysInMonth;
    protected GregorianCalendar instanceCalendar;

    /**
     * This constructor does four main things: Creates a GregorianCalendar
     * Gives the calendarName field the name of the current month, 
     * Gets the current day of the week, and how many days are in this calendar
     * @param month An enum, or int value from 1-12 representing the months
     * of the year
     * @param year An int value representing the year.
     */
    public CalendarModel(int month, int year) {
        instanceCalendar = new GregorianCalendar(year, month, 1);
        switch (month) {
            case 1:
                calendarName = "January";
            case 2:
                calendarName = "February";
            case 3:
                calendarName = "March";
            case 4:
                calendarName = "April";
            case 5:
                calendarName = "May";
            case 6:
                calendarName = "June";
            case 7:
                calendarName = "July";
            case 8:
                calendarName = "August";
            case 9:
                calendarName = "September";
            case 10:
                calendarName = "October";
            case 11:
                calendarName = "November";
            case 12:
                calendarName = "December";
        }

        offset = instanceCalendar.get(Calendar.DAY_OF_WEEK);
        daysInMonth =
                instanceCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }
}

