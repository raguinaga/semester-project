package Calendar_Roberto_Aguinaga;

import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


import java.util.ArrayList;

public class NoteScene {
    private Stage mainStage;
    private Scene calendarScene;
    private CalendarModel model;
    private VBox writeBox = new VBox();
    private ListView<CheckBox> noteList;
    private NoteHandler nh;


    private void setUpWriteBox() {
        // Set up writing area
        TextArea writeArea = new TextArea();
        writeArea.setWrapText(true);
        VBox.setVgrow(writeArea, Priority.ALWAYS);

        // Set up buttons to save note or return to calendar view
        Button returnButton = new Button("Return to calendar view");
        Button saveNote = new Button("Save note");

        // Add style classes
        returnButton.getStyleClass().add("return-button");

        // Put buttons in an HBox, set properties
        HBox buttonBox = new HBox(returnButton, saveNote);
        buttonBox.setSpacing(15);
        buttonBox.setAlignment(Pos.CENTER);

        // Set up event handlers for buttons
        returnButton.setOnAction(event -> {
            mainStage.setScene(calendarScene);
        });
        saveNote.setOnAction(event -> {
            nh.writeNote(model, writeArea.getText());
        });
        // Add to VBox
        writeBox.getChildren().addAll(writeArea, buttonBox);
    }

    private void setUpDisplayBox() {
        ArrayList<String> notes = nh.readNotes();
        noteList = new ListView<>();
        for (String note: notes) {
            noteList.getItems().add(new CheckBox(note));
        }
    }

    public Scene getScene(Stage mainStage, Scene calendarScene,
                          CalendarModel model) {
        // Get ref to main stage, store it.
        this.mainStage = mainStage;
        this.calendarScene = calendarScene;
        this.model = model;

        // Setup a new noteHandler object
        nh = new NoteHandler();

        // Set up VBoxes
        setUpWriteBox();
        setUpDisplayBox();

        // Set up split pane.
        //private VBox displayBox = new VBox();
        SplitPane splitPane = new SplitPane();
        splitPane.getItems().addAll(writeBox,noteList);
        splitPane.setOrientation(Orientation.HORIZONTAL);

        Scene noteScene = new Scene(splitPane, 1000, 900);

        // Apply style rules to this scene
        noteScene.getStylesheets().add(this.getClass().getResource(
                "./styleRules.css").toExternalForm());

        return noteScene;
    }
}
