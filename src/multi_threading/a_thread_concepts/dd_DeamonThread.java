package multi_threading.a_thread_concepts;

public class dd_DeamonThread {
    public static void main(String[] args) {
        Thread daemonThread = new Thread(() -> {
            while (true) {
                System.out.println("Daemon thread is running...");
                try {
                    Thread.sleep(1000); // Simulating work
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        //If I comment this line, then even main thread is done, daemon thread will keep on printing "Daemon thread is running..."
        //But if I set it as daemon thread, then when main thread is done, daemon thread will also stop.
        daemonThread.setDaemon(true); // Setting the thread as a daemon thread
        daemonThread.start(); // Starting the daemon thread

        System.out.println("Main thread is doing some work...");
        try {
            Thread.sleep(5000); // Main thread sleeps for 5 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Main thread is done. Exiting...");
    }
}


