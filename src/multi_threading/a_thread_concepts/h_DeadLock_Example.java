package multi_threading.a_thread_concepts;

import java.util.Random;

public class h_DeadLock_Example {
    public static void main(String[] args) {
        Intersection intersection = new Intersection();
        TrainA trainA = new TrainA(intersection);
        TrainB trainB = new TrainB(intersection);

        Thread threadA = new Thread(trainA::run, "TrainA");
        Thread threadB = new Thread(trainB::run, "TrainB");

        threadA.start();
        threadB.start();
    }
}


class TrainA{
    private Intersection intersection;

    public TrainA(Intersection intersection) {
        this.intersection = intersection;
    }

    public void run() {
        while (true){
            intersection.crossRoadA();
        }
    }

}

class TrainB{
    private Intersection intersection;

    public TrainB(Intersection intersection) {
        this.intersection = intersection;
    }

    public void run() {
        while (true){
            intersection.crossRoadB();
            try {
                Thread.sleep(new Random().nextInt(1000)); // Simulating some delay
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}


class Intersection{
    private Object roadA = new Object();
    private Object roadB = new Object();


    public void crossRoadA() {
        synchronized (roadA) {
            synchronized (roadB) {
                System.out.println("TrainA is crossing the intersection on Road B");
            }
        }
    }

    //Here if we call lock roadB first then lock roadA, it will cause a deadlock
    //Note--> So imp point is that follow the same order of acquiring locks in all threads to avoid deadlock
    public void crossRoadB() {
        synchronized (roadA) {
            synchronized (roadB) {
                System.out.println("TrainB is crossing the intersection on Road A");
            }
        }
    }
}