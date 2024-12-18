public class Futbolista extends Thread {
    private int ngols;
    private int ntirades;

    public static final int NUM_JUGADORS = 11;
    public static final int NUM_TIRADES = 20;
    public static final float PROBABILITAT = 0.5f;

    public Futbolista(String nom){
        super(nom);
        this.ngols = 0;
        this.ntirades = 0;
    }

    public int getNgols() { return ngols; }
    public int getNtirades() { return ntirades; }

    @Override
    public void run() {

        for (int i= 0; i <= NUM_TIRADES; i++){
            ntirades++;
            if (Math.random() < PROBABILITAT){
                ngols++;
            }
        }
    }

    public static void main(String[] args) {
        String[] nomsJugadors = {
            "Piqué", "Vinicius", "Torres", "Ramos", "Ronaldo", 
            "Lewan", "Belli", "Arnau", "Aspas", "Messi", "MBapé"
        };

        Futbolista[] jugadors= new Futbolista[Futbolista.NUM_JUGADORS];
        for (int i =0; i < Futbolista.NUM_JUGADORS; i++){
            jugadors[i] = new Futbolista(nomsJugadors[i]);
        }

        System.out.println("Inici dels xuts --------------------");

        for (Futbolista jugador: jugadors){
            jugador.start();
        }

        for (Futbolista jugador: jugadors) {
            try {
                // Esperar a que els fils finalitzin
                jugador.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Fi dels xuts -----------------------");

        System.out.println("--- Estadístiques ------");

        for(Futbolista jugador: jugadors){
            System.out.println(jugador.getName() + "\t->\t" + jugador.getNgols() + " gols");
        }
    }
}
