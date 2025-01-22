import java.util.Random;

public class Soci extends Thread{
    
    private Compte compte;
    private float aportacio;
    private int esperaMax;
    private Random random;
    private int maxAnys;

    public Soci(){
        this.compte = Compte.getInstancia();
        this.aportacio = 10.0f;
        this.esperaMax = 100;
        this.random = new Random();
        this.maxAnys = 10;
    }

    public Compte getCompte(){ return compte; }

    @Override
    public void run(){
        for(int any=1; any <= maxAnys; any++){
            for(int mes=1; mes<= 12; mes++){
                if(mes % 2 == 0){
                    compte.ingressar(aportacio);
                } else {
                    compte.retirar(aportacio);
                }

                try {
                    Thread.sleep(random.nextInt(esperaMax));
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.out.println("Execució interrompuda");
                }
            }
        }
    }
}
