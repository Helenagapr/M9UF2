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

    public void setPotencia(int p){
        this.potenciaObjectiva = p;
        int step = (potenciaObjectiva > potenciaActual) ? 1 : -1;

        while (potenciaActual != potenciaObjectiva) {
            try {
                Thread.sleep(random.nextInt(2000)); // Espera aleatòria entre 0 i 10 ms
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            potenciaActual += step;
            if (potenciaActual == potenciaObjectiva) {
                System.out.printf("Motor %s: FerRes Objectiu: %d Actual: %d%n", getName(), potenciaObjectiva, potenciaActual);
            } else {
                System.out.printf("Motor %s: %s Objectiu: %d Actual: %d%n", getName(), (step > 0 ? "Incre." : "Decre."), potenciaObjectiva, potenciaActual);
            }
        }
    }

    @Override
    public void run() {
        // Mantenim el fil actiu, però la configuració del motor es fa des de setPotencia
    }
}
