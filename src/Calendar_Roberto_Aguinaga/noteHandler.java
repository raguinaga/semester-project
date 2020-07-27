package Calendar_Roberto_Aguinaga;

import javafx.stage.Stage;

import java.io.*;
import java.time.LocalDate;

public class noteHandler {

    public boolean checkIfNoteExists(CalendarModel model) {
        File file = new File(model.date);
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
                 PrintWriter printWriter = new PrintWriter(fileWriter)){
                printWriter.println(notes);
            } catch (IOException ioException) {
                System.err.println(ioException.getMessage());
            } // End try-catch clause
        } else {
            try (FileWriter fWriter = new FileWriter(filename, true);
                 PrintWriter pWriter = new PrintWriter(fWriter)) {

            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        } // end of if-else statement
    }
}
