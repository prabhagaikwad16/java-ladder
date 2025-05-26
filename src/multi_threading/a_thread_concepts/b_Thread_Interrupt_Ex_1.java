package multi_threading.a_thread_concepts;

public class b_Thread_Interrupt_Ex_1 {
    public static void main(String[] args) {
        MyThread thread = new MyThread();
        thread.setName("MyThread");
        thread.start();
        thread.interrupt();
    }
}

class MyThread extends Thread {
    @Override
    public void run() {
        try {
            //Here sleep method is declared as throws InterruptedException
            //Thats why we are able to interrup and handle it.
            Thread.sleep(10000000000000000L);
        } catch (InterruptedException e) {
            System.out.println("Thread interrupted. Exiting the app.");
            System.exit(0);
        }
    }
}
