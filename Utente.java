//Simple class for register user
import java.util.ArrayList;

public class Utente {
    private int id;
    private String nome;
    private String cognome;
    private String email;
    private String password;
    
    public Utente(int id, String nome, String cognome, String email, String password) {
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.password = password;
    }
    
    public int getId() { return id; }
    public String getNome() { return nome; }
    public String getCognome() { return cognome; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    
    public void setId(int id) { this.id = id; }
    public void setNome(String nome) { this.nome = nome; }
    public void setCognome(String cognome) { this.cognome = cognome; }
    public void setEmail(String email) { this.email = email; }
    public void setPassword(String password) { this.password = password; }
    
    @Override
    public String toString() {
        return "Utente [id=" + id + ", nome=" + nome + ", cognome=" + cognome + ", email=" + email + ", password=" + password + "]";
    }

    public static void main(String[] args) {
        Utente utente = new Utente(1, "Mario", "Rossi", "mario.rossi@example.com", "password");
        System.out.println("Id: " + utente.getId());
        System.out.println("Nome: " + utente.getNome());
        System.out.println("Cognome: " + utente.getCognome());
        System.out.println("Email: " + utente.getEmail());
        System.out.println("Password: " + utente.getPassword());
    }

    //ArrayList for memorize user
    private static ArrayList<Utente> utenti = new ArrayList<>();
    
    //Method for add user
    public static void addUtente(Utente utente) {
        utenti.add(utente);
    }
    
    //Method for remove user
    public static void removeUtente(Utente utente) {
        utenti.remove(utente);
    }
    
    //Method for view all user
    public static void viewAllUser() {
        for (Utente utente : utenti) {
            System.out.println(utente);
        }
    }
}
