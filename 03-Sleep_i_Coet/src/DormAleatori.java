import java.util.Random;

public class DormAleatori extends Thread {

    private Random random;
    private long tempsCreat; 
    private long totaltemps;
    
    public DormAleatori(String nom){
        super(nom);
        this.totaltemps = 1;
        this.tempsCreat = System.currentTimeMillis();
        this.random = new Random();
    }

    public long getTempsCreat() { return tempsCreat; }
    public long getTotaltemps() { return totaltemps; }

    @Override
    public void run() {
        System.out.println(getName() + "(0) a dormir " + random.nextInt(991) + 10 + "ms total " + totaltemps);

        for (int i = 1; i < 10; i++){
            int interval = random.nextInt(991) + 10;
            totaltemps = System.currentTimeMillis() - tempsCreat;

            System.out.printf("%s(%d) a dormir %dms total %d%n", getName(), i, random.nextInt(991) + 10, totaltemps);

            // Dormim l'interval aleatori
            try {
                Thread.sleep(interval);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) {
        
        DormAleatori joan = new DormAleatori("Joan");
        DormAleatori pep = new DormAleatori("Pep");

        joan.start();
        pep.start();

        System.out.println("-- Fi de main -----------");
  
    }
}
