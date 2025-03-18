import java.util.LinkedList;
import java.util.Queue;

public class Barberia extends Thread{
    private Queue<Client> salaEspera;
    private int maxCadires;
    private Object condBarber = new Object();
    private static Barberia instancia;
    
    public Barberia(int maxCadires){
        this.maxCadires = maxCadires;
        this.salaEspera = new LinkedList<>();
    }

    public static Barberia getInstance(int maxCadires) {
        if (instancia == null) {
            instancia = new Barberia(maxCadires);
        }
        return instancia;
    }
   
    public Queue<Client> getSalaEspera() {
        return salaEspera;
    }

    public Object getCondBarber() {
        return condBarber;
    }

    public int getMaxCadires() {
        return maxCadires;
    }

    public synchronized Client seguentClient() {
        return salaEspera.poll();
    }

    public void entrarClient(Client client) {
        synchronized (condBarber) {
            if (salaEspera.size() < maxCadires) {
                salaEspera.add(client);
                System.out.println(client.getNom() + " en espera");
                condBarber.notify();
            } else {
                System.out.println("No queden cadires, " + client.getNom() + " se'n va");
            }
        }
    }

    @Override
    public void run() {
        for (int i = 1; i <= 10; i++) {
            entrarClient(new Client(i));
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        for (int i = 11; i <= 20; i++) {
            entrarClient(new Client(i));
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public class Main {
        public static void main(String[] args) {
            Barberia barberia = Barberia.getInstance(3);
            Barber barber = new Barber("Pepe", barberia);
            
            barber.start();
            barberia.start();
        }
    }
    
}
