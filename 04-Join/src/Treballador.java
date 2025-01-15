import java.util.Random;

public class Treballador extends Thread{
    private final float nou_anual_brut;
    private final int edat_inici_treball;
    private final int edat_fi_treball;
    private int edat_actual;
    private float cobrat;
    private final Random rnd;

    public Treballador(float nou_anual_brut, int edat_inici_treball, int edat_fi_treball, String nom) {
        super(nom);
        this.nou_anual_brut = nou_anual_brut;
        this.edat_inici_treball = edat_inici_treball;
        this.edat_fi_treball = edat_fi_treball;
        this.edat_actual = 0;
        this.cobrat = 0.0f;
        this.rnd = new Random();
    }

    public void cobra() {
        cobrat += nou_anual_brut / 12.0f;
    }

    public void pagaImpostos() {
        cobrat -= (nou_anual_brut / 12.0f)  * 0.24f;
    }

    @Override
    public void run() {
        while (edat_actual < edat_fi_treball) {
            
            try {
                if (edat_actual >= edat_inici_treball) {
                    for(int i=0; i < 12; i++){
                        cobra();
                        pagaImpostos();
                        Thread.sleep(rnd.nextInt(10));
                    }  
                }
                edat_actual++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public float getCobrat() {
        return cobrat;
    }

    public int getEdat() {
        return edat_actual;
    }
}
