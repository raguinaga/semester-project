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
        LocalDate defaultDate = LocalDate.now();
        CalendarModel defaultModel =
                new CalendarModel(defaultDate.getMonthValue(),
                        defaultDate.getYear());

        BorderPane root;
        Scene scene = new Scene(root);
        stage.setTitle("To Do Calendar");
        //stage.setScene();
        stage.show();
    }

    public GridPane initializeCalendarGrid(CalendarModel cm) {
        GridPane calendarGrid;
        int columns = 7; // days in a week
        int rows = 6; // 

    }

    public static void main(String[] args) {
        launch(args);
    }
}
