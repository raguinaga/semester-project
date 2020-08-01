/*
 * This class is a logical model of a calendar.
 * Specifically a Gregorian Calendar. In fact I
 * happen to use both the Calendar and GregorianCalendar API among
 * other time APIs to aide in this modeling.
 */
package Calendar_Roberto_Aguinaga;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class CalendarModel {
    // Other fields representing important Date info
    private final int year;
    private final int month;
    private final int daysInMonth;
    // Variable to conveniently store YearMonth's representation of
    // the first day in the month.
    private final int firstDay;
    // GregorianCalendar Object helps to more conveniently set the
    // above fields
    private final GregorianCalendar calendar;
    // String Representation of the month for better convenience over
    // the Calendar Class's Month enums
    private String monthName;

    /**
     * This constructor does four main things: Creates a
     * GregorianCalendar,gives the calendarName field the name of the
     * current month, Gets the current day of the week, and how many
     * days are in this calendar
     */
    public CalendarModel() {
        // Gets the current date from the Host Machine's clock
        LocalDate defaultDate = LocalDate.now();

        // Creates a new GregorianCalendar from the LocalDate object
        // we just got
        calendar = new GregorianCalendar(defaultDate.getYear(),
                defaultDate.getMonthValue(), defaultDate.getDayOfMonth());

        // Set the value of the monthName field
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

        // Get the first day of the month so that the CalendarScene
        // class knows when to start adding number labels/event
        // handlers
        firstDay = firstDate.getDayOfWeek().getValue();

        // Get the total days in the month so the CalendarScene Class
        // knows when to stop adding numbers.
        daysInMonth =
                calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        // Get the year of the calendar
        year = calendar.get(Calendar.YEAR);
        // Get the month of the calendar
        month = calendar.get(Calendar.MONTH);
    }

    /**
     * Constructor which makes a Gregorian Calendar from a LocalDate
     * object passed as a parameter.
     *
     * @param date A LocalDate object.
     */
    public CalendarModel(LocalDate date) {

        // Extract the values from the LocalDate passed in
        int year, month, day;
        year = date.getYear();
        month = date.getMonthValue();
        day = date.getDayOfMonth();
        calendar = new GregorianCalendar(year, month, day);

        // set month name
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

        // Get first day of month
        YearMonth yearMonth = YearMonth.of(date.getYear(),
                date.getMonth());
        LocalDate firstDate = yearMonth.atDay(1);

        firstDay = firstDate.getDayOfWeek().getValue();

        daysInMonth =
                calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        // Get the year of the calendar
        this.year = year;
        // Get the month of the calendar
        this.month = month;
    }

    /**
     * Copy constructor.
     *
     * @param model
     */
    public CalendarModel(CalendarModel model) {
        this.monthName = model.getMonthName();
        this.year = model.getYear();
        this.month = model.getMonth();
        this.firstDay = model.getFirstDay();
        this.daysInMonth = model.getDaysInMonth();
        this.calendar = model.getCalendar();
    }

    // Getters for fields, no setters all fields are final

    /**
     * This method returns the date of the calendar as a string. For
     * use in the NoteHandler class to name files.
     *
     * @param day An int value for the day we clicked on
     * @return A String in the format of YYYY-MM-DD
     */
    public String getDateString(int day) {
        return String.format("%d-%d-%d", year, month, day);
    }

    /**
     * Returns the name of the month
     *
     * @return A string representation of the month of the calendar
     */
    public String getMonthName() {
        return monthName;
    }

    /**
     * Gets the year of the calendar
     *
     * @return An int value for the year of the calendar
     */
    public int getYear() {
        return year;
    }

    /**
     * Returns the month of the calendar
     *
     * @return An int value of the month of the calendar
     */
    public int getMonth() {
        return month;
    }

    /**
     * Returns the first day in the calendar, not always one.
     *
     * @return An int value representing the day of the week the
     * calendar starts on
     */
    public int getFirstDay() {
        return firstDay;
    }

    /**
     * Returns the number of days in the month of the calendar.
     *
     * @return The number of days in the calendar (as an int)
     */
    public int getDaysInMonth() {
        return daysInMonth;
    }

    /**
     * Returns an object of the GregorianCalendar class that has its
     * date set by a LocalDate.
     *
     * @return A GregorianCalendar object.
     */
    public GregorianCalendar getCalendar() {
        return calendar;
    }
}

