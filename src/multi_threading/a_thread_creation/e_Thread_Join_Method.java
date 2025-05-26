package multi_threading.a_thread_creation;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class e_Thread_Join_Method {

    public static void main(String[] args) throws InterruptedException {
        int arr[] = {1000,2,40,5555887,678998};

        List<FactorialThread> threads = new ArrayList<>(arr.length);
        for(int i: arr){
            FactorialThread thread = new FactorialThread(i);
            threads.add(thread);
            thread.start();

        }

        for(FactorialThread t: threads){
            //pass the timeout value in case if you want to put time limit on wait
           t.join(200);
        }

        for(FactorialThread t: threads){
            if(t.isFinished()){
                System.out.println("Factorial of " + t.getNumber() + " is " + t.getFactorial());
            }else{
                System.out.println("Factorial of " + t.getNumber() + " is not yet calculated.");
            }
        }
    }
}

class FactorialThread extends Thread{
    private int number;
    private BigInteger factorial;
    private boolean isFinished;

    FactorialThread(int number){
        this.number = number;
        this.setDaemon(true); // Optional: Set as daemon thread if you want it to not block the application exit
    }
    @Override
    public void run() {
         factorial = BigInteger.ONE;
        for(int i=1; i<=number; i++){
            factorial  = factorial.multiply(BigInteger.valueOf(i));
        }
        isFinished = true;
    }

    public BigInteger getFactorial(){
        return factorial;
    }

    public boolean isFinished(){
        return isFinished;
    }

    public int getNumber() {
        return number;
    }
}
