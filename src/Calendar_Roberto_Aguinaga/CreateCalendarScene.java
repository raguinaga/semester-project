package Calendar_Roberto_Aguinaga;

import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

public class CreateCalendarScene {
    private static final int WEEKDAYS = 7; // days in a week
    private static final int WEEKROWS = 6; // I honestly just based
    // this off windows' calendar in the taskbar.

    private static void createWeekHeader() {
        HBox headerBox = new HBox(); // top level hbox
        headerBox.setPrefSize(600, 30); // Trying out sizes
        String[] days = {"Su", "Mo", "Tu", "We", "Th", "Fr", "Sa"};

        // This sets up the day labels in the
        for (int i = 0; i < days.length; i++) {
            // Create HBoxes to put into header HBox. These HBoxes
            // will contain the labels that have the day names.
            HBox lblBox = new HBox(new Label(days[i]));

            // Make sure that the Hboxes fill out the header HBox.
            HBox.setHgrow(lblBox, Priority.ALWAYS);
            lblBox.setMaxWidth(Double.MAX_VALUE);
            lblBox.setMinWidth(headerBox.getPrefWidth()/7);

            // Add label HBoxes to header HBox
            headerBox.getChildren().add(lblBox);
        }
    }

    public static void createCalendar(GridPane gp) {
        
    }

}
