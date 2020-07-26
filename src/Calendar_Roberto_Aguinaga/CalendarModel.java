package Calendar_Roberto_Aguinaga;

import java.util.GregorianCalendar;

public class CalendarModel {
    /*
     * Name of the calendar, plus all the info needed to successfully
     * create a Gregorian calendar. Java's GregorianCalendar API will
     * generate the right starting date for the calendar.
     */
    protected String name;
    protected int year;
    protected int firstDay;
    protected int lastDay;
    protected GregorianCalendar instanceCalendar;

    public CalendarModel(String name, int startYear, int endYear) {
        this.name = name;
        this.startYear = startYear;
        this.endYear = endYear;
    }

    public String getName() {
        return name;
    }

    public int getStartYear() {
        return startYear;
    }

    public int getEndYear() {
        return endYear;
    }
}
