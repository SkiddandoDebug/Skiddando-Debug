import java.util.Scanner;

public class Libro {
    // Dichiarazione dei campi della classe
    private String titolo;
    private String autore;
    private String anno;
    private int qty;

    // Costruttore
    public Libro() {
        this.titolo = titolo;
        this.autore = autore;
        this.anno = anno;
        this.qty = qty;
    }
    
    public String getTitolo() { return titolo; }
    public String getAutore() { return autore; }
    public String getAnno() { return anno; }
    public int getQty() { return qty; }
    
    public void setTitolo(String titolo) { this.titolo = titolo; }
    public void setAutore(String autore) { this.autore = autore; }
    public void setAnno(String anno) { this.anno = anno; }
    public void setQty(int qty) { this.qty = qty; }
    
    @Override
    public String toString() {
        return "Titolo: " + titolo + "\n" +
               "Autore: " + autore + "\n" +
               "Anno: " + anno + "\n" +
               "Quantità: " + qty;
    }
    

    
    }
