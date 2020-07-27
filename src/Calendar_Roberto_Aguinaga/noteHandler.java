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
        if (!flag) {
            String filename = cm.date + ".txt";
            File file = new File(filename);


            try {
                file.createNewFile();
                FileWriter fileWriter = new FileWriter(file, false);
                PrintWriter printWriter = new PrintWriter(fileWriter);
                printWriter.println(notes);
                printWriter.close();
                fileWriter.close();
            } catch (IOException ioException) {
                System.err.println(ioException.getMessage());
            } // End try-catch clause
        } else {
            try {
               FileWriter fWriter = new FileWriter(file, true);
            }
        }

    }
}
