package multi_threading.a_thread_creation;

import java.math.BigInteger;

public class d_DeamonThread {

    public static void main(String[] args) {
        MyThread_3 myThread3 = new MyThread_3(BigInteger.valueOf(10000000000L), 1000000);
        myThread3.setName("MyThread_3");
        //When we do not have control to check a condition to check if thread is interrupted or not,
        // if its an external library then we can set the thread as deamon thread so that application can stop even if deamon thread is running.
        myThread3.setDaemon(true); // Setting the thread as daemon
        myThread3.start();
    }
}

class MyThread_3 extends Thread{

    BigInteger base;
    int power;

    MyThread_3(BigInteger base, int power){
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
                //Though this calculation will take long to execute, if this thread is set as deamon thread, it won't stop application from running.
                result = result.multiply(base);
        }

        return result;
    }
}
