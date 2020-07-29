/*
 * This class is meant to set up the graphical representation of a
 * calendar.
 */
package Calendar_Roberto_Aguinaga;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.time.LocalDate;


public class CalendarScene {
    private final int WEEKDAYS = 7; // days in a week
    private final int WEEKROWS = 6; // I honestly just based this off
    // the windows taskbar calendar

    // Javafx Fields for this scene.
    private Stage mainStage; // reference to main stage in calendar GUI
    private CalendarModel model; // Calendar model
    private CalendarModel newModel;
    private Scene calendarScene; // the main scene with a calendar.
    private VBox root = new VBox(); // root container for the whole
    // scene

    // Date picker control so users can move to other dates.
    private DatePicker picker = new DatePicker();
    private Button goToDate;
    private HBox pickerBox = new HBox(); // Box for the above two
    // controls

    // HBoxes for the calendar controls.
    private HBox gridBox; // for the calendar grid
    private HBox headerBox; // for the day labels
    private GridPane calendarGrid = new GridPane();

    // Label telling user what month / year they are in.
    private Label nameLbl;

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
        for (int rows = 0; rows < WEEKROWS; rows++) {
            for (int cols = 0; cols < WEEKDAYS; cols++) {

                // Create VBoxes for day cells, add styleClass to
                // each one
                VBox dayCell = new VBox();
                dayCell.getStyleClass().add("day-cell");

                // on each cell, add an event handler to switch scenes.
                dayCell.addEventHandler(MouseEvent.MOUSE_CLICKED,
                        event -> {
                            mainStage.setScene(new NoteScene().getScene(mainStage,
                                    this.calendarScene, model));
                        });

                // Add VBoxes to the GridPane
                calendarGrid.add(dayCell, cols, rows);
            }
        } // end of for-loop

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
        // Set style class for the grid
        calendarGrid.getStyleClass().add("cal-grid");
    }

    public void setDayLabels() {
        // Go back through each node in the gridpane and add the
        // numbers for the days
        int gridCount = 1;
        int lblCount = 1;
        int offset = model.firstDay;

        for (Node node : calendarGrid.getChildren()) {
            VBox dayCell = (VBox) node; // have to cast nodes

            // If the month does not start on sundy, make it dark, do
            // not add number label
            if (gridCount <= offset) {
                gridCount++;
                node.setStyle("-fx-background-color: #737373");
            } else {
                // if the number of nodes exceeds the days in the
                // month, darken the days, do not add labels
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

    /**
     * The root of all my misery, switching over to a new date once
     * goes well, switching twice however, throws a null pointer
     * exception which I cannot resolve. I have given up.
     */
    public void setUpDatePicker() {
        goToDate = new Button("Go to date");
        goToDate.setOnMouseClicked(event -> {
            setUpCalModel(picker.getValue());
            try {
                mainStage.setScene(new CalendarScene().getNewScene(model));
            } catch (NullPointerException e) {
                ErrorStage.showMessage();
                System.exit(-1);
            }
        });
    }

    /**
     * Set up a new Calendar from the current system date
     */
    public void setUpCalModel() {
        model = new CalendarModel();
    }

    /**
     * Sets up a new calendar model from a LocalDate object
     * @param date A LocalDate object
     */
    public void setUpCalModel(LocalDate date) {
        model = new CalendarModel(date);
    }

    /**
     * Sets up the calendar scene with a CalendarModel reference that
     * has whatever the System date is as its date.
     * @param mainStage
     * @return
     */
    public Scene getDefaultScene(Stage mainStage) {
        // Get reference to main stage, to add to dayCell event handler.
        this.mainStage = mainStage;

        // Create a new Calendar Model based on the default date
        model = new CalendarModel();

        // Set up a label and HBox for displaying the Date above
        // the calendar
        nameLbl = new Label(model.monthName);
        HBox nameBox = new HBox(nameLbl);
        nameBox.setAlignment(Pos.CENTER);
        nameBox.setMaxWidth(600);
        nameBox.setMinHeight(70);
        nameBox.getStyleClass().add("name-label");

        // Set up the Calendar grid and date picker.
        createWeekHeader();
        createCalendar();
        setDayLabels();
        setUpDatePicker();

        // Set up the HBox for the date picker and button
        pickerBox.getChildren().addAll(picker, goToDate);
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

        calendarScene = new Scene(root, 1000, 900);

        // load the stylesheet
        calendarScene.getStylesheets().add(this.getClass().getResource(
                "./styleRules.css").toExternalForm());

        return calendarScene;
    }

    /**
     * I could not get an if-else statement above to correctly assign
     * the CalendarModel reference so this method does literally the
     * same thing as getDefaultScene except it assigns the
     * CalendarModel reference to the argument supplied by the caller
     * of this function
     * @param cm A CalendarModel object
     * @return A Scene.
     */
    public Scene getNewScene(CalendarModel cm) {
        // Make the model reference a new Cal Model
        model = cm;

        // Same Header Box/ date label stuff as above
        nameLbl = new Label(model.monthName);
        HBox dateLbl = new HBox(nameLbl);
        dateLbl.setAlignment(Pos.CENTER);
        dateLbl.setMaxWidth(600);
        dateLbl.setMinHeight(70);
        dateLbl.getStyleClass().add("name-label");

        // Set up the Calendar grid and date picker.
        createWeekHeader();
        createCalendar();
        setDayLabels();
        setUpDatePicker();

        pickerBox.getChildren().addAll(picker, goToDate);
        pickerBox.setSpacing(10);
        pickerBox.setAlignment(Pos.TOP_CENTER);
        gridBox = new HBox(calendarGrid);
        gridBox.setAlignment(Pos.BOTTOM_CENTER);

        // Final root VBox setup
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(pickerBox, dateLbl, headerBox,
                gridBox);
        root.setSpacing(5);
        root.setPadding(new Insets(15));

        calendarScene = new Scene(root, 1000, 900);

        // load the style sheet
        calendarScene.getStylesheets().add(this.getClass().getResource(
                "./styleRules.css").toExternalForm());

        return calendarScene;
    }
}
