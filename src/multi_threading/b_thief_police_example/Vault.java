package multi_threading.b_thief_police_example;

import java.util.Random;

public class Vault {
    private int password = (int) new Random().nextLong(999999);
    public boolean correctPassword(int guessedPassword){
        return this.password == guessedPassword;
    }
}
