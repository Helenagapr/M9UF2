public class MainDemoFil {
    public static void main(String[] args) {
        // Captura el fil actual en execució
        Thread currentThread = Thread.currentThread();
        
        // Mostrem el nom, la prioritat i el toString() del fil
        System.out.println("MainDemoFil.main:");
        System.out.println("Prioritat -> " + currentThread.getPriority());
        System.out.println("Nom -> " + currentThread.getName());
        System.out.println("toString() -> " + currentThread.toString());
    }
}
