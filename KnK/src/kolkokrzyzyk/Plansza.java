
package kolkokrzyzyk;
import java.util.ArrayList;
import java.util.List;

public class Plansza {
    
    public List<ArrayList> plansza;
    public Plansza(){
        plansza = new ArrayList<>();
        plansza.add(new ArrayList(3));
        plansza.add(new ArrayList(3));
        plansza.add(new ArrayList(3));
        
        for(int i = 0; i<3;i++)
            for(int j = 0; j<3;j++)
                plansza.get(i).add(' ');
       
        
        
    }
    
}
