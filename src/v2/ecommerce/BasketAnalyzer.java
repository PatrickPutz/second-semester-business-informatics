package v2.ecommerce;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BasketAnalyzer {

    private ArrayList<BasketData> baskets;

    public BasketAnalyzer(ArrayList<BasketData> baskets) {
        this.baskets = baskets;
    }

    public List<BasketData> getEveryNthBasket(int n){
        List<BasketData> result = new ArrayList<>();
        for(int i = 0; i < baskets.size(); i += n){
            result.add(baskets.get(i));
        }
        return result;
    }

    public List<BasketData> filterBaskets(String paymentType, Double from, Double to){
        List<BasketData> result = new ArrayList<>();
        for (BasketData b : baskets) {
            if(b.getPaymentType().equalsIgnoreCase(paymentType)
                    && b.getOrderTotal() >= from
                    && b.getOrderTotal() <= to)
                result.add(b);
        }
        return result;
    }

    public HashMap<String, ArrayList<Double>> groupByProductCategory(){
        HashMap<String, ArrayList<Double>> result = new HashMap<>();
        for (BasketData b : baskets) {
            if(!result.containsKey(b.getProductCategory()))
                result.put(b.getProductCategory(), new ArrayList<>());

            ArrayList<Double> list = result.get(b.getProductCategory());
            list.add(b.getOrderTotal());
            result.put(b.getProductCategory(), list);
        }
        return result;
    }

    public ArrayList<BasketData> getBaskets() {
        return baskets;
    }
}
