public class Taula {
    private Filosof[] filosofos;
    private Forquilla[] forquilles;

    public Taula(int numFilosofs) {

        filosofos = new Filosof[numFilosofs];
        forquilles = new Forquilla[numFilosofs];

        for (int i = 0; i < numFilosofs; i++) {
            forquilles[i] = new Forquilla(i);
        }

        for (int i = 0; i < numFilosofs; i++) {
            String nom = "fil" + i;
            Filosof f = new Filosof(nom, forquilles[i], forquilles[(i + 1) % numFilosofs]);
            filosofos[i] = f;
        }
    }

    public void showTaula() {
        for (int i = 0; i < filosofos.length; i++) {
            System.out.println("Comensal: " + filosofos[i].getName() +
                    " esq:" + forquilles[i].getNumero() +
                    " dret:" + forquilles[(i + 1) % forquilles.length].getNumero());
        }
        System.out.println("-------------------------------------");
    }

    public void cridarATaula() {
        for (Filosof f : filosofos) {
            f.start(); 
        }
    }
}
