package Calendar_Roberto_Aguinaga;

import javafx.application.Application;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.Scene;

import javafx.scene.control.DatePicker;

import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.geometry.Pos;

import java.security.PolicySpi;
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

        // Create the top-level container, a VBox.
        VBox root = new VBox();
        Label datePickerLabel  = new Label("Use the form to pick a " +
                "new date");
        HBox datePickBox = new HBox(new DatePicker());


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
