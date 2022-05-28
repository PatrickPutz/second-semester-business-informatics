package komplettuebungen.einwohner;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class EinwohnerManager {

    public static void main(String[] args) throws DataFileException {

        EinwohnerManager em = new EinwohnerManager();
        ArrayList<Einwohner> list = em.load();
        for (Einwohner einwohner : list) {
            System.out.println(einwohner.toString());
        }

    }

    public ArrayList<Einwohner> load() throws DataFileException {
        ArrayList<Einwohner> result = new ArrayList<>();

        try(BufferedReader br = new BufferedReader(new FileReader(".\\data\\testdata-einwohner.csv"))) {

            String line;
            br.readLine();
            while((line = br.readLine()) != null){

                String[] parts = line.split(";");
                result.add(new Einwohner(
                        Integer.parseInt(parts[0]),
                        parts[1],
                        parts[2],
                        Integer.parseInt(parts[3])
                ));

            }

        } catch (Exception e) {
            throw new DataFileException(e);
        }

        // Collections.sort(result);
        result.sort(new GeburtsjahrDescNameAscComparator());

        return result;
    }

}
