package teiluebungen.exceptions.bank;

public class BankDemo {

    public static void main(String[] args) {

        Bank bank = new Bank();
        try {
            bank.addAccount("pat", 500.00, 500.00);
            bank.addAccount("max", 150.00,200.00);
            bank.addAccount("olaf", 2000.00,1000.00);
            System.out.println(bank);

            bank.transfer("olaf", "pat", 1000.00);
            System.out.println(bank);

            bank.transfer("max", "pat", 400.00);
        } catch (BankException e) {
            e.printStackTrace();
        } catch (AccountNotFoundException e) {
            e.printStackTrace();
        }

    }

}
