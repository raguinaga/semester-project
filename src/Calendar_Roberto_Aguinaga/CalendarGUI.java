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


public class CalendarGUI extends Application {

    @Override
    public void start(Stage stage) {

        // Get date at program launch, so the default calendar scene
        // is the current year/month
        CalendarModel defaultModel =
                new CalendarModel(defaultDate.getMonthValue(),
                        defaultDate.getYear());

        // Create the top-level container, a VBox.
        VBox root = new VBox();

        // Create an hbox to house the top-half of the program, a hbox container
        // with a datePicker control, so that the user can move around to other dates.
        Label datePickerLabel = new Label("Use the form to pick a " +
                "new date");
        HBox datePickBox = new HBox(new DatePicker());
        root.getChildren().add(datePickBox);

        // Create another HBox to house the actual calendar, which is a gridpane
        HBox calendarBox = new HBox();
        GridPane calendarGrid = new GridPane();

        Scene scene = new Scene(root);
        stage.setTitle("To Do Calendar");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
