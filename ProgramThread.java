package Bank;

import java.util.Random;

public class ProgramThread extends Thread {

    //boolean to determine if thread will deposit or withdraw
    private final boolean isDepositThread;
    //name of thread
    private final String name;
    //account being shared
    private final Account account;
    //format to display output
    private final String format;

    /**
     * constructor
     * @param type
     * @param name
     * @param account
     * @param format 
     */
    public ProgramThread(boolean type, String name, Account account, String format) {
        super(name);
        this.isDepositThread = type;
        this.name = name;
        this.account = account;
        this.format = format;
    }

    //thread run
    public  void run() {
        //infinite loop
        while (true) {
            //deposit
            int low = 1;
            int hi = 200;
            //if withdraw thread, max amount to withdraw is 50
            if (!isDepositThread) {
                hi = 50;
            }
            //method to get even amount to deposit
            //or withdraw in range
            int amount = getEvenNumInRange(low, hi);
            
            //if deposit thread
            if (isDepositThread) {
                 //deposit the amount
                    account.deposit(amount);
                    //display it on console
                    System.out.printf(format, this.getName() + " deposits $" + amount, "", " Balance is $" + account.getBalance());
               //sleep for 1 seconds
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    System.out.println(ex);
                }
                 
            } else {
                //if able to withdraw
                boolean withdrawSuccess = account.withdraw(amount);
                 
                //if not able to withdraw
                    if (!withdrawSuccess) {
                        //display and
                        System.out.printf(format, "", this.getName() + " withdraws $" + amount, " Withdrawl - blocked - insufficeint funds");
                    }
                
               //block the thread
                while (!withdrawSuccess) {
                    withdrawSuccess = account.withdraw(amount);
                }
                //display the balance once amount is withdrawn
                System.out.printf(format, "", this.getName() + " withdraws $" + amount, " Balance is $" + account.getBalance());
                //and yield to other thread
                Thread.yield();
            }
        }
    }

    /**
     * Method to get even number in range
     * @param low
     * @param high
     * @return 
     */
    public int getEvenNumInRange(int low, int high) {
        Random r = new Random();
        int num = r.nextInt(high - low) + 1 + low;
        while (num % 2 != 0) {
            num = r.nextInt(high - low) + 1 + low;
        }
        return num;
    }
	
}
