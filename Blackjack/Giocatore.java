import java.util.ArrayList;
import java.util.List;

public class Giocatore {
    private String nome;
    private List<Carta> mano;
    
    public Giocatore(String nome) {
        this.nome = nome;
        this.mano = new ArrayList<>();
    }
    
    public void aggiungiCarta(Carta carta) {
        mano.add(carta);
    }
    
    public List<Carta> getMano() {
        return mano;
    }
    
    public String getNome() {
        return nome;
    }
    
    public int getPunteggio() {
        int punteggio = 0;
        int assi = 0;
        
        for (Carta carta : mano) {
            punteggio += carta.getValorePunti();
            if (carta.getValore().equals("Asso")) {
                assi++;
            }
        }
        
        // Gestione degli assi (possono valere 1 o 11)
        while (punteggio > 21 && assi > 0) {
            punteggio -= 10; // Riduci il valore di un asso da 11 a 1
            assi--;
        }
        
        return punteggio;
    }
    
    public boolean hasSballato() {
        return getPunteggio() > 21;
    }
    
    public boolean haBlackjack() {
        return mano.size() == 2 && getPunteggio() == 21;
    }
    
    public void reset() {
        mano.clear();
    }
}
