/*
 * This package sets up the note scene for entering and viewing notes
 */

package Calendar_Roberto_Aguinaga;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.geometry.Orientation;

import java.util.ArrayList;


public class NoteScene implements ReturnContent {
    private final SplitPane root = new SplitPane();
    private final int dayNumber;
    private final CalendarModel model;
    private final VBox writeBox = new VBox(); // VBox to house TextArea,
    // and button HBox
    private ListView<CheckBox> noteList; // ListView for checkboxes
    private final NoteHandler noteHandler; // IO file-handler object

    public NoteScene(Label label, CalendarModel model) {
        this.model = model;
        dayNumber = Integer.parseInt(label.getText());
        noteHandler = new NoteHandler(model);
        setUpWriteBox();
        setUpDisplayBox();
        setUpRoot();
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
     * This method basically does the same thing as ListView's refresh,
     * but doesn't duplicate the notes on the list view.
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


    public void setUpRoot() {

        // Set up split pane
        root.getItems().addAll(writeBox, noteList);
        root.setOrientation(Orientation.HORIZONTAL);

         root.setMinSize(1000, 900);

        // Apply style rules to the root
        root.getStylesheets().add(this.getClass().getResource(
                "./styleRules.css").toExternalForm());

    }
    
    @Override
    public Parent getContent() {
        return root;
    }
}
