public class Taula1 {
    private final Filosof1[] filosofos;
    private final Forquilla1[] forquilles;

    public Taula1(int numFilosofs) {
        filosofos = new Filosof1[numFilosofs];
        forquilles = new Forquilla1[numFilosofs];

        for (int i = 0; i < numFilosofs; i++) {
            forquilles[i] = new Forquilla1(i);
        }

        for (int i = 0; i < numFilosofs; i++) {
            filosofos[i] = new Filosof1(i, forquilles[i], forquilles[(i + 1) % numFilosofs]);
            System.out.println("Comensal: fil" + i + " esq:" + i + " dret:" + ((i + 1) % numFilosofs));
        }
    }

    public void cridarATaula() {
        for (Filosof1 f : filosofos) {
            f.start();
        }
    }
}
