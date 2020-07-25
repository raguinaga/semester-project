package Calendar_Roberto_Aguinaga;

public class CalendarModel {
    private String name;
    private int startYear;
    private int endYear;

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
