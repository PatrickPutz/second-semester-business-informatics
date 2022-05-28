package teiluebungen.fileio.transactions;

import java.io.Serializable;
import java.util.Objects;

public class Transaction implements Serializable {

    private String transactionDate;
    private String product;
    private double price;
    private String paymentType;
    private String name;
    private String city;
    private String state;
    private String country;

    public Transaction(String transactionDate, String product, double price, String paymentType, String name, String city, String state, String country) {
        this.transactionDate = transactionDate;
        this.product = product;
        this.price = price;
        this.paymentType = paymentType;
        this.name = name;
        this.city = city;
        this.state = state;
        this.country = country;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public String getProduct() {
        return product;
    }

    public double getPrice() {
        return price;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getCountry() {
        return country;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionDate='" + transactionDate + '\'' +
                ", product='" + product + '\'' +
                ", price=" + price +
                ", paymentType='" + paymentType + '\'' +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Double.compare(that.price, price) == 0 && Objects.equals(transactionDate, that.transactionDate) && Objects.equals(product, that.product) && Objects.equals(paymentType, that.paymentType) && Objects.equals(name, that.name) && Objects.equals(city, that.city) && Objects.equals(state, that.state) && Objects.equals(country, that.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(transactionDate, product, price, paymentType, name, city, state, country);
    }
}
