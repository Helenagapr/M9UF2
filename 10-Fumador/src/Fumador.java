import java.util.Random;

public class Fumador extends Thread {
    private final Estanc estanc;
    private final int id;
    private Tabac tabac;
    private Llumi llumi;
    private Paper paper;
    private int fumades = 0;

    public Fumador(Estanc estanc, int id) {
        this.estanc = estanc;
        this.id = id;
    }

    @Override
    public void run() {
        while (fumades < 3) {
            synchronized (estanc) {  
                try {
                    compraTabac();
                    compraPaper();
                    compraLlumi();
                }catch(InterruptedException e){

                }

                if (tabac != null && paper != null && llumi != null) {
                    fuma();
                } else {
                    try {
                        estanc.wait();  
                    } catch (InterruptedException e) {
                        return;
                    }
                }
            }
        }
    }
    private void compraTabac() throws InterruptedException {
        if (tabac == null) {
            tabac = estanc.venTabac();
            if (tabac != null) System.out.println("Fumador " + id + " comprant Tabac");
        }
    }

    private void compraPaper() throws InterruptedException {
        if(paper == null) {
            paper = estanc.venPaper();
            if (paper != null) System.out.println("Fumador " + id + " comprant Paper");
        }
    }

    private void compraLlumi() throws InterruptedException {
        if(llumi == null) {
            llumi = estanc.venLlumi();
            if(llumi != null) System.out.println("Fumador " + id + " comprant Llumí");
        }
    }

    private void fuma() {
        try {
            System.out.println("Fumador " + id + " fumant");
            Thread.sleep(500 + new Random().nextInt(500));  
            fumades++;
            System.out.println("Fumador " + id + " ha fumat " + fumades + " vegades");

            tabac = null;
            paper = null;
            llumi = null;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
