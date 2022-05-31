package v2.einwohner;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class EinwohnerManager {

    public static ArrayList<Einwohner> load() throws DataFileException {

        try(BufferedReader br = new BufferedReader(new FileReader(".\\data\\testdata-einwohner.csv"))) {
            ArrayList<Einwohner> result = new ArrayList<>();

            br.readLine();
            String line;
            while((line = br.readLine()) != null){
                String[] parts = line.split(";");
                result.add(new Einwohner(
                        Integer.parseInt(parts[0]),
                        parts[1],
                        parts[2],
                        Integer.parseInt(parts[3])
                ));
            }

            result.sort(new GeburtsjahrDescNameAscComparator());
            return result;
        } catch (Exception e) {
            throw new DataFileException(e);
        }

    }

}
