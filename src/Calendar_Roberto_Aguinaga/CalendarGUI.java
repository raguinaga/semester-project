package Calendar_Roberto_Aguinaga;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.GridPane;
import javafx.scene.control.DatePicker;

import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class CalendarGUI extends Application {

    @Override
    public void start(Stage stage) {
        BorderPane root;
        GridPane calendarGrid;
        Scene scene = new Scene(root);
        stage.setTitle("To Do Calendar");
        //stage.setScene();
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
