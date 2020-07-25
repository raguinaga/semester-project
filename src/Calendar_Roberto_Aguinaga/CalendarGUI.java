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
        Button login = new Button("login");
        Button cancel = new Button("cancel");
        HBox hbox = new HBox(10, login, cancel);
        AnchorPane root = new AnchorPane(hbox);
        AnchorPane.setRightAnchor(hbox, 10d);
        AnchorPane.setBottomAnchor(hbox, 10d);
        Scene scene = new Scene(root);
        stage.setTitle("To Do Calendar");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
