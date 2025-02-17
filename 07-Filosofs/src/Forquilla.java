public class Forquilla {
    private int numero;
    private boolean enUs;

    public Forquilla(int numero) {
        this.numero = numero;
        this.enUs = false;
    }

    public int getNumero() {
        return numero;
    }

    public boolean isEnUs() {
        return enUs;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setEnUs(boolean enUs) {
        this.enUs = enUs;
    }

    // Intenta coger la forquilla:
    // Si no está en uso, la marca como en uso y devuelve true; de lo contrario, devuelve false.
    public boolean agafar() {
        if (!enUs) {
            enUs = true;
            return true;
        }
        return false;
    }

    // Libera la forquilla.
    public void deixar() {
        enUs = false;
    }
}

