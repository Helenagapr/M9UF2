import java.util.Random;

public class Associacio {
    private int numSocis;
    private Soci[] socis;

    public Associacio(){
        this.numSocis = 1000;
        this.socis = new Soci[numSocis];

        for(int i= 0; i < numSocis; i++){
            socis[i] = new Soci();
        }
    }

    public void iniciaCompteTempsSocis(){
        for(Soci soci: socis){
            new Thread(soci).start();
        }
    }

    public void esperaPeriodeSocis(){
        try{
            for(Soci soci: socis){
                soci.join();
            }
        } catch(InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Execució interrompuda durant l'espera del període");
        }
    }

    public void mostraBalancComptes(){
        Compte compte = Compte.getInstancia();
        System.out.printf("Saldo: %.2f\n", compte.getSaldo());
    }


    public static void main(String[] args) {
        
        Associacio associacio = new Associacio();

        associacio.iniciaCompteTempsSocis();
        associacio.esperaPeriodeSocis();
        associacio.mostraBalancComptes();
    }
}
