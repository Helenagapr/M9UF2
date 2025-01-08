import java.util.Scanner;

public class Coet {
    private Motor[] motors = new Motor[4];

    public Coet() {
        for (int i= 0; i < 4; i++){
            motors[i] = new Motor("" + i);
        }
    }

    public void passaAPotencia(int p){
        System.out.println("Passant a potència " + p);
        if(p >= 0){
            for (Motor motor: motors){
                motor.setPotencia(p);
            }
        }
    }

    public void arranca() {
        for (Motor motor: motors){
            motor.start();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Coet coet = new Coet();
        coet.arranca();

        while (true) {
            System.out.print("Introdueix la potència objectiu: ");
            int potencia = scanner.nextInt();

            if (potencia == 0) {
                System.out.println("Passant a potència 0");
                coet.passaAPotencia(potencia);
                break; // Aturem el procés un cop arriba a 0
            } else {
                coet.passaAPotencia(potencia);
            }
        }

        scanner.close();
        System.out.println("-- Fi de main -----------");
    }
}
