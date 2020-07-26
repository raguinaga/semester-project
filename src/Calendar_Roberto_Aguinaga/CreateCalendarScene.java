package Calendar_Roberto_Aguinaga;

import javafx.scene.control.Label;
import javafx.scene.layout.*;


public class CreateCalendarScene {
    private static final int WEEKDAYS = 7; // days in a week
    private static final int WEEKROWS = 6; // I honestly just based
    // this off the calendar that Windows has in its taskbar

    private static void GenerateWeekdayHeader() {
        String[] days = {"Su", "Mo", "Tu", "We", "Th", "Fr", "Sa"};
        HBox header = new HBox();

        for (int i = 0; i < WEEKDAYS; i++) {
            Pane pane = new Pane();
            // So that all panes are equally-sized
            HBox.setHgrow(pane, Priority.ALWAYS);
            pane.setMaxWidth(Double.MAX_VALUE);

            pane.getChildren().add(new Label(days[i]));
        }
    }

    /**
     * This method does all the actual work of setting up the
     * body of the calendar graphically, setting up the GridPane.
     *
     * @param calendarGrid
     */
    public static void InitializeGridPane(GridPane calendarGrid) {
        for (int i = 0; i < WEEKROWS; i++) {
            for (int j = 0; j < WEEKDAYS; j++) {
                //   Add a VBox and style it
                VBox vbox = new VBox();
                vbox.setMinWidth();
            }
        }
    }

}
