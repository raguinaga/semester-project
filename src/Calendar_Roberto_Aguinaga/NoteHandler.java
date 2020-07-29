/*
 * A class that does the IO work for the NoteScene class
 */

package Calendar_Roberto_Aguinaga;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class NoteHandler {
    // A calendar model
    private CalendarModel model;

    /**
     * Constructor takes model and stores the reference.
     * @param model A CalendarModel
     */
    public NoteHandler(CalendarModel model) {
        this.model = model;
    }

    /**
     * This is kind of a pointless method. But I kept it around
     * because I had already typed it out. It basically does the same
     * thing as File's file.exists()
     * @return A boolean, true if the file exists, false if it does not.
     */
    public boolean checkIfNoteExists() {
        File file = new File(model.dateString + ".txt");
        if (file.exists()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Writes notes to a file named after the ISO date of the current
     * day, or ideally whatever the current calendar model's date is
     * (though I never got that to work quite right).
     * @param notes A string, for things that need to be done. I
     *              honestly should've named this "tasks"
     * @throws IOException
     */
    public void writeNote( String notes) throws IOException {
        // Make the file name based on the
        String filename = model.dateString + ".txt";
        File file = new File(filename);
        // Check if the file exists, if it does not, create a new
        // file. If the file does exist, open it in append mode. Used
        // try-with-resources blocks for auto resource management.
        if (!checkIfNoteExists()) {
            try (FileWriter fileWriter = new FileWriter(file, false);
                 PrintWriter printWriter = new PrintWriter(fileWriter)) {
                printWriter.println(notes);
            } catch (IOException ioException) {
                System.err.println(ioException.getCause());
            } // End of try-catch clause
        } else {
            // Open file in append mode
            try (FileWriter fWriter = new FileWriter(filename, true);
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
     * @throws NullPointerException
     * @throws FileNotFoundException
     */
    public ArrayList<String> readNotes() throws NullPointerException,
            FileNotFoundException {
        ArrayList<String> notes = new ArrayList<>();
        try {
            // Make file object, check if it exist, if it does, start
            // scanning through it with a while loop + Scanner,
            // adding lines to the ArrayList
            File file = new File(model.dateString + ".txt");
            if (file.exists()) {
                Scanner noteScanner = new Scanner(file);
                while (noteScanner.hasNextLine()) {
                    notes.add(noteScanner.nextLine());
                }
                noteScanner.close();
            } else {
                // If the file does not exist just return an String
                // with a message. A rather clumsy way to deliver a
                // message but better than throwing a new
                // FileNotFoundException.
                notes.add("File not found");
            }
        } catch (NullPointerException e) {
            System.out.println(e.getCause());
        } catch (FileNotFoundException fe) {
            // I had to include this catch block or the Scanner would
            // not be constructed
            System.out.println(fe.getCause());
        }
        return notes;
    }
}
