package multi_threading.a_thread_creation;

import java.sql.SQLOutput;

public class a_CreateThread {
    public static void main(String[] args) throws InterruptedException {


        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Thread.currentThread().setName("Child Thread 1");
                System.out.println("Current thread is: "+Thread.currentThread().getName());
                Thread.currentThread().setUncaughtExceptionHandler(
                        (t, e) -> System.out.println("Uncaught exception in thread: " + t.getName() + ", Exception: " + e.getMessage())
                );

                throw new RuntimeException("Exception in Child Thread 1");
            }
        });
        System.out.println("Before starting a new thread:"+Thread.currentThread().getName());
        thread.start(); // Start the new thread
        Thread.sleep(1000L);
        Thread.currentThread().setUncaughtExceptionHandler(
                (t, e) -> System.out.println("Uncaught exception in thread: " + t.getName() + ", Exception: " + e.getMessage())
        );
        Thread.currentThread().setName("Main Thread");
        System.out.println("After starting a new thread:"+Thread.currentThread().getName());
    }
}
