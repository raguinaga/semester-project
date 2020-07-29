package Calendar_Roberto_Aguinaga;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;


public abstract class ErrorStage {
    protected static Stage errorStg;

    public static void showMessage() {
        Label errorLbl = new Label("☉_☉\n"+"Sorry,the program ran " +
                "into a problem and had to close.");
        errorLbl.setAlignment(Pos.CENTER);
        errorLbl.setStyle("-fx-font-weight: bolder;" +
                "-fx-font-size: 20");
        errorLbl.setWrapText(true);
        Scene errorScn = new Scene(errorLbl);
        errorStg.setScene(errorScn);
        errorStg.showAndWait();
    }
}
