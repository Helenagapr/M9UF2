public class Organitzador {
    public static void main(String[] args) {
        
        Esdeveniment esdeveniment = new Esdeveniment(5);

        Assistent[] assistents = new Assistent[10];

        for(int i=0; i < assistents.length; i++){
            assistents[i] = new Assistent("Assistent-"+i, esdeveniment);
        }

        for (Assistent assistent: assistents){
            assistent.start();
        }
    }
}
