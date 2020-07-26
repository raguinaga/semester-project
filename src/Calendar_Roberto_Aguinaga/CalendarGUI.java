package Calendar_Roberto_Aguinaga;

import javafx.application.Application;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.Scene;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.GridPane;
import javafx.scene.control.DatePicker;

import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import java.time.LocalDate;
import java.time.Month;

public class CalendarGUI extends Application {

    @Override
    public void start(Stage stage) {
        // Get date at program launch, so the default calendar scene
        // is the current year/month
        LocalDate defaultDate = LocalDate.now();
        CalendarModel defaultModel =
                new CalendarModel(defaultDate.getMonthValue(),
                        defaultDate.getYear());

        // Create the top-level container, a border pane
        BorderPane root = new BorderPane();

        // Create a date picker and an exit button for the top pane,
        // add them both.
        DatePicker datePicker = new DatePicker();
        Button exitButton = new Button("Exit");
        HBox topBox = new HBox(datePicker, exitButton);
        root.setTop(topBox);

        Scene scene = new Scene(root);
        stage.setTitle("To Do Calendar");
        stage.setScene(scene);
        stage.show();
    }

    public void initializeCalendarGrid(CalendarModel cm) {
        GridPane calendarGrid;
        int columns = 7; // days in a week
        int rows = 6; // I honestly just based this off the # of rows
                      // in the microsoft calendar preview-thing in the
                      // taskbar
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {

            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
