package teiluebungen.fileio.transactions;

import java.util.ArrayList;
import java.util.HashMap;

public class TransactionManager {

    private ArrayList<Transaction> transactions;

    public TransactionManager(ArrayList<Transaction> transactions) {
        this.transactions = transactions;
    }

    public HashMap<String, Integer> getTransactionCountByCity(){
        HashMap<String, Integer> result = new HashMap<>();

        for (Transaction t : transactions) {

            if(!result.containsKey(t.getCity()))
                result.put(t.getCity(), 1);
            else
                result.put(t.getCity(), result.get(t.getCity()) + 1);

        }

        return result;
    }

    public Integer getCountOfTransactions(String country){
        int result = 0;

        for (Transaction t : transactions) {
            if(t.getCountry().equalsIgnoreCase(country))
                result++;
        }

        return result;
    }

    public ArrayList<Transaction> getTransactionsToProduct(String product){
        ArrayList<Transaction> result = new ArrayList<>();

        for (Transaction t : transactions) {
            if(t.getProduct().equalsIgnoreCase(product))
                result.add(t);
        }

        return result;
    }

    public HashMap<String, Double> getAverageTransactionAmountByPaymentType(){
        HashMap<String, Double> result = getTotalTransactionAmountByPaymentType();
        HashMap<String, Integer> count = getCountOfTransactionsByPaymentType();

        for (String s : result.keySet()) {
            result.put(s, (result.get(s) / count.get(s)));
        }

        return result;
    }

    private HashMap<String, Integer> getCountOfTransactionsByPaymentType(){
        HashMap<String, Integer> result = new HashMap<>();

        for (Transaction t : transactions) {
            if(!result.containsKey(t.getPaymentType()))
                result.put(t.getPaymentType(), 1);
            else
                result.put(t.getPaymentType(), result.get(t.getPaymentType()) + 1);
        }

        return result;
    }

    private HashMap<String, Double> getTotalTransactionAmountByPaymentType(){
        HashMap<String, Double> result = new HashMap<>();

        for (Transaction t : transactions) {
            if(!result.containsKey(t.getPaymentType()))
                result.put(t.getPaymentType(), t.getPrice());
            else{
                double amount = result.get(t.getPaymentType()) + t.getPrice();
                result.put(t.getPaymentType(), amount);
            }
        }

        return result;
    }

}
