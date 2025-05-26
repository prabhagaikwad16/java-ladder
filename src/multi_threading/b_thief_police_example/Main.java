package multi_threading.b_thief_police_example;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Vault vault = new Vault();
        HackerThread ascendingThread = new AscendingThread(vault);
        HackerThread descendingThread = new DescendingThread(vault);
        PoliceThread policeThread = new PoliceThread();

        List<Thread> threads = new ArrayList<>();
        threads.add(ascendingThread);
        threads.add(descendingThread);
        threads.add(policeThread);

        for(Thread t: threads){
            t.start();
        }
    }
}
