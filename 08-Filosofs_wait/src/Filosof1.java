import java.util.Random;

public class Filosof1 extends Thread {
    private final int id;
    private final Forquilla1 forquillaEsq;
    private final Forquilla1 forquillaDret;
    private int gana = 0;

    public Filosof1(int id, Forquilla1 forquillaEsq, Forquilla1 forquillaDret) {
        this.id = id;
        this.forquillaEsq = forquillaEsq;
        this.forquillaDret = forquillaDret;
    }

    private void esperarEntre(int minMillis, int maxMillis) {
        try {
            Thread.sleep(minMillis + (int) (Math.random() * (maxMillis - minMillis + 1)));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void menjar() throws InterruptedException {
        while (true) {
            System.out.println("Fil\u00f2sof: fil" + id + " intentant menjar");

            forquillaEsq.agafar(id);
            if (forquillaDret.getPropietari() == Forquilla1.LLIURE) {
                forquillaDret.agafar(id);
                System.out.println("Fil\u00f2sof: fil" + id + " menja");
                esperarEntre(1000, 2000);
                forquillaDret.deixar(id);
            }
            forquillaEsq.deixar(id);
            
            System.out.println("Fil\u00f2sof: fil" + id + " pensant");
            esperarEntre(1000, 2000);
            gana++;
            System.out.println("Fil\u00f2sof: fil" + id + " gana=" + gana);
        }
    }

    @Override
    public void run() {
        try {
            menjar();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    
    
    public static void main(String[] args) {
        System.out.println("-------------------------------------");
        Taula1 taula = new Taula1(5);
        taula.cridarATaula();
    }
}
