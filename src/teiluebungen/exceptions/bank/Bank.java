package teiluebungen.exceptions.bank;

import java.util.HashMap;

public class Bank {

    private HashMap<String, Account> accounts;

    public Bank() {
        this.accounts = new HashMap<>();
    }

    public void addAccount(String owner, double balance, double overdraftFrame) throws BankException {
        if(this.accounts.containsKey(owner))
            throw new BankException("Account already exists!");

        this.accounts.put(owner, new Account(owner, balance, overdraftFrame));
    }

    public void transfer(String fromOwner, String toOwner, double amount) throws AccountNotFoundException, BankException {

        if(!this.accounts.containsKey(fromOwner) || !this.accounts.containsKey(toOwner))
            throw new AccountNotFoundException("One or both accounts do not exist!");

        Account from = this.accounts.get(fromOwner);
        Account to = this.accounts.get(toOwner);

        try{
            from.debit(amount);
            to.credit(amount);
        } catch (NotEnoughMoneyException e) {
            throw new BankException("Transfer impossible.");
        }
    }

    @Override
    public String toString() {
        return "Bank{" +
                "accounts=" + accounts +
                '}';
    }
}
