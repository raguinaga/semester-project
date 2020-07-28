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
    protected String dateString;
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
                monthName = "January" + defaultDate.getYear();
                break;
            case 2:
                monthName = "February" + defaultDate.getYear();
                break;
            case 3:
                monthName = "March" + defaultDate.getYear();
                break;
            case 4:
                monthName = "April" + defaultDate.getYear();
                break;
            case 5:
                monthName = "May" + defaultDate.getYear();
                break;
            case 6:
                monthName = "June" + defaultDate.getYear();
                break;
            case 7:
                monthName = "July" + defaultDate.getYear();
                break;
            case 8:
                monthName = "August" + defaultDate.getYear();
                break;
            case 9:
                monthName = "September" + defaultDate.getYear();
                break;
            case 10:
                monthName = "October" + defaultDate.getYear();
                break;
            case 11:
                monthName = "November" + defaultDate.getYear();
                break;
            case 12:
                monthName = "December" + defaultDate.getYear();
                break;
        }

        firstDay = calendar.get(Calendar.DAY_OF_WEEK);
        daysInMonth =
                calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        dateString = defaultDate.format(DateTimeFormatter.ISO_LOCAL_DATE);
    }

    public CalendarModel(int year, int month, int day) {
        calendar = new GregorianCalendar(year, month, day);

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
    public int getFirstDay() {
        LocalDate date = LocalDate.now();
        GregorianCalendar gc = new GregorianCalendar(date.getYear(),
                date.getMonthValue(),1);
        gc.set(gc.DAY_OF_WEEK,gc.DATE);
        System.out.println(gc.get(Calendar.DATE));
        System.out.println(gc.DAY_OF_WEEK);
        return gc.get(gc.DAY_OF_WEEK);
    }
}

