public class testLibro {
    public static void main(String[] args) {
        // Creazione di un nuovo libro con il costruttore vuoto
        Libro libro1 = new Libro();

        // Test dei metodi
        System.out.println("Stato iniziale del libro:");
        System.out.println(libro1);

        // Test del setter
        libro1.setQty(5);
        System.out.println("\nDopo aver modificato la quantità:");
        System.out.println(libro1);

        // Test dei getters
        System.out.println("\nDettagli del Libro1:");
        System.out.println("Titolo: " + libro1.getTitolo());
        System.out.println("Autore: " + libro1.getAutore());
        System.out.println("Anno: " + libro1.getAnno());
        System.out.println("Quantità: " + libro1.getQty());

        // Creazione di un nuovo libro con parametri
        Libro libro2 = new Libro();
        System.out.println("\nNuovo libro creato con parametri:");
        System.out.println(libro2);
    } 
}