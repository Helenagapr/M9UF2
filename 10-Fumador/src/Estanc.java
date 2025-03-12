import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Estanc {
    private List<Tabac> tabac;
    private List<Paper> paper;
    private List<Llumi> llumi;
    private boolean obert;
    private final Random random;

    public Estanc(){
        this.tabac =  new ArrayList<>();
        this.paper = new ArrayList<>();
        this.llumi = new ArrayList<>();
        this.obert = true;
        this.random = new Random();
    }

    public synchronized void nouSubministrament() {
        if (!obert) return;  
            int producte = random.nextInt(3); 
            switch (producte) {
                case 0 -> addTabac();
                case 1 -> addPaper();
                case 2 -> addLlumi();
        }
        notifyAll(); 

    }

    private synchronized void addTabac() {
        tabac.add(new Tabac());
        System.out.println("Afegint Tabac");
    }

    private synchronized void addPaper() {
        paper.add(new Paper());
        System.out.println("Afegint Paper");
    }

    private synchronized void addLlumi() {
        llumi.add(new Llumi());
        System.out.println("Afegint Llumí");
    }

    public synchronized Tabac venTabac() throws InterruptedException {
        while (obert && tabac.isEmpty()) wait();  
    
        if (!obert) return null;  
        return tabac.remove(0);  
    }
    
    public synchronized Paper venPaper() throws InterruptedException {
        while (obert && paper.isEmpty()) wait();  
    
        if (!obert) return null;  
        return paper.remove(0);  
    }
    
    public synchronized Llumi venLlumi() throws InterruptedException {
        while (obert && llumi.isEmpty()) wait();  
    
        if (!obert) return null;  
        return llumi.remove(0); 
    }
    

    public synchronized void tancarEstanc() {
        obert = false;
        notifyAll();  
    }

    public void executar() {
        System.out.println("Estanc obert");
        while (obert) {
            try {
                Thread.sleep(500 + random.nextInt(1000));
                nouSubministrament();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("Estanc tancat");
    }
}
