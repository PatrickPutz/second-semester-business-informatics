package komplettuebungen.chatserver;

import teiluebungen.fileio.logger.ErrorMessage;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ChatLogger {

    private String path;

    public ChatLogger() {
        this.path = ".\\data\\chatlog.txt";
    }

    public void logMessage(String name, String message){
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(this.path, true))) {
            String timeStamp = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
            bw.write(timeStamp + " - " + name + ": " + message);
            bw.newLine();
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
