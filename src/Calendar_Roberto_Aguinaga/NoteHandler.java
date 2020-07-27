package Calendar_Roberto_Aguinaga;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class NoteHandler {

    private CalendarModel model;


    public boolean checkIfNoteExists(CalendarModel model) {
        this.model = model;
        File file = new File(model.date + ".txt");
        if (file.exists()) {
            return true;
        } else {
            return false;
        }
    }

    public void writeNote(CalendarModel cm, String notes) {
        boolean flag = checkIfNoteExists(cm);
        String filename = cm.date + ".txt";
        File file = new File(filename);
        if (!flag) {
            try (
                    FileWriter fileWriter = new FileWriter(file, false);
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

        try {
            File file = new File(model.date + ".txt");
            Scanner noteScanner = new Scanner(file);
            while (noteScanner.hasNextLine()) {
                notes.add(noteScanner.nextLine());
            }
            noteScanner.close();
        }  catch (NullPointerException e) {
            System.err.println(e.getMessage());
        } catch (FileNotFoundException notFoundException) {
            System.err.println(notFoundException.getMessage());
        }
        return notes;
    }
}
