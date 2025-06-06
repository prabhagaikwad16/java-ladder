package multi_threading.a_thread_concepts;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class j_ReentrantReadWriteLock_Intro {

    public static void main(String[] args) {
        StorageItemManager StorageItemManager = new StorageItemManager();
        List<ReadItemThread> ReadItemThreads = new ArrayList<>();
        for(int i =0; i<7; i++){
            ReadItemThreads.add(new ReadItemThread(StorageItemManager));
        }
        WriteItemThread writeItemThread = new WriteItemThread(StorageItemManager);

        long startTime = System.currentTimeMillis();
        writeItemThread.start();
        for(ReadItemThread ReadItemThread : ReadItemThreads) {
            ReadItemThread.start();
        }

        try {
            for (ReadItemThread ReadItemThread : ReadItemThreads) {
                ReadItemThread.join();
            }
            writeItemThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();

        System.out.println("Execution time: " + (endTime - startTime) + " ms");
    }
}

class ReadItemThread extends Thread {
    private StorageItemManager StorageItemManager;

    public ReadItemThread(StorageItemManager StorageItemManager) {
        this.StorageItemManager = StorageItemManager;
    }

    @Override
    public void run() {
        for(int i=0; i<10000; i++){
            StorageItemManager.read(i);
        }
    }
}

class WriteItemThread extends Thread {
    private StorageItemManager StorageItemManager;

    public WriteItemThread(StorageItemManager StorageItemManager) {
        this.StorageItemManager = StorageItemManager;
    }

    @Override
    public void run() {
        for(int i=0; i<10000; i++){
            StorageItemManager.add(i);
        }
    }
}


class StorageItemManager{
    List<Integer> storage = new ArrayList<>(1000);
    ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
    ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();

    public void add(Integer item) {
        try{
            writeLock.lock();
            storage.add(item);
        }finally {
            writeLock.unlock();
        }
    }

    public void read(Object item) {
        try {
            readLock.lock();
            if (storage.contains(item)) {
                System.out.println("Item found: " + item);
            } else {
                System.out.println("Item not found: " + item);
            }
        }finally {
            readLock.unlock();
        }
    }
}
