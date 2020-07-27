package Calendar_Roberto_Aguinaga;

import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class NoteScene {

    private Scene noteScene;
    private HBox buttonBox;
    private VBox writeBox = new VBox();
    private VBox displayBox = new VBox();
    private SplitPane splitPane;
    private Button returnButton;
    private Button saveNote;
    private TextArea writeArea;
    private String date;

    private void setUpWriteBox() {
        writeArea = new TextArea();
        writeArea.setWrapText(true);
        VBox.setVgrow(writeArea, Priority.ALWAYS);

        returnButton = new Button("Return to calendar view");
        saveNote = new Button("Save note");

        buttonBox = new HBox(returnButton,saveNote);
        buttonBox.setSpacing(15);

        // Add to VBox
        writeBox.getChildren().addAll(writeArea,buttonBox);
    }

    private void setUpDisplayBox() {

    }

    public Scene getScene() {
        splitPane = new SplitPane();
        splitPane.getItems().add(writeBox);
        splitPane.setOrientation(Orientation.VERTICAL);

        return noteScene = new Scene(splitPane,1000,900);
    }
}
