package teiluebungen.fileio.product;

import java.io.*;
import java.util.ArrayList;

public class ProductManager {

    private ArrayList<Product> products;

    public ProductManager() {
        this.products = new ArrayList<>();
    }

    public void add(Product p){
        this.products.add(p);
    }

    public void save(String path) throws ProductFileException {

        try(ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(path)))) {

            oos.writeObject(products);
            oos.flush();

        } catch (FileNotFoundException e) {
            throw new ProductFileException("Error while saving " + path, e);
        } catch (IOException e) {
            throw new ProductFileException("Error while saving " + path, e);
        }
    }

    public void load(String path) throws ProductFileException {

        try(ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(path)))) {

            this.products = (ArrayList<Product>) ois.readObject();

        } catch (FileNotFoundException e) {
            throw new ProductFileException("Error while loading " + path, e);
        } catch (IOException e) {
            throw new ProductFileException("Error while loading " + path, e);
        } catch (ClassNotFoundException e) {
            throw new ProductFileException("Wrong data format while loading " + path, e);
        }

    }

    @Override
    public String toString() {
        return "ProductManager{" +
                "products=" + products +
                '}';
    }
}
