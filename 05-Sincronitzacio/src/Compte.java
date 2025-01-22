public class Compte{
    private static Compte instancia;
    private float saldo;

    private Compte(){
        this.saldo = 0.0f;
    }

    public static Compte getInstancia(){
        if(instancia == null){
            instancia = new Compte();
        }
        return instancia;
    }

    public float getSaldo() { return saldo; }

    public void setSaldo(float saldo){
        this.saldo = saldo;
    }

    public void ingressar(float quantitat) {
        saldo += quantitat;
    }

    public void retirar(float quantitat){
        saldo -= quantitat;
    }
}
