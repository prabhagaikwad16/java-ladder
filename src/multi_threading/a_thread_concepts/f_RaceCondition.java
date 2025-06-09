package multi_threading.a_thread_concepts;

public class f_RaceCondition {
    public static void main(String[] args) {

        Counter counter = new Counter();

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                counter.increment();
            }
            System.out.println("Thread 1 count: " + counter.count);
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                counter.increment();
            }
            System.out.println("Thread 2 count: " + counter.count);
        });

        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Final count: " + counter.count);
    }

}

class Counter {
    int count = 0;

    public void increment() {
        //If we remove the synchronized keyword, count may not be incremented correctly
        synchronized (this){
            count++;
        }
    }
}
