package teiluebungen.fileio.transactions;

import java.util.Comparator;

public class PriceProductComparator implements Comparator<Transaction> {
    @Override
    public int compare(Transaction o1, Transaction o2) {
        int result = Double.compare(o1.getPrice(), o2.getPrice());
        if(result == 0)
            result = o2.getProduct().compareTo(o1.getProduct());
        return result;
    }
}
