package Calendar_Roberto_Aguinaga;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;


public class CalendarGUI extends Application {

    @Override
    public void start(Stage window) {
        
        // Get the starting scene
        Scene defaultScene =
                new CalendarScene().getCalendarScene(window);

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
