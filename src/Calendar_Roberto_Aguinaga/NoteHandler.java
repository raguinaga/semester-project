package Calendar_Roberto_Aguinaga;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class NoteHandler {
    // A calendar model
    private CalendarModel model;

    public NoteHandler(CalendarModel model) {
        this.model = model;
    }


    public boolean checkIfNoteExists() {
        File file = new File(model.dateString + ".txt");
        if (file.exists()) {
            return true;
        } else {
            return false;
        }
    }

    public void writeNote( String notes) {
        boolean flag = checkIfNoteExists();
        String filename = model.dateString + ".txt";
        File file = new File(filename);
        if (!flag) {
            try (FileWriter fileWriter = new FileWriter(file, false);
                 PrintWriter printWriter = new PrintWriter(fileWriter)) {
                printWriter.println(notes);
            } catch (IOException ioException) {
                System.err.println(ioException.getMessage());
            } // End of try-catch clause
        } else {
            try (FileWriter fWriter = new FileWriter(filename, true);
                 PrintWriter pWriter = new PrintWriter(fWriter)) {
                pWriter.println(notes);
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        } // end of if-else statement
    }

    public ArrayList<String> readNotes() {
        ArrayList<String> notes = new ArrayList<>();
        System.err.println(model);
        try {
            File file = new File(model.dateString + ".txt");
            if (file.exists()) {
                Scanner noteScanner = new Scanner(file);
                while (noteScanner.hasNextLine()) {
                    notes.add(noteScanner.nextLine());
                }
                noteScanner.close();
            } else {
                throw new FileNotFoundException();
            }
        } catch (NullPointerException e) {
            System.err.println("This is a null pointer exception");
        } catch (FileNotFoundException notFoundException) {
            System.err.println("This is a file not found exception");
        }
        return notes;
    }
}
