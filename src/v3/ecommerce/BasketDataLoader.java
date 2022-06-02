package v3.ecommerce;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class BasketDataLoader {

//    public static void main(String[] args) throws DataFileException {
//        ArrayList<BasketData> baskets = load(".\\data\\buyings.json", new BasketComparator());
//        for (BasketData basket : baskets) {
//            System.out.println(basket.toString());
//        }
//    }

    public static ArrayList<BasketData> load(String path) throws DataFileException {
        try(BufferedReader br = new BufferedReader(new FileReader(path))) {
            ArrayList<BasketData> result = new ArrayList<>();
            String line;
            while((line = br.readLine()) != null){
                BasketData bd = new Gson().fromJson(line, BasketData.class);
                result.add(bd);
            }
            return result;
        } catch (Exception e) {
            throw new DataFileException("Error while loading: " + path, e);
        }
    }

    public static ArrayList<BasketData> load(String path, BasketComparator comparator) throws DataFileException {
        ArrayList<BasketData> result = load(path);
        result.sort(comparator);
        return result;
    }

}
