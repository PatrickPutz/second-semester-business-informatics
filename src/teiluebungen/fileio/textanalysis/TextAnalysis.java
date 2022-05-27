package teiluebungen.fileio.textanalysis;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TextAnalysis {

    public static void main(String[] args) {

        String path = ".\\data\\copyfrom.txt";
        HashMap<Character, Integer> result = new HashMap<>();

        try(BufferedInputStream bis = new BufferedInputStream(new FileInputStream(path))) {

            int byteRead;
            while((byteRead = bis.read()) != -1){
                if(!result.containsKey((char) byteRead))
                    result.put((char) byteRead, 1);
                else
                    result.put((char) byteRead, result.get((char) byteRead) + 1);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (Map.Entry<Character, Integer> characterIntegerEntry : result.entrySet()) {
            System.out.println(characterIntegerEntry);
        }
    }

}
