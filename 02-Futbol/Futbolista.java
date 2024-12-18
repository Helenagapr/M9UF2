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
}
