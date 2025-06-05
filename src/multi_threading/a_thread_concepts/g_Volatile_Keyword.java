package multi_threading.a_thread_concepts;

//2 uses of volatile keyword in java
// 1. To prevent compiler optimization and guarantees order of execution of statements.
// 2. In case of long or double variables, as these are 64 bit variables and can be read in two parts by the compiler, using volatile ensures that the variable is read as a whole from main memory and not in parts from CPU cache.
// In this example, we will see how volatile keyword can be used to prevent compiler optimization and ensure visibility of changes to variables across threads.


public class g_Volatile_Keyword {
    public static void main(String[] args) {

        Calculator calculator = new Calculator();
        Thread thread1 = new Thread(()->{

            for(int i=0; i<Integer.MAX_VALUE; i++){
                calculator.increment();
            }
        });

        Thread thread2 = new Thread(()->{
            for(int i=0; i<Integer.MAX_VALUE; i++){
                calculator.checkRaceCondition();
            }
        });

        thread1.start();
        thread2.start();
    }
}


class Calculator{

    //If we declare these variables as volitile then compiler will not optimize the code and will always read the latest value of these variables from main memory.

      private volatile int x = 0;
      private volatile int y = 0;

    public  void increment(){
        x++;
        y++;
    }
    public void checkRaceCondition(){
        if(y>x){
            System.out.println("Race condition detected: y = " + y + ", x = " + x);
        }
    }
}
