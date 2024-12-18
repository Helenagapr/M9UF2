public class PartitFutbol {
    public static void main(String[] args) {
        
        String[] nomsJugadors = {
            "Piqué", "Vinicius", "Torres", "Ramos", "Ronaldo", 
            "Lewan", "Belli", "Arnau", "Aspas", "Messi", "MBapé"
        };

        Futbolista[] jugadors= new Futbolista[Futbolista.NUM_JUGADORS];
        for (int i =0; i < Futbolista.NUM_JUGADORS; i++){
            jugadors[i] = new Futbolista(nomsJugadors[i]);
        }

        System.out.println("Inici dels xuts --------------------");

        for (Futbolista jugador: jugadors){
            jugador.start();
        }

        for (Futbolista jugador: jugadors) {
            try {
                // Esperar a que els fils finalitzin
                jugador.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Fi dels xuts -----------------------");

        System.out.println("--- Estadístiques ------");

        for(Futbolista jugador: jugadors){
            System.out.println(jugador.getName() + "\t->\t" + jugador.getNgols() + " gols");
        }
    }
    
}
