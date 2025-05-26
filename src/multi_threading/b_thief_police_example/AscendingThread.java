package multi_threading.b_thief_police_example;

public class AscendingThread extends HackerThread{

    AscendingThread(Vault vault){
        this.setName("Ascending Hacker Thread");
        this.vault = vault;
        this.setPriority(Thread.MAX_PRIORITY);
    }


    @Override
    public void startHacking() {
        System.out.println("Hacker thread: "+this.getName());
        for(int i=0; i<999999;i++){
            if(this.vault.correctPassword(i)){
                System.out.println(this.getName()+" has guessed the password: " + i);
                System.exit(0); // Exit the program if the password is guessed
            }
        }
    }

    public void run() {
        startHacking();
    }
}
