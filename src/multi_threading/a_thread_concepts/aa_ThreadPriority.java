package multi_threading.a_thread_concepts;

public class aa_ThreadPriority {

    public static void main(String[] args) {
        Thread1 thread1 = new Thread1();
        Thread2 thread2 = new Thread2();

        // Setting priorities
        thread1.setPriority(Thread.MIN_PRIORITY); // 1
        thread2.setPriority(Thread.MAX_PRIORITY); // 10

        System.out.println("Thread1 priority: " + thread1.getPriority());
        System.out.println("Thread2 priority: " + thread2.getPriority());

        // Starting threads
        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Thread1 extends Thread {
    @Override
    public void run() {
        for(int i=0; i<50; i++) {
            System.out.println("Thread1 is running with priority: " + getPriority());
            try {
                Thread.sleep(100); // Simulating work
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Thread2 extends Thread {
    @Override
    public void run() {
        for(int i=0; i<50; i++) {
            System.out.println("Thread2 is running with priority: " + getPriority());
            try {
                Thread.sleep(100); // Simulating work
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}