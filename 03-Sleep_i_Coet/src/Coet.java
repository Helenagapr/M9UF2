import java.util.Scanner;

public class Coet {
    private Motor[] motors = new Motor[4];

    public Coet() {
        for (int i= 0; i < 4; i++){
            motors[i] = new Motor("Motor " + i);
        }
    }

    public void passaAPotencia(int p){
        if (p < 0 || p > 10) {
            System.out.println("La potència ha d'estar entre 0 i 10.");
            return;
        }
        System.out.printf("Passant a potència %d\n", p);

        for (Motor motor: motors) motor.setPotencia(p);
    }

    public void arranca() {
        for (Motor motor: motors){
            motor.start();
        }
    }

    public static void main(String[] args) {
        Coet coet = new Coet();
        coet.arranca();

        try (Scanner scanner = new Scanner(System.in)) {
            while (true) { 
                int potencia = scanner.nextInt();
                coet.passaAPotencia(potencia);

                if (potencia == 0) break;
            }
        }
    }
}
