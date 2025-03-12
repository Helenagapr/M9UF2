import java.util.Random;

public class Barber extends Thread{
    private Random random;

    public Barber(String nom){
        super(nom);
        this.random = new Random();
    }

    @Override
    public void run(){

    }
}
