package Bank;

public class Account {

    //to hold balance
    private double balance;

    //get set
    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    //Constructors
    public Account() {
    }

    public Account(double balance) {
        this.balance = balance;
    }

    //deposit
    public  void deposit(double balance) {
        this.balance += balance;
    }

    //withdraw
    public  boolean withdraw(double balance) {

        if (this.balance < balance) {
            return false;
        }
        this.balance -= balance;
        return true;
    }
	
}
