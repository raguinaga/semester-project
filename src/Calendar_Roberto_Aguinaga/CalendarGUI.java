package Calendar_Roberto_Aguinaga;

import javafx.application.Application;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.Scene;

import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class CalendarGUI extends Application {

    @Override
    public void start(Stage stage) {

        stage.setTitle("To Do Calendar");
        //stage.setScene();
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
