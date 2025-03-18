import java.util.Random;

public class Barber extends Thread{

    private Barberia barberia;

    public Barber(String nom, Barberia barberia){
        super(nom);
        this.barberia = barberia;

    }

    @Override
    public void run(){
        while (true) {
            Client client = barberia.seguentClient();
            if (client == null) {
                System.out.println("Ningú en espera");
                System.out.println("Barber " + getName() + " dormint");
                synchronized (barberia.getCondBarber()) {
                    try {
                        barberia.getCondBarber().wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                System.out.println("Li toca al client " + client.getNom());
                client.tallarseElCabell();
                try {
                    Thread.sleep(900 + (int) (Math.random() * 100));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
