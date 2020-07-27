package Calendar_Roberto_Aguinaga;

import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class NoteScene {
    private Stage mainStage;
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

        // Set up event handlers for buttons
        returnButton.setOnAction(event -> {

        });
        saveNote.setOnAction(event -> {

        });
        // Add to VBox
        writeBox.getChildren().addAll(writeArea,buttonBox);
    }

    private void setUpDisplayBox() {

    }

    public Scene getScene(Stage mainStage) {
        // Get ref to main stage, store it.
        this.mainStage = mainStage;

        // Set up splitpane.
        splitPane = new SplitPane();
        splitPane.getItems().add(writeBox);
        splitPane.setOrientation(Orientation.VERTICAL);

        return noteScene = new Scene(splitPane,1000,900);
    }
}
