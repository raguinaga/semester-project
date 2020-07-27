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
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;



public class CalendarScene {
    private final int WEEKDAYS = 7; // days in a week
    private final int WEEKROWS = 6; // I honestly just based
    private Stage mainStage;
    private Scene noteScene;
    private CalendarModel model;
    private Scene calendarScene; // the main scene with a calendar.
    private VBox root = new VBox();
    private DatePicker picker = new DatePicker(); // So users can
    // move to other dates
    private HBox pickerBox = new HBox(picker);
    // for calendar.
    private HBox gridBox;
    private HBox headerBox;
    private GridPane calendarGrid = new GridPane();
    private Label nameLbl;

    /**
     * This method generates the weekday header.
     */
    private void createWeekHeader() {
        String[] days = {"Su", "Mo", "Tu", "We", "Th", "Fr", "Sa"};

        // Initialize headerBox properties
        headerBox = new HBox();
        headerBox.setAlignment(Pos.CENTER);
        headerBox.setMaxWidth(847); // try to match the gridpane width

        // Set up internal Hboxes and labels.
        for (int i = 0; i < days.length; i++) {
            // Create HBoxes, these HBoxes will contain the labels
            // that have the day names.
            Label lbl = new Label(days[i]);
            HBox lblBox = new HBox(lbl);

            // Make sure the HBoxes fill up the header box HBox
            HBox.setHgrow(lblBox, Priority.ALWAYS);
            lblBox.setAlignment(Pos.CENTER);

            // Add style class to Hboxes and labels
            lbl.getStyleClass().add("day-label");
            lblBox.getStyleClass().add("header-row");

            // Add label HBoxes to header HBox
            headerBox.getChildren().add(lblBox);
        }
        // Add style class to the header row
        headerBox.getStyleClass().add("header-box");
    }

    private void createCalendar() {
        // Loop through the 6 rows, and add vboxes 7 times to each row
        for (int rows = 0; rows < WEEKROWS; rows++) {
            for (int cols = 0; cols < WEEKDAYS; cols++) {

                // Create VBoxes for day cells, add styleClass
                VBox dayCell = new VBox();
                dayCell.getStyleClass().add("day-cell");

                // on each cell, add an event handler to switch scenes.
                dayCell.addEventHandler(MouseEvent.MOUSE_CLICKED,
                        event -> {
                            mainStage.setScene(new NoteScene().getScene(mainStage,
                                    this.calendarScene));
                        });

                // Add VBoxes to the GridPane
                calendarGrid.add(dayCell, cols, rows);
            }
        }

        // Have to also add row and column constraints for consistent
        // VBox size, this adds an extra row that I couldn't figure
        // out how to avoid.
        for (int i = 0; i < WEEKDAYS; i++) {
            RowConstraints rc = new RowConstraints(90);
            calendarGrid.getRowConstraints().add(rc);
            ColumnConstraints cc =
                    new ColumnConstraints(120);
            calendarGrid.getColumnConstraints().add(cc);
        }
        // Set style classes
        calendarGrid.getStyleClass().add("cal-grid");
    }

    public void setDayLabels() {
        // Go back through each node in the gridpane and add the
        // numbers for the days
        int gridCount = 2;
        int lblCount = 1;
        int offset = model.firstDay;
        for (Node node : calendarGrid.getChildren()) {
            VBox dayCell = (VBox) node;
            if (gridCount < offset) {
                gridCount++;
                node.setStyle("-fx-background-color: #737373");
            } else {
                if (lblCount > model.daysInMonth) {
                    node.setStyle("-fx-background-color: #737373");
                } else {
                    Label numberLbl = new Label(Integer.toString(lblCount));
                    dayCell.getChildren().add(numberLbl);
                }
                lblCount++;
            }
        }
    }

        public Scene getCalendarScene (Stage mainStage){
            // Get reference to main stage, to add to dayCell event handler.
            this.mainStage = mainStage;

            // Create a new Calendar Model
            model = new CalendarModel();

            // Set up a label and HBox for displaying the Date
            Label dateLbl = new Label(model.date);
            HBox nameBox = new HBox(dateLbl);
            nameBox.setAlignment(Pos.CENTER);
            nameBox.getStyleClass().add("name-label");

            // Set up the Calendar grid
            createWeekHeader();
            createCalendar();
            setDayLabels();

            // Some more aesthetics adjustments
            pickerBox.setAlignment(Pos.TOP_CENTER);

            gridBox = new HBox(calendarGrid);
            gridBox.setAlignment(Pos.BOTTOM_CENTER);
            gridBox.setSpacing(0);
            gridBox.setPadding(new Insets(0));
            gridBox.setPrefHeight(calendarGrid.getPrefHeight());


            // Set up the anchors on the DatePicker / Gridpane
            VBox.setVgrow(pickerBox, Priority.SOMETIMES);
            root.setAlignment(Pos.CENTER);
            root.getChildren().addAll(pickerBox, nameBox, headerBox,
                    gridBox);
            root.setSpacing(0);
            root.setPadding(new Insets(15));

            calendarScene = new Scene(root, 1000, 900);

            return calendarScene;
        }
    }
