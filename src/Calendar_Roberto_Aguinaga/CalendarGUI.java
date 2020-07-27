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
    public void start(Stage window) {
        // Create a calendar model with default constructor
        CalendarModel cm = new CalendarModel();
        // Get the starting scene
        Scene defaultScene = new CalendarScene().getCalendarScene();

        // Load the stylesheet
        defaultScene.getStylesheets().add(this.getClass().getResource(
                "./styleRules.css").toExternalForm());

        // Set the scene and show the stage.
        window.setScene(defaultScene);
        window.setTitle("Calendar");
        window.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
