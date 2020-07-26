package Calendar_Roberto_Aguinaga;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

public class CreateCalendarScene {
    private static final int WEEKDAYS = 7; // days in a week
    private static final int WEEKROWS = 6; // I honestly just based based
    // this off windows' calendar in the taskbar.

    private static void createWeekHeader() {
      HBox headerBox = new HBox();
      headerBox.setPrefSize(600, 30); // Trying out sizes
      String[] days = {"Su", "Mo", "Tu", "We", "Th", "Fr", "Sa"};

      for (int i = 0; i < days.length; i++) {
        HBox lblBox = new HBox(new Label(days[i]));
        HBox.setHgrow(Priority.ALWAYS);
      }
    }
  
}
