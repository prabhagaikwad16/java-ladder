package multi_threading.b_thief_police_example;

public class PoliceThread extends Thread{
    @Override
    public void run() {
        for(int i=0; i<10; i++){
            try {
                Thread.sleep(10);
                System.out.println(i);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("Police has arrived");
        System.exit(0);
    }
}
