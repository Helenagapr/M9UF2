public class Barri {
    public static void main(String[] args) throws InterruptedException {
        Estanc estanc = new Estanc();
        Fumador[] fumadors = new Fumador[3];
        for (int i = 0; i < 3; i++) {
            fumadors[i] = new Fumador(estanc, i);
        }
        
        Thread estancThread = new Thread(estanc::executar);
        estancThread.start();
        
        for (Fumador fumador : fumadors) {
            fumador.start();
        }

        for (Fumador fumador : fumadors) {
            fumador.join();
        }
        
        estanc.tancarEstanc();
        try {
            estancThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
