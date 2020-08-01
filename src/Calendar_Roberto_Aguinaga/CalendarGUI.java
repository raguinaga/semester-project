/*
 * This class does little more than launch the main scene.
 */
package Calendar_Roberto_Aguinaga;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;


public class CalendarGUI extends Application {

    /**
     * Starts the Gui program
     * @param window The stage of this program
     */
    @Override
    public void start(Stage window) {

        // Get the starting scene
        Scene defaultScene = new Scene(new CalendarScene().getContent());

        // Set the scene and show the stage.
        window.setScene(defaultScene);
        window.setTitle("Calendar");
        window.show();
    }

    /**
     * Method main launches the program
     * @param args Command-line args
     */
    public static void main(String[] args) {
        launch(args);
    }
}
