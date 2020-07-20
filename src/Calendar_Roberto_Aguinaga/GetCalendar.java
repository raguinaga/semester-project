package Calendar_Roberto_Aguinaga;

import java.util.Calendar;
import java.util.Date;

public class GetCalendar {

    protected Calendar instanceCalendar;

    public GetCalendar() {
        instanceCalendar = Calendar.getInstance();
    }

    public String getMonth() {
        String month = "";
        switch (instanceCalendar.get(Calendar.MONTH)) {
            case 1:
                month = "January";
            case 2:
                month = "February";
            case 3:
                month = "March";
            case 4:
                month = "April";
            case 5:
                month = "May";
            case 6:
                month = "June";
            case 7:
                month = "July";
            case 8:
                month = "August";
            case 9:
                month = "September";
            case 10:
                month = "October";
            case 11:
                month = "November";
            case 12:
                month = "December";
        }
        return month;
    }
}
