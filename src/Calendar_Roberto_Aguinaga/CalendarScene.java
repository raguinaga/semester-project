/*
 * This class is meant to set up the graphical representation of a
 * calendar. It is abstract as it does not do much more than house a
 * few fields and methods necessary to set up the controls that make
 * up the calendar. It is not meant to be instantiated. I do not know
 * if this is best practice.
 */
package Calendar_Roberto_Aguinaga;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;


public class CalendarScene {
    protected Scene calendarScene; // the main scene with a calendar.
    protected VBox root = new VBox();
    protected AnchorPane anchorPane = new AnchorPane();
    private DatePicker picker = new DatePicker(); // So users can
    // move to other dates
    private HBox pickerBox = new HBox(picker);
    protected HBox gridBox;
    protected GridPane calendarGrid = new GridPane();// Container
    // for calendar.



    private final int WEEKDAYS = 7; // days in a week
    private final int WEEKROWS = 7; // I honestly just based
    // this off windows' calendar in the taskbar. It has one extra
    // row for the days

    /**
     * This method generates the weekday header.
     */
    private void createWeekHeader() {
        String[] days = {"Su", "Mo", "Tu", "We", "Th", "Fr", "Sa"};

        // This sets up the day labels in the
        for (int i = 0; i < days.length; i++) {
            // Create HBoxes to put into header HBox. These HBoxes
            // will contain the labels that have the day names.
            Label lbl = new Label(days[i]);
            HBox lblBox = new HBox(lbl);

            // Make sure that the HBoxes fill out the header HBox.
            HBox.setHgrow(lblBox, Priority.ALWAYS);
            lblBox.setMaxWidth(Double.MAX_VALUE);

            // Add style class to Hboxes and labels
            lbl.getStyleClass().add("day-label");
            lblBox.getStyleClass().add("header-row");

            // Add label HBoxes to header HBox
            calendarGrid.addRow(0,lblBox);
        }
    }

    private void createCalendar() {
        // Have to start at 1 for the rows loop because of the header
        // row.
        for (int rows = 1; rows < WEEKROWS; rows++) {
            for (int cols = 0; cols < WEEKDAYS; cols++) {
                // Create VBoxes for day cells
                VBox dayCell = new VBox(new Button("Hi"));
                dayCell.getStyleClass().add("day-cell");

                // Make sure sizing for the cells consistent
                //dayCell.setMinWidth(headerBox.getPrefWidth() / 7);
                GridPane.setVgrow(dayCell, Priority.ALWAYS);

                // on each cell, add an event handler
                /*dayCell.addEventHandler(MouseEvent.MOUSE_CLICKED,
                        event -> {
                            noteHandler.addNote(dayCell);
                        });*/

                // Add VBoxes to the GridPane
                calendarGrid.add(dayCell, cols, rows);
            }
        }

        // Have to also add row and column constraints
        for (int i = 0; i < WEEKROWS; i++) {
            RowConstraints rc = new RowConstraints(90);
            calendarGrid.getRowConstraints().add(rc);
            ColumnConstraints cc=
                    new ColumnConstraints(120);
            calendarGrid.getColumnConstraints().add(cc);
        }
    }

    public Scene getCalendarScene() {
        // Set up the gridpane
        createWeekHeader();
        createCalendar();

        // Set style classes
        calendarGrid.getStyleClass().add("cal-grid");

        //
        pickerBox.setAlignment(Pos.TOP_CENTER);
        gridBox = new HBox(calendarGrid);
        gridBox.setAlignment(Pos.BOTTOM_CENTER);

        HBox.setHgrow(gridBox, Priority.ALWAYS);
        HBox.setHgrow(calendarGrid, Priority.ALWAYS);

        // Set up the anchors on the DatePicker / Gridpane
        VBox.setVgrow(gridBox, Priority.ALWAYS);
        VBox.setVgrow(pickerBox, Priority.SOMETIMES);
        root.getChildren().addAll(pickerBox,gridBox);
        root.setSpacing(15);
        root.setPadding(new Insets(15));

        calendarScene = new Scene(root,1000,900);

        return calendarScene;
    }
}
