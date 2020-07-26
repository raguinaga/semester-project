package Calendar_Roberto_Aguinaga;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class CalendarModel {
    /*
     * Name of the calendar, plus all the info needed to successfully
     * create a Gregorian calendar. Java's GregorianCalendar API will
     * generate the right starting date for the calendar.
     */
    protected String calendarName;
    protected int month;
    protected int year;
    protected int firstDay;
    protected int lastDay;
    protected GregorianCalendar instanceCalendar;

    public CalendarModel() {
        instanceCalendar = new GregorianCalendar(year,month,1);
    }


}
