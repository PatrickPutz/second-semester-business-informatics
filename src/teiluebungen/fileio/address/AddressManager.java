package teiluebungen.fileio.address;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class AddressManager {

    private ArrayList<Address> addresses;

    public AddressManager() {
        this.addresses = new ArrayList<>();
    }

    public void loadFromCsv(String path, String separator) throws AddressLoadException {

        try(BufferedReader br = new BufferedReader(new FileReader(path))) {

            String line;
            while((line = br.readLine()) != null ){

                String[] parts = line.split(separator);
                if(parts.length != 4)
                    throw new AddressLoadException("Wrong format!");

                addresses.add(new Address(parts[0], parts[1], parts[2], parts[3]));

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public ArrayList<Address> getAddresses() {
        return addresses;
    }

    @Override
    public String toString() {
        return "AddressManager{" +
                "addresses=" + addresses +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddressManager that = (AddressManager) o;
        return Objects.equals(addresses, that.addresses);
    }

    @Override
    public int hashCode() {
        return Objects.hash(addresses);
    }
}
