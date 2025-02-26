public class TaulaLock {
    private final FilosofLock[] filosofos;
    private final ForquillaLock[] forquilles;

    public TaulaLock(int numFilosofs) {
        filosofos = new FilosofLock[numFilosofs];
        forquilles = new ForquillaLock[numFilosofs];

        for (int i = 0; i < numFilosofs; i++) {
            forquilles[i] = new ForquillaLock(i);
        }

        for (int i = 0; i < numFilosofs; i++) {
            filosofos[i] = new FilosofLock(i, forquilles[i], forquilles[(i + 1) % numFilosofs]);
            System.out.println("Comensal: Fil" + i + " esq:" + i + " dret:" + ((i + 1) % numFilosofs));
        }
    }

    public void showTaula() {
        System.out.println("-----------------------------");
    }

    public void cridarATaula() {
        for (FilosofLock f : filosofos) {
            f.start();
        }
    }
}
