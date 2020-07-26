package Calendar_Roberto_Aguinaga;

import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.io.*;

public class noteHandler {

    // Set up a scene where user can add a note for the day
    SplitPane root = new SplitPane();
    Scene noteScene = new Scene(root);

    public void addNote(VBox day) {
        Label aboutLbl = new Label("Type in the notes for today.");
        // Create a textBox so user can input one note at a time.
        TextArea writeNote = new TextArea();
        writeNote.setWrapText(true);

        // Make an anchor pane with a button to save the note.
        AnchorPane anchorPane = new AnchorPane();
        Button saveNoteButton = new Button("Save note");
        AnchorPane.setBottomAnchor(saveNoteButton,0.0);

        VBox textAreaBox = new VBox(aboutLbl, writeNote, anchorPane);
        textAreaBox.setPrefSize(300, 500);
        root.getItems().add(textAreaBox);
    }

    public static void displayNote() {
        Label aboutLbl = new Label("Check the items you wish to mark " +
                "as done");
        ListView<String> noteList = new ListView<>();
        VBox listContainer = new VBox(noteList);


    }

    /*protected String[] getNotes() {

    }*/

    /*protected void writeNotes(String note) {
        File noteFile = new File(CalendarModel.monthName + );
        try {

        } catch (IOException e) {

        }
    }*/

    public Scene getNoteScene() {
        return noteScene;
    }

}
