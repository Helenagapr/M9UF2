import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ForquillaLock {
    private final int numero;
    private final Lock bloqueig = new ReentrantLock();

    public ForquillaLock(int numero) {
        this.numero = numero;
    }

    public int getNumero() {
        return numero;
    }

    public Lock getBloqueig() { return bloqueig; }

    public void agafar() {
        bloqueig.lock();
    }

    public void deixar() {
        bloqueig.unlock();
    }

}

