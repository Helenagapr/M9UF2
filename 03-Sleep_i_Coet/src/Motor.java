import java.util.Random;

public class Motor extends Thread{
    private long potenciaObjectiva;
    private long potenciaActual;
    private Random random;
    
    public Motor(String nom) {
        super(nom);
        this.potenciaActual = 0;
        this.potenciaObjectiva = 0;
        this.random = new Random();
    }

    public long getPotenciaObjectiva() { return potenciaObjectiva; }
    public long getPotenciaActual() { return potenciaActual; }

    public synchronized void setPotencia(int potenciaObjectiu) {
        this.potenciaObjectiva = potenciaObjectiu;
        notify();
    }

    @Override
    public void run() {
        Random random = new Random();

        try {
            while (true) { 
                synchronized (this) {
                    while (potenciaActual == potenciaObjectiva) {
                        wait();
                        if (potenciaActual == 0 && potenciaObjectiva == 0) break;
                    }
                }

                if (potenciaActual < potenciaObjectiva) {

                    potenciaActual++;

                    if (potenciaActual == potenciaObjectiva) {
                        System.out.printf("%s: Fer res Objectiu: %d Actual: %d\n", getName(), potenciaObjectiva, potenciaActual);
                    } else {
                        System.out.printf("%s: Incre. Objectiu: %d Actual: %d\n", getName(), potenciaObjectiva, potenciaActual);
                    }
                } else if (potenciaActual > potenciaObjectiva) {

                    potenciaActual--;
                    
                    if (potenciaActual == potenciaObjectiva) {
                        System.out.printf("%s: Fer res Objectiu: %d Actual: %d\n", getName(), potenciaObjectiva, potenciaActual);
                    } else {
                        System.out.printf("%s: Decre. Objectiu: %d Actual: %d\n", getName(), potenciaObjectiva, potenciaActual);
                    }
                }

                Thread.sleep(random.nextInt(2000));

                if (potenciaActual == 0 && potenciaObjectiva == 0) break;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
