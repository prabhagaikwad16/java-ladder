package multi_threading.b_thief_police_example;

public abstract class HackerThread extends Thread {
     Vault vault = null;
     public abstract void startHacking();
}
