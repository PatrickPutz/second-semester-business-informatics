package teiluebungen.fileio.transactions;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class TransactionLoader {

    public ArrayList<Transaction> loadTransactions(String path) throws TransactionLoadException {
        ArrayList<Transaction> transactions = new ArrayList<>();

        try(BufferedReader br = new BufferedReader(new FileReader(path))) {

            String line = br.readLine();
            int count = 1;
            while((line = br.readLine()) != null){
                count++;
                String[] parts = line.split(";");
                if(parts.length != 8)
                    throw new TransactionLoadException("Invalid format in line " + count);

                transactions.add(new Transaction(
                        parts[0],
                        parts[1],
                        Double.parseDouble(parts[2]),
                        parts[3],
                        parts[4],
                        parts[5],
                        parts[6],
                        parts[7]));

            }

        } catch (FileNotFoundException e) {
            throw new TransactionLoadException("File not found", e);
        } catch (IOException e) {
            throw new TransactionLoadException("IO Exception", e);
        }

        return transactions;
    }

}
