/*
 * This package sets up the note scene for entering and viewing notes
 */

package Calendar_Roberto_Aguinaga;

import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.util.ArrayList;


public class NoteScene implements ReturnContent {
    private final int dayNumber;
    private final SplitPane root = new SplitPane();
    private Scene calendarScene; // Ref to previous calendar scene
    private CalendarModel model; // calendar model, again may be
    // cause of null pointer problems
    private final VBox writeBox = new VBox(); // VBox to house TextArea,
    // and button HBox
    private ListView<CheckBox> noteList; // ListView for checkboxes
    private NoteHandler noteHandler; // IO file-handler object

    public NoteScene(Label label, CalendarModel model) {
        this.model = model;
        dayNumber = Integer.parseInt(label.getText());
        setUpWriteBox();
        setUpDisplayBox();
        updateNoteList();

    }
    /**
     * Sets up the left VBox, text area, buttons and button HBox
     */
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
            returnButton.getScene()
                    .setRoot(new CalendarScene(model).getContent());
        });
        saveNote.setOnAction(event -> {
            noteHandler.writeNote(writeArea.getText());
            updateNoteList();
        });
        // Add to VBox
        writeBox.getChildren().addAll(writeArea, buttonBox);
    }

    /**
     * Sets up the initial view of the list.
     */
    private void setUpDisplayBox() {
        // Get strings that are notes
        ArrayList<String> notes = noteHandler.readNotes();
        // Construct the list view
        noteList = new ListView<>();
        // For loop sets up the Check boxes, adds event listeners to
        // control styles.
        for (String note : notes) {
            CheckBox chkBox = new CheckBox(note);
            // Add event listener
            chkBox.selectedProperty().
                    addListener(((observable, oldValue, newValue) -> {
                        if (newValue) {
                            chkBox.getStyleClass().add("checked-box");
                        } else if (oldValue) {
                            chkBox.getStyleClass().add("unchecked-box");
                        }
                    }));
            // Add items to list view
            noteList.getItems().add(chkBox);
        }
    }

    /**
     * This method basically does the same thing as refresh, but
     * doesn't duplicate the notes on the list view. This feels
     * clunky to use but it was fast to put together.
     */
    public void updateNoteList() {
        ArrayList<String> notes = noteHandler.readNotes();
        // Clear items in list view to avoid duplication.
        noteList.getItems().clear();
        for (String note : notes) {
            CheckBox chkBox = new CheckBox(note);
            // Add listener
            chkBox.selectedProperty().
                    addListener(((observable, oldValue, newValue) -> {
                        if (newValue) {
                            chkBox.getStyleClass().add("checked-box");
                        } else if (oldValue) {
                            chkBox.getStyleClass().add("unchecked-box");
                        }
                    }));
            // Add items to list view
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
    /*Public Scene getScene(Stage mainStage, Scene calendarScene,
                          CalendarModel model) {
        // Get ref to main stage, store it.
        this.mainStage = mainStage;
        this.calendarScene = calendarScene;
        this.model = model;

        // Setup a new noteHandler object
        noteHandler = new NoteHandler(model);

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
*/
    @Override
    public Parent getContent() {
        return root;
    }
}
