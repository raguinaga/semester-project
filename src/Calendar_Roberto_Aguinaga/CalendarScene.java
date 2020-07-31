/*
 * This class is meant to set up the graphical representation of a
 * calendar.
 */
package Calendar_Roberto_Aguinaga;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;


public class CalendarScene implements ReturnContent {

    // Javafx Fields for this scene.
    private CalendarModel model; // Calendar model
    private final VBox root = new VBox(); // root container for the whole
    // scene

    // Date picker control so users can move to other dates.
    private final DatePicker datePicker = new DatePicker();
    private Button goToDate;
    // Box for the above two controls
    private final HBox pickerBox = new HBox();

    // HBoxes for the calendar controls.
    private HBox gridBox; // for the calendar grid
    private HBox headerBox; // for the day labels
    private final GridPane calendarGrid = new GridPane();

    // Label telling user what month / year they are in.
    private Label nameLbl;

    /**
     * Default constructor sets up a new Calendar Model based on
     * LocalDate's now method. Then calls the rest of the methods in
     * the class to set up the Calander controls
     */
    public CalendarScene() {
        model = new CalendarModel();
        setUpDatePicker();
        createWeekHeader();
        createCalendar();
        setDayLabels();
        setUpRoot();

    }

    public CalendarScene(CalendarModel cm) {
        model = cm;
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

        // Set up internal Hboxes and labels.
        for (int i = 0; i < days.length; i++) {
            // Create HBoxes / labels, these HBoxes will contain the
            // labels that have the day names.
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

    /**
     * Prepares the gridpane that makes up the body of the calendar.
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


                dayCell.addEventHandler(MouseEvent.MOUSE_CLICKED,
                        event -> {
                            NoteScene noteScene = new NoteScene();
                            dayCell.getScene().setRoot(noteScene.getContent());
                        });


                // Add VBoxes to the GridPane
                calendarGrid.add(dayCell, cols, rows);
            }
        } // end of for-loop

        // Have to also add row and column constraints for consistent
        // VBox size, this adds an extra row that I couldn't figure
        // out how to avoid.
        for (int i = 0; i < days; i++) {
            RowConstraints rc = new RowConstraints(90);
            calendarGrid.getRowConstraints().add(rc);
            ColumnConstraints cc =
                    new ColumnConstraints(120);
            calendarGrid.getColumnConstraints().add(cc);
        }
        // Set style class for the grid
        calendarGrid.getStyleClass().add("cal-grid");
    }

    public void setDayLabels() {
        // Go back through each node in the gridpane and add the
        // numbers for the days
        int gridCount = 1;
        int lblCount = 1;
        int offset = model.getFirstDay();

        for (Node node : calendarGrid.getChildren()) {
            VBox dayCell = (VBox) node; // have to cast nodes

            // If the month does not start on Sunday, make it dark, do
            // not add number label
            if (gridCount <= offset) {
                gridCount++;
                node.setStyle("-fx-background-color: #737373");
            } else {
                // if the number of nodes exceeds the days in the
                // month, darken the days, do not add labels
                if (lblCount > model.getDaysInMonth()) {
                    node.setStyle("-fx-background-color: #737373");
                } else {
                    Label numberLbl = new Label(Integer.toString(lblCount));
                    dayCell.getChildren().add(numberLbl);

                    // Add a "number" ID property to each cell for use
                    // in getting the right note file.
                    dayCell.setId(Integer.toString(lblCount));
                }
                lblCount++;
            } // end of else-if chain
        } // end of for-each loop
    }

    public void setUpDatePicker() {
        goToDate = new Button("Go to date");
        goToDate.setOnMouseClicked(event -> {
            model = new CalendarModel(datePicker.getValue());
            CalendarScene calScene = new CalendarScene(model);
            goToDate.getScene().setRoot(calScene.getContent());
        });
    }


    /**
     *
     * @return
     */
    private void setUpRoot() {

        // Set up a label and HBox for displaying the Date above
        // the calendar
        nameLbl = new Label(model.getMonthName());
        HBox nameBox = new HBox(nameLbl);
        nameBox.setAlignment(Pos.CENTER);
        nameBox.setMaxWidth(600);
        nameBox.setMinHeight(70);
        nameBox.getStyleClass().add("name-label");

        // Set up the HBox for the date picker and button
        pickerBox.getChildren().addAll(datePicker, goToDate);
        pickerBox.setSpacing(10);
        pickerBox.setAlignment(Pos.TOP_CENTER);
        gridBox = new HBox(calendarGrid);
        gridBox.setAlignment(Pos.BOTTOM_CENTER);

        // Final root VBox setup
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(pickerBox, nameBox, headerBox,
                gridBox);
        root.setSpacing(5);
        root.setPadding(new Insets(15));

        root.setMaxSize(1000,900);

        // load the stylesheet
        root.getStylesheets().add(this.getClass().getResource(
                "./styleRules.css").toExternalForm());

    }

    @Override
    public Parent getContent() {
        return root;
    }
}
