package teiluebungen.fileio.datacopy;

import java.io.*;

public class DataCopy {

    public static void main(String[] args) {

        String fromPath = ".\\data\\copyfrom.txt";
        String toPath = ".\\data\\copyto.txt";

        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(fromPath));
             BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(toPath))) {

            int byteRead;
            while((byteRead = bis.read()) != -1){
                bos.write(byteRead);
            }
            bos.flush();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    }
