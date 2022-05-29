package komplettuebungen.ecommerce;

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
        for (BasketData basket : baskets) {
            if(basket.getPaymentType().equals(paymentType)
                    && basket.getOrderTotal() >= from
                    && basket.getOrderTotal() <= to)
                result.add(basket);
        }
        return result;
    }

    public HashMap<String, ArrayList<Double>> groupByProductCategory(){
        HashMap<String, ArrayList<Double>> result = new HashMap<>();
        for (BasketData basket : baskets) {
            if(!result.containsKey(basket.getProductCategory()))
                result.put(basket.getProductCategory(), new ArrayList<Double>());

            ArrayList<Double> list = result.get(basket.getProductCategory());
            list.add(basket.getOrderTotal());
        }
        return result;
    }

}
