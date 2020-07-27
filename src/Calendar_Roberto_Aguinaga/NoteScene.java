package Calendar_Roberto_Aguinaga;

import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class NoteScene {
    private Stage mainStage;
    private Scene calendarScene;
    private HBox buttonBox;
    private VBox writeBox = new VBox();
    //private VBox displayBox = new VBox();
    private SplitPane splitPane;
    private Button returnButton;
    private Button saveNote;
    private TextArea writeArea;
    private ListView<CheckBox> noteList;
    private String date;

    private void setUpWriteBox() {
        writeArea = new TextArea();
        writeArea.setWrapText(true);
        VBox.setVgrow(writeArea, Priority.ALWAYS);

        returnButton = new Button("Return to calendar view");
        saveNote = new Button("Save note");

        returnButton.getStyleClass().add("return-button");

        buttonBox = new HBox(returnButton,saveNote);
        buttonBox.setSpacing(15);
        buttonBox.setAlignment(Pos.CENTER);

        // Set up event handlers for buttons
        returnButton.setOnAction(event -> {
            mainStage.setScene(calendarScene);
        });
        saveNote.setOnAction(event -> {

        });
        // Add to VBox
        writeBox.getChildren().addAll(writeArea,buttonBox);
    }

    private void setUpDisplayBox() {
        noteList = new ListView<>();
    }

    public Scene getScene(Stage mainStage, Scene calendarScene) {
        // Get ref to main stage, store it.
        this.mainStage = mainStage;
        this.calendarScene = calendarScene;

        // Set up VBoxes
        setUpWriteBox();

        // Set up split pane.
        splitPane = new SplitPane();
        splitPane.getItems().add(writeBox);
        splitPane.setOrientation(Orientation.VERTICAL);

        Scene noteScene = new Scene(splitPane, 1000, 900);

        // Apply style rules to this scene
        noteScene.getStylesheets().add(this.getClass().getResource(
                "./styleRules.css").toExternalForm());

        return noteScene;
    }
}
