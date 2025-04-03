import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Mazzo {
    private List<Carta> carte;
    
    public Mazzo() {
        carte = new ArrayList<>();
        inizializzaMazzo();
    }
    
    private void inizializzaMazzo() {
        String[] semi = {"Cuori", "Quadri", "Fiori", "Picche"};
        String[] valori = {"Asso", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Regina", "Re"};
        int[] punti = {11, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10};
        
        for (String seme : semi) {
            for (int i = 0; i < valori.length; i++) {
                carte.add(new Carta(seme, valori[i], punti[i]));
            }
        }
    }
    
    public void mescola() {
        Collections.shuffle(carte);
    }
    
    public Carta pescaCarta() {
        if (carte.isEmpty()) {
            inizializzaMazzo();
            mescola();
        }
        return carte.remove(0);
    }
    
    public int carteRimanenti() {
        return carte.size();
    }
}