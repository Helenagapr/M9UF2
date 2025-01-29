import java.util.ArrayList;
import java.util.List;

public class Esdeveniment {
    private List<Assistent> reserves;
    private int placesDisponibles;

    public Esdeveniment(int places){
        this.reserves = new ArrayList<Assistent>();
        this.placesDisponibles = places;
    }

    public int getPlacesDisponibles(){ return placesDisponibles; }

    public synchronized void ferReserva(Assistent assistent) {
        
        while(reserves.size() >= placesDisponibles){
            try{
                wait();
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        reserves.add(assistent);
        System.out.println(assistent.getName() + " ha fet una reserva. Places disponibles: " + (placesDisponibles - reserves.size()));
        notifyAll();
    }

    public synchronized void cancelaReserva(Assistent assistent){
        if(reserves.remove(assistent)){
            System.out.println(assistent.getName() + " ha cancel·lat una reserva. Places disponibles: " + (placesDisponibles - reserves.size()));
            notifyAll();
        }else {
            System.out.println(assistent.getName() + " no ha pogut cancel·lar una reserva inexistent. Places disponibles: " + (placesDisponibles - reserves.size()));
        }
    }


}
