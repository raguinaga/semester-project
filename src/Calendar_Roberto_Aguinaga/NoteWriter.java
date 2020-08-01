/*
 * A class that does the IO work for the NoteScene class
 */

package Calendar_Roberto_Aguinaga;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class NoteWriter {
    // Only one file variable to access throughout class.
    private final File FILE;


    public NoteWriter(String date) {
        // Use the calendar Model to create the string
        FILE = new File(date + ".txt");
    }

    /**
     * Writes notes to a file named after the ISO date of the current
     * day, or ideally whatever the current calendar model's date is
     * (though I never got that to work quite right).
     * @param notes A string, for things that need to be done. I
     *              honestly should've named this "tasks"
     */
    public void writeNote( String notes) {
        // Make the file name based on the
        // Check if the file exists, if it does not, create a new
        // file. If the file does exist, open it in append mode. Used
        // try-with-resources blocks for auto resource management.
        if (!FILE.exists()) {
            try (FileWriter fileWriter = new FileWriter(FILE, false);
                 PrintWriter printWriter = new PrintWriter(fileWriter)) {
                printWriter.println(notes);
            } catch (IOException ioException) {
                System.err.println(ioException.getCause());
            } // End of try-catch clause
        } else {
            // Open file in append mode
            try (FileWriter fWriter = new FileWriter(FILE, true);
                 PrintWriter pWriter = new PrintWriter(fWriter)) {
                pWriter.println(notes);
            } catch (IOException e) {
                System.err.println(e.getCause());
            }
        } // end of if-else statement
    }

    /**
     * Reads notes from a file named after the current calendar
     * model's date. This turned out to be a mistake as it will
     * always display the model's date's notes, instead of the VBox
     * dayCell the user specified... Not sure of how to get the
     * specific date even now...
     * @return An ArrayList of Strings containing notes / tasks /
     * whatever is in the file
     */
    public ArrayList<String> readNotes() {
        ArrayList<String> notes = new ArrayList<>();
        try {
            // Make file object, check if it exist, if it does, start
            // scanning through it with a while loop + Scanner,
            // adding lines to the ArrayList
            if (FILE.exists()) {
                Scanner noteScanner = new Scanner(FILE);
                while (noteScanner.hasNextLine()) {
                    notes.add(noteScanner.nextLine());
                }
                noteScanner.close();
            }
        } catch (NullPointerException | FileNotFoundException e) {
            System.err.println(e.getCause());
        }
        return notes;
    }
}
