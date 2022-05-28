package teiluebungen.fileio.transactions;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class TransactionObjectHandler {

    public static void saveTransactions(ArrayList<Transaction> list, String path) throws TransactionObjectException {

        try(ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(path)))) {

            int i = 0;
            for (Transaction transaction : list) {
                oos.writeObject(transaction);
                i++;
                if(i == 100)
                    oos.flush();
            }
            oos.writeObject(null);
            oos.flush();

        } catch (FileNotFoundException e) {
            throw new TransactionObjectException(e);
        } catch (IOException e) {
            throw new TransactionObjectException(e);
        }

    }

}
