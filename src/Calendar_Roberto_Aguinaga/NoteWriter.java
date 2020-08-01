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

    /**
     * Sets up the File object to be manipulated by the NoteScene
     * controls.
     *
     * @param date A String that uniquely identifies the date that
     *             was clicked on.
     */
    public NoteWriter(String date) {
        // Creates a file object but does not try to open it. We name
        // it after the date that was clicked on.
        FILE = new File(date + ".txt");
    }

    /**
     * Writes notes to a file named after the ISO date of the current
     * day.
     *
     * @param notes A string, for things that need to be done. I
     *              honestly should've named this "tasks"
     */
    public void writeNote(String notes) {
        // Check if the file exists. If it does not, create a new
        // file. If the file does exist, open it in append mode. Used
        // try-with-resources blocks for auto resource management.
        if (!FILE.exists()) {
            try (FileWriter fileWriter = new FileWriter(FILE, false);
                 PrintWriter printWriter = new PrintWriter(fileWriter)) {
                printWriter.println(notes);
            } catch (IOException ioException) {
                System.err.println(ioException.getCause());
            } // End of first try-catch clause
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
     * model's date.
     *
     * @return An ArrayList of Strings containing notes / tasks /
     * whatever is in the file
     */
    public ArrayList<String> readNotes() {
        ArrayList<String> noteList = new ArrayList<>();
        try {
            // Check if file exists, if it does, start
            // scanning through it with a while loop + Scanner,
            // adding lines to the ArrayList
            if (FILE.exists()) {
                Scanner noteScanner = new Scanner(FILE);
                while (noteScanner.hasNextLine()) {
                    noteList.add(noteScanner.nextLine());
                }
                noteScanner.close();
            } // If the file does not exist, do nothing.
        } catch (NullPointerException | FileNotFoundException e) {
            System.err.println(e.getCause());
        }
        return noteList;
    }

}
