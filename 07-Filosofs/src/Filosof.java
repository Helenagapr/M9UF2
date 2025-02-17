import java.util.Random;

public class Filosof extends Thread {
    private Forquilla forquillaEsq;
    private Forquilla forquillaDret;
    private int gana;  // Contador de "hambre" (intentos fallidos)
    private Random random;

    public Filosof(String nom, Forquilla forquillaEsq, Forquilla forquillaDret) {
        super(nom);
        this.forquillaEsq = forquillaEsq;
        this.forquillaDret = forquillaDret;
        this.gana = 0;
        this.random = new Random();
    }

    private void esperarEntre(int minMillis, int maxMillis) {
        int tiempo = minMillis + random.nextInt(maxMillis - minMillis + 1);
        try {
            Thread.sleep(tiempo);
        } catch (InterruptedException e) {
            
        }
    }

    // Simula el acto de comer.
    // El filósofo intenta coger primero la forquilla izquierda y luego la derecha.
    // Si no consigue la derecha, libera la izquierda, incrementa el contador y espera.
    public void menjar() {
        boolean haMenjat = false;
        while (!haMenjat) {
            // Intentar coger la forquilla izquierda.
            if (forquillaEsq.agafar()) {
                System.out.println("Filòsof: " + getName() + " agafa la forquilla esquerra " + forquillaEsq.getNumero());
                // Intentar coger la forquilla derecha.
                if (forquillaDret.agafar()) {
                    System.out.println("Filòsof: " + getName() + " agafa la forquilla dreta " + forquillaDret.getNumero());
                    System.out.println("Filòsof: " + getName() + " menja");
                    esperarEntre(1000, 2000);
                    // Liberar ambas forquilles después de comer.
                    forquillaEsq.deixar();
                    forquillaDret.deixar();
                    System.out.println("Filòsof: " + getName() + " ha acabat de menjar");
                    haMenjat = true;
                } else {
                    // No pudo coger la forquilla derecha: libera la izquierda y espera.
                    System.out.println("Filòsof: " + getName() + " deixa l'esquerra (" + forquillaEsq.getNumero() + ") i espera (dreta ocupada)");
                    forquillaEsq.deixar();
                    gana++;
                    System.out.println("Filòsof: " + getName() + " gana=" + gana);
                    esperarEntre(500, 1000);
                }
            } else {
                // No se pudo coger la forquilla izquierda; espera un tiempo y vuelve a intentarlo.
                esperarEntre(500, 1000);
            }
        }
    }

    // Simula el acto de pensar.
    public void pensar() {
        System.out.println("Filòsof: " + getName() + " pensant");
        esperarEntre(1000, 2000);
    }

    @Override
    public void run() {
        while (true) {
            menjar();
            pensar();
        }
    }

    public static void main(String[] args) {
        Taula taula = new Taula(4);
        taula.showTaula();
        taula.cridarATaula();
    }
}
