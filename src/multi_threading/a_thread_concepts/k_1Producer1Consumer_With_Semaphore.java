package multi_threading.a_thread_concepts;

import java.util.LinkedList;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public class k_1Producer1Consumer_With_Semaphore {
    public static void main(String[] args) {
        LinkedList<Item> queue = new LinkedList<>();
        Semaphore full = new Semaphore(0); // Initially, the queue is empty
        Semaphore empty = new Semaphore(10); // Maximum size of the queue
        ReentrantLock lock = new ReentrantLock();
        BinaryProducer producer = new BinaryProducer(queue, full, empty, lock);
        BinaryConsumer consumer = new BinaryConsumer(queue, full, empty, lock);
        producer.start();
        consumer.start();
    }
}

class Item{
    private int value;

    public Item(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}

class BinaryProducer extends Thread {
    private LinkedList<Item> queue;
    Semaphore full;
    Semaphore empty;
    ReentrantLock lock;

    public BinaryProducer(LinkedList<Item> queue,Semaphore full, Semaphore empty,ReentrantLock lock) {
        this.queue = queue;
        this.full = full;
        this.empty = empty;
        this.lock = lock;
    }

    private void produce(){
        try {
            empty.acquire();   // Wait for empty slot
            lock.lock();
            Item item = new Item((int) (Math.random() * 100));
            queue.offer(item);
            System.out.println("Produced: " + item.getValue());
            lock.unlock();
            full.release();
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true) {
            produce();
            try {
                Thread.sleep(100); // Simulating production time
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class BinaryConsumer extends Thread {
    private LinkedList<Item> queue;
    Semaphore full;
    Semaphore empty;
    ReentrantLock lock;

    public BinaryConsumer(LinkedList<Item> queue,Semaphore full, Semaphore empty,ReentrantLock lock) {
        this.queue = queue;
        this.full = full;
        this.empty = empty;
        this.lock = lock;
    }

    private void consume(){
        try{
            full.acquire();    // Wait for available item
            lock.lock();
            Item item = queue.poll();
            System.out.println("Consumed: " + item.getValue());
            lock.unlock();
            empty.release();   // Signal that one slot is empty

        }catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true) {
            consume();
            try {
                Thread.sleep(100); // Simulating production time
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


