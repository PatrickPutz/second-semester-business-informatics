package v3.ecommerce;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BasketAnalyzer {

//    public static void main(String[] args) throws DataFileException {
//        ArrayList<BasketData> baskets = BasketDataLoader.load(".\\data\\buyings.json", new BasketComparator());
//        BasketAnalyzer ba = new BasketAnalyzer(baskets);
//
//        System.out.println("<<< Every Nth >>>");
//        List<BasketData> everyNth = ba.getEveryNthBasket(25000);
//        for (BasketData basketData : everyNth) {
//            System.out.println(basketData.toString());
//        }
//        System.out.println("<<< Filtered >>>");
//        List<BasketData> filtered = ba.filterBaskets("MasterCard", 50.00, 100.00);
//        for (BasketData basketData : filtered) {
//            System.out.println(basketData.toString());
//        }
//        System.out.println("<<< Grouped >>>");
//        HashMap<String, ArrayList<Double>> grouped = ba.groupByProductCategory();
//        for (Map.Entry<String, ArrayList<Double>> stringArrayListEntry : grouped.entrySet()) {
//            System.out.println(stringArrayListEntry);
//        }
//    }

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
        for (BasketData bd : baskets) {
            if(bd.getPaymentType().equals(paymentType)
                && bd.getOrderTotal() >= from
                && bd.getOrderTotal() <= to){
                result.add(bd);
            }
        }
        return result;
    }

    public HashMap<String, ArrayList<Double>> groupByProductCategory(){
        HashMap<String, ArrayList<Double>> result = new HashMap<>();
        for (BasketData bd : baskets) {
            if(!result.containsKey(bd.getProductCategory()))
                result.put(bd.getProductCategory(), new ArrayList<>());

            ArrayList<Double> list = result.get(bd.getProductCategory());
            list.add(bd.getOrderTotal());
            result.put(bd.getProductCategory(), list);
        }
        return result;
    }

}
