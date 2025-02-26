import java.util.Random;

public class FilosofLock extends Thread {
    private final int id;
    private final ForquillaLock forquillaEsq;
    private final ForquillaLock forquillaDret;
    private long iniciGana;
    private long fiGana;
    private long gana;

    public FilosofLock(int id, ForquillaLock forquillaEsq, ForquillaLock forquillaDret) {
        this.id = id;
        this.forquillaEsq = forquillaEsq;
        this.forquillaDret = forquillaDret;
        this.gana = 0;
    }

    private void esperarEntre(int minMillis, int maxMillis) {
        try {
            Thread.sleep(minMillis + (int) (Math.random() * (maxMillis - minMillis + 1)));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void pensar() {
        iniciGana = System.currentTimeMillis();
        System.out.println("Fil" + id + " pensant");
        esperarEntre(1000, 2000);
    }

    private void menjar() {
        agafarForquilles();
        fiGana = System.currentTimeMillis();
        gana = (fiGana - iniciGana) / 1000;
        System.out.println("Fil" + id + " té forquilles esq(" + forquillaEsq.getNumero() + ") dreta(" + forquillaDret.getNumero() + ")");
        System.out.println("Fil" + id + " menja amb gana " + gana);
        esperarEntre(1000, 2000);
        resetGana();
        deixarForquilles();
    }

    private void agafarForquilles() {
        while (true) {
            forquillaEsq.agafar();
            if (forquillaDret.getBloqueig().tryLock()) {
                return;
            }
            forquillaEsq.deixar();
            esperarEntre(500, 1000);
        }
    }

    private void deixarForquilles() {
        forquillaDret.deixar();
        forquillaEsq.deixar();
        System.out.println("Fil" + id + " ha acabat de menjar");
        System.out.println("Fil" + id + " deixa les forquilles");
    }

    private void resetGana() {
        iniciGana = System.currentTimeMillis();
        gana = 0;
    }

    @Override
    public void run() {
        while (true) {
            pensar();
            menjar();
        }
    }
    
    
    
}
