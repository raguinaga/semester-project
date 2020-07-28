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
    //private CheckBox chkBox;
    private NoteHandler nh;
    private String checkedNote;


    private void setUpWriteBox() {
        // Set up writing area, make sure it fills the pane
        TextArea writeArea = new TextArea("Enter your notes here!");
        writeArea.setWrapText(true);
        VBox.setVgrow(writeArea, Priority.ALWAYS);

        // Set up buttons to save note or return to calendar view
        Button returnButton = new Button("Return to calendar view");
        Button saveNote = new Button("Save note");

        // Add style classes
        returnButton.getStyleClass().add("return-button");
        saveNote.getStyleClass().add("save-button");

        // Put buttons in an HBox, set properties
        HBox buttonBox = new HBox(returnButton, saveNote);
        buttonBox.setSpacing(15);
        buttonBox.setAlignment(Pos.CENTER);

        // Set up event handlers for buttons
        returnButton.setOnAction(event -> {
            mainStage.setScene(calendarScene);
        });
        saveNote.setOnAction(event -> {
            nh.writeNote(writeArea.getText());
            updateNoteList();
        });
        // Add to VBox
        writeBox.getChildren().addAll(writeArea, buttonBox);
    }

    /**
     * Sets up the initial view of the list.
     */
    private void setUpDisplayBox() {
        ArrayList<String> notes = nh.readNotes();
        noteList = new ListView<>();
        for (String note : notes) {
            CheckBox chkBox = new CheckBox(note);
            chkBox.selectedProperty().
                    addListener(((observable, oldValue, newValue) -> {
                        if (newValue) {
                            chkBox.getStyleClass().add("checked-box");
                        } else if (oldValue) {
                            chkBox.getStyleClass().add("unchecked-box");
                        }
                    }));
            noteList.getItems().add(chkBox);
        }
    }

    /**
     * This method basically does the same thing as refresh, but
     * doesn't duplicate the notes on the list view. This feels
     * clunky to use but it was fast to put together.
     */
    public void updateNoteList() {
        ArrayList<String> notes = nh.readNotes();
        noteList.getItems().clear();
        for (String note : notes) {
            CheckBox chkBox = new CheckBox(note);
            chkBox.selectedProperty().
                    addListener(((observable, oldValue, newValue) -> {
                        if (newValue) {
                            chkBox.getStyleClass().add("checked-box");
                        } else if (oldValue) {
                            chkBox.getStyleClass().add("unchecked-box");
                        }
                    }));
            noteList.getItems().add(chkBox);
        }
    }

    /**
     * Does a bunch of stuff, including setting up the noteHandler
     * class, setting up the Split Pane,applying some style classes,
     * and of course returning the scene.
     *
     * @param mainStage     A reference to the main stage.
     * @param calendarScene A reference to the calendar scene to go
     *                      back to
     * @param model         A reference to the calendar model, needed for the
     *                      note handler class
     * @return A Scene with a split Pane, for writing and reading notes
     */
    public Scene getScene(Stage mainStage, Scene calendarScene,
                          CalendarModel model) {
        // Get ref to main stage, store it.
        this.mainStage = mainStage;
        this.calendarScene = calendarScene;
        this.model = model;

        // Setup a new noteHandler object
        nh = new NoteHandler(model);

        // Set up VBoxes
        setUpDisplayBox();
        setUpWriteBox();

        // Set up split pane.
        //private VBox displayBox = new VBox();
        SplitPane splitPane = new SplitPane();
        splitPane.getItems().addAll(writeBox, noteList);
        splitPane.setOrientation(Orientation.HORIZONTAL);

        Scene noteScene = new Scene(splitPane, 1000, 900);

        // Apply style rules to this scene
        noteScene.getStylesheets().add(this.getClass().getResource(
                "./styleRules.css").toExternalForm());

        return noteScene;
    }
}
