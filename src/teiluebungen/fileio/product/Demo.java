package teiluebungen.fileio.product;

public class Demo {

    public static void main(String[] args) {

        ProductManager pm = new ProductManager();
        pm.add(new Product("Product1" , 1000.00, "ProdCat1"));
        pm.add(new Product("Product2" , 250.77, "ProdCat2"));
        pm.add(new Product("Product3" , 999.99, "ProdCat3"));

        try {
            pm.save(".\\data\\products.dat");
        } catch (ProductFileException e) {
            e.printStackTrace();
        }
        System.out.println("<<< Initial >>>");
        System.out.println(pm.toString());


        ProductManager prodm = new ProductManager();
        System.out.println("loading entries...");
        try {
            prodm.load(".\\data\\products.dat");
        } catch (ProductFileException e) {
            e.printStackTrace();
        }
        System.out.println("<<< Loaded >>>");
        System.out.println(prodm.toString());
    }

}
