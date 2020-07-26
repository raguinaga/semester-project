/*
 * This class is meant to set up the graphical representation of a
 * calendar. It is abstract as it does not do much more than house a
 * few fields and methods necessary to set up the controls that make
 * up the calendar. It is not meant to be instantiated. I do not know
 * if this is best practice.
 */
package Calendar_Roberto_Aguinaga;

import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;


public abstract class CreateCalendarScene {
    //
    private final int WEEKDAYS = 7; // days in a week
    private final int WEEKROWS = 6; // I honestly just based
    // this off windows' calendar in the taskbar.

    /**
     * This method generates a weekday header.
     */
    private HBox createWeekHeader() {
        HBox headerBox = new HBox(); // top level hbox
        headerBox.setPrefSize(600, 30); // Trying out sizes
        String[] days = {"Su", "Mo", "Tu", "We", "Th", "Fr", "Sa"};

        // This sets up the day labels in the
        for (int i = 0; i < days.length; i++) {
            // Create HBoxes to put into header HBox. These HBoxes
            // will contain the labels that have the day names.
            HBox lblBox = new HBox(new Label(days[i]));

            // Make sure that the HBoxes fill out the header HBox.
            HBox.setHgrow(lblBox, Priority.ALWAYS);
            lblBox.setMaxWidth(Double.MAX_VALUE);
            lblBox.setMinWidth(headerBox.getPrefWidth() / 7);

            // Add label HBoxes to header HBox
            headerBox.getChildren().add(lblBox);
        }
        return headerBox;
    }

    public void createCalendar(GridPane calendarGrid) {
        HBox headerBox = createWeekHeader();
        for (int rows = 0; rows < WEEKROWS; rows++) {
            for (int cols = 0; cols < WEEKDAYS; cols++) {
                // Create VBoxes for day cells
                VBox dayCell = new VBox();

                // Make sure sizing for the cells consistent
                dayCell.setMinWidth(headerBox.getPrefWidth() / 7);
                GridPane.setVgrow(dayCell, Priority.ALWAYS);

                // on each cell, add an event handler
                dayCell.addEventHandler(MouseEvent.MOUSE_CLICKED,
                        event -> {
                            noteHandler.addNote(dayCell);
                        });

                // Add VBoxes to the GridPane
                calendarGrid.add(dayCell, cols, rows);
            }
        }
    }

}
