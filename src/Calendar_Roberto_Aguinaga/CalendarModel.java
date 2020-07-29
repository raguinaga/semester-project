/*
 * This class is a logical model of a calendar.
 * Specifically a Gregorian Calendar, in fact I
 * happen to use both the Calendar
 * and GregorianCalendar API among other time APIs to aide in this
 * modeling.
 */
package Calendar_Roberto_Aguinaga;

import java.time.LocalDate;
import java.time.YearMonth;
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
                monthName = "January " + defaultDate.getYear();
                break;
            case 2:
                monthName = "February " + defaultDate.getYear();
                break;
            case 3:
                monthName = "March " + defaultDate.getYear();
                break;
            case 4:
                monthName = "April " + defaultDate.getYear();
                break;
            case 5:
                monthName = "May " + defaultDate.getYear();
                break;
            case 6:
                monthName = "June " + defaultDate.getYear();
                break;
            case 7:
                monthName = "July " + defaultDate.getYear();
                break;
            case 8:
                monthName = "August " + defaultDate.getYear();
                break;
            case 9:
                monthName = "September " + defaultDate.getYear();
                break;
            case 10:
                monthName = "October " + defaultDate.getYear();
                break;
            case 11:
                monthName = "November " + defaultDate.getYear();
                break;
            case 12:
                monthName = "December " + defaultDate.getYear();
                break;
        }

        // I have to create YearMonth and LocalDate objects because
        // Calendar's DAY_OF_WEEK apparently does not get changed by
        // the Calendar.set method, despite what the docs say. Also the
        // getTime method works but returns an object of the Date Class
        // which has had virtually all methods deprecated in favor of
        // ...wait for it... Calendar's non-working methods.
        // (╯°□°）╯︵ ┻━┻
        YearMonth yearMonth = YearMonth.of(defaultDate.getYear(),
                defaultDate.getMonth());
        LocalDate firstDate = yearMonth.atDay(1);
        // Get the first day of the month so that the calendar
        // gripane knows when to start adding number labels
        firstDay = firstDate.getDayOfWeek().getValue();

        // Get the total days in the month so te Gridpane methods
        // know when to stop adding numbers.
        daysInMonth =
                calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        // Get the year of the calendar
        year = calendar.get(Calendar.YEAR);
        // Get the month of the calendar
        month = calendar.get(Calendar.MONTH);
        // Make a nice string representation of the calendar date.
        dateString = defaultDate.format(DateTimeFormatter.ISO_LOCAL_DATE);
    }

    /**
     * Constructor which makes a Gregorian Calendar from a LocalDate
     * object passed as a parameter.
     *
     * @param date A LocalDate object.
     */
    public CalendarModel(LocalDate date) {
        int year, month, day;
        year = date.getYear();
        month = date.getMonthValue();
        day = date.getDayOfMonth();
        calendar = new GregorianCalendar(year, month, day);

        switch (month) {
            case 1:
                monthName = "January " + year;
                break;
            case 2:
                monthName = "February " + year;
                break;
            case 3:
                monthName = "March " + year;
                break;
            case 4:
                monthName = "April " + year;
                break;
            case 5:
                monthName = "May " + year;
                break;
            case 6:
                monthName = "June " + year;
                break;
            case 7:
                monthName = "July " + year;
                break;
            case 8:
                monthName = "August " + year;
                break;
            case 9:
                monthName = "September " + year;
                break;
            case 10:
                monthName = "October " + year;
                break;
            case 11:
                monthName = "November " + year;
                break;
            case 12:
                monthName = "December " + year;
                break;
        }

        YearMonth yearMonth = YearMonth.of(date.getYear(),
                date.getMonth());
        LocalDate firstDate = yearMonth.atDay(1);

        // Get the first day of the month so that the calendar
        // gripane knows when to start adding number labels
        firstDay = firstDate.getDayOfWeek().getValue();

        // Get the total days in the month so te Gridpane methods
        // know when to stop adding numbers.
        daysInMonth =
                calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        // Get the year of the calendar
        year = calendar.get(Calendar.YEAR);
        // Get the month of the calendar
        month = calendar.get(Calendar.MONTH);
        // Make a nice string representation of the calendar date.
        dateString = date.format(DateTimeFormatter.ISO_LOCAL_DATE);
    }


}

