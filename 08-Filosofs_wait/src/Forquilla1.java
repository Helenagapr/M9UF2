public class Forquilla1 {
    private final int numero;
    private int propietari;
    public static final int LLIURE = -1;

    public Forquilla1(int numero) {
        this.numero = numero;
        this.propietari = LLIURE;
    }

    public synchronized void agafar(int filosof) throws InterruptedException {
        while (propietari != LLIURE) {
            wait();
        }
        propietari = filosof;
        System.out.println("Fil\u00f2sof: fil" + filosof + " agafa la forquilla esquerra " + numero);
    }

    public synchronized void deixar(int filosof) {
        if (propietari == filosof) {
            propietari = LLIURE;
            System.out.println("Fil\u00f2sof: fil" + filosof + " deixa l'esquerra (" + numero + ") i espera (dreta ocupada)");
            notifyAll();
        }
    }

    public int getNumero() {
        return numero;
    }

    public int getPropietari(){ return propietari; }
}

