package Bank;

public class Main {

    public static void main(String[] args) {
        //format to display output on console
        String format = "%-32s %-32s %-15s\n";
        //output header
        System.out.printf(format, "Deposit threads ","Withdrawl threads","Balance");
        System.out.printf(format, "-----------------","-----------------","----------");
        //creating seven threads
        ProgramThread t[] = new ProgramThread[7];
        //creating account with balance 0
        Account acc = new Account(0);
        //creating thread
        for(int i=0;i<t.length;i++) {
            //first 3 threads are depositing
            if(i<=2)
            t[i] = new ProgramThread(true, "Thread " + (i+1), acc, format);
            else {
                //next 3 are withdrawing
                t[i] = new ProgramThread(false, "Thread " + (i+1), acc, format);
            }
            //starting thread
            t[i].start();
        }
    }
	
}
