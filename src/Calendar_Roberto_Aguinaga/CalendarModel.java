/*
 * This class is a logical model of a calendar.
 * Specifically a Gregorian Calendar, in fact I
 * happen to use both the Calendar
 * and GregorianCalendar API to aide in this modeling.
 */
package Calendar_Roberto_Aguinaga;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class CalendarModel {
    /*
     * Name of the calendar, plus all the info needed to successfully
     * create a Gregorian calendar. Java's GregorianCalendar API will
     * generate the right starting date for the calendar given the year
     * and month.
     */
    protected String date;
    protected String monthName;
    protected int year;
    protected int month;
    protected int firstDay;
    protected int daysInMonth;
    protected GregorianCalendar calendar;

    /**
     * This constructor does four main things: Creates a GregorianCalendar
     * Gives the calendarName field the name of the current month,
     * Gets the current day of the week, and how many days are in this calendar
     */
    public CalendarModel() {

        LocalDate defaultDate = LocalDate.now();
        calendar = new GregorianCalendar(defaultDate.getYear(),
                defaultDate.getMonthValue(), defaultDate.getDayOfMonth());

        switch (defaultDate.getMonthValue()) {
            case 1:
                monthName = "January";
            case 2:
                monthName = "February";
            case 3:
                monthName = "March";
            case 4:
                monthName = "April";
            case 5:
                monthName = "May";
            case 6:
                monthName = "June";
            case 7:
                monthName = "July";
            case 8:
                monthName = "August";
            case 9:
                monthName = "September";
            case 10:
                monthName = "October";
            case 11:
                monthName = "November";
            case 12:
                monthName = "December";
        }

        firstDay = calendar.get(Calendar.DAY_OF_WEEK);
        daysInMonth =
                calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        date = defaultDate.format(DateTimeFormatter.ISO_LOCAL_DATE);
    }

    public CalendarModel(int year, int month) {
        calendar = new GregorianCalendar(year, month, 1);

        switch (month) {
            case 1:
                monthName = "January";
            case 2:
                monthName = "February";
            case 3:
                monthName = "March";
            case 4:
                monthName = "April";
            case 5:
                monthName = "May";
            case 6:
                monthName = "June";
            case 7:
                monthName = "July";
            case 8:
                monthName = "August";
            case 9:
                monthName = "September";
            case 10:
                monthName = "October";
            case 11:
                monthName = "November";
            case 12:
                monthName = "December";
        }
        firstDay = calendar.get(Calendar.DAY_OF_WEEK);
        daysInMonth =
                calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);

    }

   /* public static CalendarModel getCurrentCalendar() {
        return new CalendarModel(year, month);
    }

    public static CalendarModel getDefaultInstance() {
        return new CalendarModel();
    }*/
}

