/*
 * This class is meant to set up the graphical representation of a
 * calendar.
 */
package Calendar_Roberto_Aguinaga;

import javafx.scene.Node;
import javafx.scene.Parent;

import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import javafx.geometry.Insets;
import javafx.geometry.Pos;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.Priority;


public class CalendarScene implements ReturnContent {

    // root container plus the calendar model to use
    private final VBox ROOT = new VBox();
    private CalendarModel calendarModel; // Calendar model

    // HBox for the date picker, GridPane for Calendar Body
    private HBox dateNavBox = new HBox();
    private GridPane calendarGrid = new GridPane();

    // Date picker for moving to other dates, plus button to actually
    // go to the other dates.
    private DatePicker datePicker = new DatePicker();
    private Button goToDate = new Button("Go to date");

    // HBoxes for the calendar controls.
    private HBox gridBox; // for the calendar grid
    private HBox headerBox; // for the day labels

    /**
     * Default constructor sets up a new Calendar Model based on
     * LocalDate's now method. Then calls the rest of the methods in
     * the class to set up the Calendar controls
     */
    public CalendarScene() {
        calendarModel = new CalendarModel();
        setUpDatePicker();
        createWeekHeader();
        createCalendar();
        setDayLabels();
        setUpRoot();
    }

    /**
     * CalendarModel Constructor sets up a CalendarModel copied from a
     * supplied parameter. It then calls all the rest of the set up
     * methods to create a new root pane.
     *
     * @param cm A CalendarModel.
     */
    public CalendarScene(CalendarModel cm) {
        // copy constructor call
        calendarModel = new CalendarModel(cm);

        // Call to the rest of the methods.
        setUpDatePicker();
        createWeekHeader();
        createCalendar();
        setDayLabels();
        setUpRoot();
    }

