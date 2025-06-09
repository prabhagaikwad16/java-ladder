package multi_threading.a_thread_concepts;

import java.util.concurrent.locks.ReentrantLock;

public class ii_ReentrantLock_Practice {
    public static void main(String[] args) throws InterruptedException {

        CounterInReentrant counter = new CounterInReentrant();
        Thread t1 = new Thread(() -> {

            for (int i = 0; i < 10000; i++) {
                counter.increment();
            }
        });

        Thread t2 = new Thread(() -> {

            for (int i = 0; i < 10000; i++) {
                counter.increment();
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();;

        System.out.println("Final count = "+counter.getCount());
    }
}


class CounterInReentrant {
    private int count = 0;

    ReentrantLock lock = new ReentrantLock();

    public void increment() {
        if (lock.tryLock()) {
            try {
                System.out.println("got the lock, incrementing count." + Thread.currentThread().getName());
                count++;
            } finally {
                lock.unlock();
            }
        } else {
            System.out.println("Lock is not available skipped the increment." + Thread.currentThread().getName());
        }
    }

    public int getCount() {
        return count;
    }
}


