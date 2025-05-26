package multi_threading.b_thief_police_example;

public class DescendingThread extends HackerThread{

    DescendingThread(Vault vault){
        this.setName("Descending Hacker Thread");
        this.vault = vault;
        this.setPriority(Thread.MAX_PRIORITY);
    }


    @Override
    public void startHacking() {
        System.out.println("Hacker thread: "+this.getName());
        for(int i=999999; i>=0;i--){
            if(this.vault.correctPassword(i)){
                System.out.println(this.getName()+" has guessed the password: " + i);
                System.exit(0); // Exit the program if the password is guessed
            }
        }
    }

    @Override
    public void run() {
        startHacking();
    }
}
