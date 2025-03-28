import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //Create a constructor for new book
        Libro libro = new Libro();
        
        String[][] biblioteca = new String[3][5];  // [0]=titoli, [1]=autori, [2]=anni
        int[] quantita = new int[5];              // array monodimensionale per le quantità
        ArrayList<String> titoli = new ArrayList<>();
        
        // Inizializza la biblioteca
        inizializzaBiblioteca(biblioteca, quantita, titoli);

        // Menu principale (interattivo)
        boolean continua = true;
        while (continua) {
            System.out.println("\nBiblioteca - Menu' Principale");
            System.out.println("1. Aggiungi un libro");
            System.out.println("2. Rimuovi un libro");
            System.out.println("3. Visualizza inventario");
            System.out.println("4. Aggiorna la quantita' di un libro");
            System.out.println("5. Visualizza i libri disponibili");
            System.out.println("6. Registrazione utente");
            System.out.println("7. Esci");
            
            int scelta = scanner.nextInt();
            scanner.nextLine();
            
            switch (scelta) {
                case 1:
                    aggiungiLibro(biblioteca, quantita, titoli);
                    break;
                case 2:
                    rimuoviLibro(biblioteca, quantita, titoli);
                    break;
                case 3:
                    visualizzaInventario(biblioteca, quantita, titoli);
                    break;
                case 4:
                    aggiornaQuantita(biblioteca, quantita, titoli);
                    break;
                case 5:
                    visualizzaLibriDisponibili(biblioteca, quantita, titoli);
                    break;
                case 6:
                    continua = false;
                    break;
                case 7:
                    registrazioneUtente();
                    break;
                default:
                    System.out.println("Scelta non valida");
                    break;
            }
        }
        scanner.close();
    }

    private static void inizializzaBiblioteca(String[][] biblioteca, int[] quantita, ArrayList<String> titoli) {
        // Inizializza la biblioteca con alcuni libri di esempio
        biblioteca[0][0] = "Il Signore degli Anelli";
        biblioteca[1][0] = "J.R.R. Tolkien";
        biblioteca[2][0] = "1954";
        quantita[0] = 3;

        biblioteca[0][1] = "1984";
        biblioteca[1][1] = "George Orwell";
        biblioteca[2][1] = "1949";
        quantita[1] = 2;

        titoli.add("Il Signore degli Anelli");
        titoli.add("1984");
    }

    private static void aggiungiLibro(String[][] biblioteca, int[] quantita, ArrayList<String> titoli) {
        if (titoli.size() >= 5) {
            System.out.println("La biblioteca è piena!");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("Inserisci il titolo del libro:");
        String titolo = scanner.nextLine();
        
        if (titoli.contains(titolo)) {
            System.out.println("Questo libro esiste già!");
            return;
        }

        System.out.println("Inserisci l'autore del libro:");
        String autore = scanner.nextLine();
        System.out.println("Inserisci l'anno di pubblicazione del libro:");
        String anno = scanner.nextLine();
        System.out.println("Inserisci la quantita' del libro:");
        int qty = Integer.parseInt(scanner.nextLine());

        int indice = titoli.size();
        biblioteca[0][indice] = titolo;
        biblioteca[1][indice] = autore;
        biblioteca[2][indice] = anno;
        quantita[indice] = qty;
        titoli.add(titolo);
        
        System.out.println("Libro aggiunto con successo!");
    }

    private static void rimuoviLibro(String[][] biblioteca, int[] quantita, ArrayList<String> titoli) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Inserisci il titolo del libro da rimuovere:");
        String titolo = scanner.nextLine();
        
        int indice = titoli.indexOf(titolo);
        if (indice != -1) {
            // Sposta tutti i libri successivi di una posizione indietro
            for (int i = indice; i < titoli.size() - 1; i++) {
                biblioteca[0][i] = biblioteca[0][i + 1];
                biblioteca[1][i] = biblioteca[1][i + 1];
                biblioteca[2][i] = biblioteca[2][i + 1];
                quantita[i] = quantita[i + 1];
            }
            
            // Pulisci l'ultima posizione
            int ultimaPosizione = titoli.size() - 1;
            biblioteca[0][ultimaPosizione] = null;
            biblioteca[1][ultimaPosizione] = null;
            biblioteca[2][ultimaPosizione] = null;
            quantita[ultimaPosizione] = 0;
            
            titoli.remove(titolo);
            System.out.println("Libro rimosso con successo!");
        } else {
            System.out.println("Libro non trovato");
        }
    }

    private static void visualizzaInventario(String[][] biblioteca, int[] quantita, ArrayList<String> titoli) {
        if (titoli.isEmpty()) {
            System.out.println("La biblioteca è vuota!");
            return;
        }

        System.out.println("\nInventario della biblioteca:");
        System.out.println("-----------------------------");
        for (int i = 0; i < titoli.size(); i++) {
            System.out.println("Titolo: " + biblioteca[0][i]);
            System.out.println("Autore: " + biblioteca[1][i]);
            System.out.println("Anno di pubblicazione: " + biblioteca[2][i]);
            System.out.println("Quantità: " + quantita[i]);
            System.out.println("-----------------------------");
        }
    }

    private static void aggiornaQuantita(String[][] biblioteca, int[] quantita, ArrayList<String> titoli) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Inserisci il titolo del libro da aggiornare:");
        String titolo = scanner.nextLine();
        
        int indice = titoli.indexOf(titolo);
        if (indice != -1) {
            System.out.println("Quantità attuale: " + quantita[indice]);
            System.out.println("Inserisci la nuova quantità:");
            try {
                int nuovaQuantita = Integer.parseInt(scanner.nextLine());
                if (nuovaQuantita >= 0) {
                    quantita[indice] = nuovaQuantita;
                    System.out.println("Quantità aggiornata con successo!");
                } else {
                    System.out.println("La quantità non può essere negativa!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Inserire un numero valido!");
            }
        } else {
            System.out.println("Libro non trovato");
        }
    
    
    visualizzaLibriDisponibili(biblioteca, quantita, titoli);
    }
    //static method for view all books available
    private static void visualizzaLibriDisponibili(String[][] biblioteca, int[] quantita, ArrayList<String> titoli) {
        boolean libriDisponibili = false;
    
        System.out.println("\nLibri disponibili:");
        System.out.println("-----------------------------");
    
        for (int i = 0; i < titoli.size(); i++) {
            if (quantita[i] > 0) {
                libriDisponibili = true;
                System.out.println("Titolo: " + biblioteca[0][i]);
                System.out.println("Autore: " + biblioteca[1][i]);
                System.out.println("Anno di pubblicazione: " + biblioteca[2][i]);
                System.out.println("Quantità: " + quantita[i]);
                System.out.println("-----------------------------");
            }
        }
    
        if (!libriDisponibili) {
            System.out.println("Nessun libro disponibile al momento.");
        }
    }
    
    private static void registrazioneUtente() {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Inserisci l'id:");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.println("Inserisci il nome:");
        String nome = scanner.nextLine();
        System.out.println("Inserisci il cognome:");
        String cognome = scanner.nextLine();
        System.out.println("Inserisci l'email:");
        String email = scanner.nextLine();
        System.out.println("Inserisci la password:");
        String password = scanner.nextLine();
        
        Utente utente = new Utente(id, password, password, password, password);
        Utente.addUtente(utente);
        System.out.println("Utente registrato con successo!");
        
    }
    
    
    
    
 }