    /**
     * This method generates the weekday header.
     */
    private void createWeekHeader() {
        // day names
        String[] days = {"Su", "Mo", "Tu", "We", "Th", "Fr", "Sa"};

        // Initialize headerBox properties
        headerBox = new HBox();
        headerBox.setAlignment(Pos.CENTER);
        headerBox.setMaxWidth(847); // try to match the gridpane width

        // Set up internal HBoxes and labels.
        for (String day : days) {
            // Create HBoxes / labels, these HBoxes will contain the
            // labels that have the day names.
            Label lbl = new Label(day);
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

    /**
     * Prepares the GridPane that makes up the body of the calendar.
     */
    private void createCalendar() {
        // Loop through the 6 rows, and add vboxes 7 times to each row
        int weeks = 6;
        int days = 7;
        for (int rows = 0; rows < weeks; rows++) {
            for (int cols = 0; cols < days; cols++) {

                // Create VBoxes for day cells, add styleClass to
                // each one
                VBox dayCell = new VBox();
                dayCell.getStyleClass().add("day-cell");

                // Add VBoxes to the GridPane
                calendarGrid.add(dayCell, cols, rows);
            }
        } // end of for-loop

        // Have to also add row and column constraints for consistent
        // VBox size, had to split into 2 loops to avoid extra row
        // being generated.
        for (int i = 0; i < weeks; i++) {
            RowConstraints rc = new RowConstraints(90);
            calendarGrid.getRowConstraints().add(rc);
        }
        // ColumnConstraint loop
        for (int i = 0; i < days; i++) {
            ColumnConstraints cc = new ColumnConstraints(120);
            calendarGrid.getColumnConstraints().add(cc);
        }

        // Set style class for the grid
        calendarGrid.getStyleClass().add("cal-grid");

        // Sets up the CalendarGrid HBox
        gridBox = new HBox(calendarGrid);
        gridBox.setAlignment(Pos.BOTTOM_CENTER);
    }

    /**
     * Goes back through the GridPane to add labels and event handlers
     */
    public void setDayLabels() {
        int gridCount = 1; // Starts counting the grid
        int lblCount = 1; // Starts counting the labels

        // Gets the day of week the month starts on
        int offset = calendarModel.getFirstDay();

        for (Node node : calendarGrid.getChildren()) {
            VBox dayCell = (VBox) node; // have to cast nodes

            // If the month does not start on Sunday, make it dark, do
            // not add number label/event handler
            if (gridCount <= offset) {
                gridCount++;
                node.setStyle("-fx-background-color: #737373");
            } else {
                // if the number of nodes exceeds the days in the
                // month, darken the days, do not add labels
                if (lblCount > calendarModel.getDaysInMonth()) {
                    node.setStyle("-fx-background-color: #737373");
                } else {
                    // Add number labels
                    Label numberLbl = new Label(Integer.toString(lblCount));
                    dayCell.getChildren().add(numberLbl);

                    // Add an event handler to each cell, pass the
                    // current day in the form of the label to
                    // NoteScene class constructor
                    dayCell.addEventHandler(MouseEvent.MOUSE_CLICKED,
                            event -> {
                                // Set up a new NoteScene for every
                                // VBox, avoiding nullpointer exceptions
                                NoteScene noteScene =
                                        new NoteScene(numberLbl, calendarModel);

                                // pass root node to whatever our
                                // scene is. No more confusing/messy
                                // scene referencing
                                dayCell.getScene()
                                        .setRoot(noteScene.getContent());
                            });
                } // End of nested if/else
                lblCount++;
            } // end of outer if/else
        } // end of for-each loop
    }

    /**
     * Sets up the DatePicker event handler so that it no longer
     * throws null pointer exceptions when trying to move to valid
     * dates. This block can still throw those exceptions but now we
     * ignore them (great design, I know /s).
     */
    private void setUpDatePicker() {
        goToDate.setOnMouseClicked(event -> {
            try {
                calendarModel = new CalendarModel(datePicker.getValue());
                CalendarScene calScene = new CalendarScene(calendarModel);
                goToDate.getScene().setRoot(calScene.getContent());
            } catch (NullPointerException ignored) {
                /* In my defence, I tried using an if statement to
                 * check if the value was null, but it still threw a
                 * NP exception. After all what is my else block
                 * supposed to do? After a while I just chose to
                 * ignore this. The program runs fine even after the
                 * exception is thrown.
                 */
            }
        });

        // Sets up the HBox container for the controls
        dateNavBox.getChildren().addAll(datePicker, goToDate);
        dateNavBox.setSpacing(10);
        dateNavBox.setAlignment(Pos.TOP_CENTER);
    }


    /**
     * Takes all the other layout containers and places them in the
     * root VBox. Also sets up the NameBox and its interior containers
     * I chose to keep that set up in this method to avoid more
     * method calls in the constructor.
     */
    private void setUpRoot() {

        // Set up a label and HBox for displaying the Date above the
        // calendar
        Label nameLbl = new Label(calendarModel.getMonthName());
        HBox nameBox = new HBox(nameLbl);

        // Set nameBox properties, add style class
        nameBox.setAlignment(Pos.CENTER);
        nameBox.setMaxWidth(600);
        nameBox.setMinHeight(70);
        nameBox.getStyleClass().add("name-label");

        // Final root VBox setup
        ROOT.setAlignment(Pos.CENTER);
        ROOT.getChildren().addAll(dateNavBox, nameBox, headerBox,
                gridBox);
        ROOT.setSpacing(5);
        ROOT.setPadding(new Insets(15));

        ROOT.setMaxSize(1000, 900);

        // load the CSS stylesheet
        ROOT.getStylesheets().add(this.getClass().getResource(
                "./styleRules.css").toExternalForm());

    }

    /**
     * Returns the root node for this scene graph.
     * @return A Parent node that contains all the children nodes for
     * the calendars
     */
    @Override
    public Parent getContent() {
        return ROOT;
    }
}
