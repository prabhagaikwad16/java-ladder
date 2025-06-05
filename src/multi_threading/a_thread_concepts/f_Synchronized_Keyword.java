package multi_threading.a_thread_concepts;

public class f_Synchronized_Keyword {
    public static void main(String[] args) throws InterruptedException {
        InventoryManager inventoryManager = new InventoryManager();
        IncrementThread incrementThread = new IncrementThread(inventoryManager);
        DecrementThread decrementThread = new DecrementThread(inventoryManager);
        incrementThread.start();
        decrementThread.start();

        incrementThread.join();
        decrementThread.join();
        inventoryManager.printCounter();
    }


}

class InventoryManager {
    private long counter;

    Object lock = new Object();

    public  void increment(){
        synchronized (lock) {
            this.counter++;
        }
    }

    public  void decrement(){
        synchronized (lock) {
            this.counter--;
        }
    }

    public void printCounter(){
        System.out.println("Counter: " + counter);
    }
}

class IncrementThread extends Thread{

    InventoryManager inventoryManager;

    IncrementThread(InventoryManager inventoryManager){
        this.inventoryManager = inventoryManager;
    }

    @Override
    public void run() {
        for(int i=0; i<10000; i++){
            inventoryManager.increment();
        }
    }
}

class DecrementThread extends Thread{

    InventoryManager inventoryManager;

    DecrementThread(InventoryManager inventoryManager){
        this.inventoryManager = inventoryManager;
    }

    @Override
    public void run() {
        for(int i=0; i<10000; i++){
            inventoryManager.decrement();
        }
    }
}


