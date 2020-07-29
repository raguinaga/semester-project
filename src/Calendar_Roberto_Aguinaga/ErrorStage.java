package Calendar_Roberto_Aguinaga;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;


public class ErrorStage extends Application {
    @Override
    public void start(Stage errorStage) {
        Label errorLbl = new Label("☉_☉\n"+"Sorry,the program ran " +
                "into a problem and had to close.");
        errorLbl.setWrapText(true);
        Scene errorScn = new Scene(errorLbl);
        errorStage.show();
    }
}
