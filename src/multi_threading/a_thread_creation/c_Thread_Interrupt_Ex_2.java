package multi_threading.a_thread_creation;

import java.math.BigInteger;

public class c_Thread_Interrupt_Ex_2 {
    public static void main(String[] args) {
        MyThread_1 myThread1 = new MyThread_1(BigInteger.valueOf(10000000000L), 1000000);
        myThread1.start();
        myThread1.interrupt();
    }
}

class MyThread_1 extends Thread {

    BigInteger base;
    int power;

    MyThread_1(BigInteger base, int power){
        this.base = base;
        this.power = power;
    }

    public void run(){
        BigInteger result = pow(base, power);
        System.out.println("result = "+result);
    }

    private BigInteger pow(BigInteger base, int power){
        BigInteger result = BigInteger.ONE;
        for(int i=0;i<power;i++){
            //unlike sleep method even if try to interrupt the thread below calculation will not be stopped thats why we need to find a hot spot and add the condtion if(thread.isInterrupted()) then
            if(this.isInterrupted()){
                System.out.println("Thread interrupted. Exiting the app.");
                System.exit(0);
            }else{
                result = result.multiply(base);
            }
        }

        return result;
    }
}
