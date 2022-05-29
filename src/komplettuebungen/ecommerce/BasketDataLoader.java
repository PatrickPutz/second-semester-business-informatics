package komplettuebungen.ecommerce;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class BasketDataLoader {

    public static ArrayList<BasketData> load(String path) throws DataFileException {
        ArrayList<BasketData> result = new ArrayList<>();

        try(BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while((line = br.readLine()) != null){
                BasketData bd = new Gson().fromJson(line,BasketData.class);
                result.add(bd);
            }
        } catch (Exception e) {
            throw new DataFileException("Path: " + path, e);
        }

        return result;
    }

    public static ArrayList<BasketData> load(String path, BasketComparator comparator) throws DataFileException {
        ArrayList<BasketData> result = load(path);
        result.sort(comparator);
        return result;
    }

}
