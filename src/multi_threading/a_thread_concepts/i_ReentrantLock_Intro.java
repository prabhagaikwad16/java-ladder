package multi_threading.a_thread_concepts;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class i_ReentrantLock_Intro {

    public static void main(String[] args) {
        StorageManager storageManager = new StorageManager();
        List<ReadThread> readThreads = new ArrayList<>();
        for(int i =0; i<7; i++){
            readThreads.add(new ReadThread(storageManager));
        }
        WriteThread writeThread = new WriteThread(storageManager);

        long startTime = System.currentTimeMillis();
        writeThread.start();
        for(ReadThread readThread : readThreads) {
            readThread.start();
        }

        try {
            for (ReadThread readThread : readThreads) {
                readThread.join();
            }
            writeThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();

        System.out.println("Execution time: " + (endTime - startTime) + " ms");
    }
}

class ReadThread extends Thread {
    private StorageManager storageManager;

    public ReadThread(StorageManager storageManager) {
        this.storageManager = storageManager;
    }

    @Override
    public void run() {
        for(int i=0; i<10000; i++){
            storageManager.read(i);
        }
    }
}

class WriteThread extends Thread {
    private StorageManager storageManager;

    public WriteThread(StorageManager storageManager) {
        this.storageManager = storageManager;
    }

    @Override
    public void run() {
        for(int i=0; i<10000; i++){
            storageManager.add(i);
        }
    }
}


class StorageManager{
    List<Integer> storage = new ArrayList<>(1000);
    ReentrantLock lock = new ReentrantLock();

    public void add(Integer item) {
        try{
            lock.lock();
            storage.add(item);
        }finally {
            lock.unlock();
        }
    }

    public void read(Object item) {
        try {
            lock.lock();
            if (storage.contains(item)) {
                System.out.println("Item found: " + item);
            } else {
                System.out.println("Item not found: " + item);
            }
        }finally {
            lock.unlock();
        }
    }
}
