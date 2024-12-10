public class Principal {
    public static void main(String[] args) {
        System.out.println("Termina thread main");

        // Crear fils
        Fil juan = new Fil("Juan");
        Fil pepe = new Fil("Pepe");

        // Iniciar els fils
        juan.start();
        pepe.start();

        try {
            // Esperar a que els fils finalitzin
            juan.join();
            pepe.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
