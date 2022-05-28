package teiluebungen.threading;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileReadWorker extends Worker implements Runnable{

    private String path;
    public ArrayList<String> lines;

    public FileReadWorker(String name, String path) {
        super(name);
        this.path = path;
        this.lines = new ArrayList<>();
    }

    @Override
    protected void work() {
        printStarted();
        try(BufferedReader br = new BufferedReader(new FileReader(path))) {

            String line;
            while((line = br.readLine()) != null && shouldRun){
                this.lines.add(line);
                Thread.sleep(500);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            printStopped();
        }

    }

    @Override
    public void run() {
        work();
    }
}
