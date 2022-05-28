package teiluebungen.fileio.transactions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Demo {

    public static void main(String[] args) {

        ArrayList<Transaction> transactions = new ArrayList<>();
        try {
            transactions = new TransactionLoader().loadTransactions(".\\data\\transactions.csv");
        } catch (TransactionLoadException e) {
            e.printStackTrace();
        }

        Collections.sort(transactions, new PriceProductComparator());

        int count = 0;
        for (Transaction transaction : transactions) {
            System.out.println(transaction);
            count++;
        }
        System.out.println(count);

        try {
            TransactionObjectHandler.saveTransactions(transactions, ".\\data\\transaction-export.dat");
        } catch (TransactionObjectException e) {
            e.printStackTrace();
        }

        System.out.println("<<< ---- >>>");
        TransactionManager tm = new TransactionManager(transactions);
        HashMap<String, Integer> countByCity = tm.getTransactionCountByCity();
        for (Map.Entry<String, Integer> stringIntegerEntry : countByCity.entrySet()) {
            System.out.println(stringIntegerEntry);
        }

        System.out.println("<<< ---- >>>");
        System.out.println("Transactions - United Kingdom: " + tm.getCountOfTransactions("United Kingdom"));

        System.out.println("<<< ---- >>>");
        ArrayList<Transaction> transactionsToProduct = tm.getTransactionsToProduct("product2");
        for (Transaction transaction : transactionsToProduct) {
            System.out.println(transaction);
        }

        System.out.println("<<< ---- >>>");
        HashMap<String, Double> averageTransactionAmount = tm.getAverageTransactionAmountByPaymentType();
        for (Map.Entry<String, Double> stringDoubleEntry : averageTransactionAmount.entrySet()) {
            System.out.println(stringDoubleEntry);
        }

    }

}
