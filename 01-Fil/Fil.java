public class Fil extends Thread {
    private String nom;
    private static final Object lock = new Object();

    public Fil(String nom) {
        this.nom = nom;
    }

    @Override
    public void run() {
        synchronized (lock) {
            for (int i = 1; i <= 9; i++) {
                System.out.println(nom + " " + i);
                try {
                    // Pausa per simular el temps de treball
                    Thread.sleep(100);
                    // Despertar l'altre fil per executar
                    lock.notify();
                    if (i < 9) {
                        // Esperar el torn de l'altre fil
                        lock.wait();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("Termina el fil " + nom);
    }
}
