/*
 * This package sets up the root node for viewing and entering notes.
 */

package Calendar_Roberto_Aguinaga;

import javafx.geometry.Insets;
import javafx.scene.Parent;

import javafx.scene.control.SplitPane;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import javafx.geometry.Pos;
import javafx.geometry.Orientation;

import java.util.ArrayList;


public class NoteScene implements ReturnContent {
    // Create root layout node, Calendar model variable
    private final SplitPane ROOT = new SplitPane();
    private final CalendarModel MODEL;

    // NoteWriter Class does the actual IO work of reading / writing
    // files
    private final NoteWriter NOTEWRITER;

    // VBox to house TextArea, ListView for notes
    private VBox writeBox = new VBox();
    private ListView<CheckBox> noteList; // ListView for checkboxes

    /**
     * Constructor takes a model and Label, calls all methods in
     * class to set up layouts + controls. Uses model and label to
     * create appropriate string for writing a unique file name to
     * contain the actual notes.
     * @param label Label that holds the specified day's date.
     * @param model A CalendarModel to create a new Cal model from.
     */
    public NoteScene(Label label, CalendarModel model) {
        // Get our own private class model, even if we don't really
        // manipulate it.
        this.MODEL = new CalendarModel(model);

        // Get int value from the dayCell VBox we clicked on. Use it
        // to make a somewhat unique date string. Yes it is verbose
        // but easier to read/reason about instead of a bunch of
        // nested calls
        int dayNumber = Integer.parseInt(label.getText());
        String dateString = model.getDateString(dayNumber);

        // Set up a new NoteWriter for this scene graph
        NOTEWRITER = new NoteWriter(dateString);

        // Set up the rest of the scene graph
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

        // Add style classes to buttons
        returnButton.getStyleClass().add("return-button");
        saveNote.getStyleClass().add("save-button");

        // Put buttons in an HBox, set HBox properties
        HBox buttonBox = new HBox(returnButton, saveNote);
        buttonBox.setSpacing(15);
        buttonBox.setAlignment(Pos.CENTER);

        // Set up event handlers for buttons
        returnButton.setOnAction(event -> {

            // Just get a new CalScene object instead of trying to
            // reference an old one, that way I hopefully avoid null
            // pointer exceptions
            returnButton.getScene()
                    .setRoot(new CalendarScene(MODEL).getContent());
        });

        saveNote.setOnAction(event -> {
            NOTEWRITER.writeNote(writeArea.getText());
            // Use custom method to refresh/redraw list.
            updateNoteList();
        });

        // Add to VBox
        writeBox.getChildren().addAll(writeArea, buttonBox);
    }

    /**
     * Sets up the initial view of the list.
     */
    private void setUpDisplayBox() {
        // Get notes from file if it exists, if not the ArrayList is
        // empty
        ArrayList<String> notes = NOTEWRITER.readNotes();

        // Construct the list view
        noteList = new ListView<>();

        // Enhanced for loop sets up the CheckBoxes, adds event
        // listeners to control styles.
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
        ArrayList<String> notes = NOTEWRITER.readNotes();

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
     * This method sets up the root node and adds the CSS stylesheet
     * to the scene graph.
     */
    public void setUpRoot() {

        // Set up split pane
        ROOT.getItems().addAll(writeBox, noteList);
        ROOT.setOrientation(Orientation.HORIZONTAL);
        ROOT.setPadding(new Insets(15));

        ROOT.setMaxSize(1000, 900);

        // Apply style rules to the root
        ROOT.getStylesheets().add(this.getClass().getResource(
                "./styleRules.css").toExternalForm());

    }

    /**
     * This method returns the root node.
     * @return SplitPane control that has been set up during
     * construction.
     */
    @Override
    public Parent getContent() {
        return ROOT;
    }
}
