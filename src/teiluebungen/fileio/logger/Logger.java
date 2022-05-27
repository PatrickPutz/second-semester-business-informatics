package teiluebungen.fileio.logger;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Logger {

    private String path;

    public Logger(String path) {
        this.path = path;
    }

    private void logMessage(ErrorMessage message){

        try(BufferedWriter bw = new BufferedWriter(new FileWriter(this.path, true))) {

            bw.write(message.toLine());
            bw.newLine();
            bw.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void logFatal(String message){
        String timeStamp = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
        ErrorMessage em = new ErrorMessage(ErrorLevelType.FATAL, message, timeStamp);
        logMessage(em);
    }

    public void logError(String message){
        String timeStamp = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
        ErrorMessage em = new ErrorMessage(ErrorLevelType.ERROR, message, timeStamp);
        logMessage(em);
    }

    public void logInfo(String message){
        String timeStamp = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
        ErrorMessage em = new ErrorMessage(ErrorLevelType.INFO, message, timeStamp);
        logMessage(em);
    }

    public void logDebug(String message){
        String timeStamp = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
        ErrorMessage em = new ErrorMessage(ErrorLevelType.DEBUG, message, timeStamp);
        logMessage(em);
    }

}
