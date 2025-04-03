import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class Main extends JFrame {
    private Mazzo mazzo;
    private Giocatore giocatore;
    private Giocatore banco;
    
    private JPanel panelGiocatore;
    private JPanel panelBanco;
    private JPanel panelControlli;
    private JPanel panelInfo;
    
    private JLabel labelPunteggioGiocatore;
    private JLabel labelPunteggioBanco;
    private JLabel labelStatus;
    
    private JButton buttonCarta;
    private JButton buttonStai;
    private JButton buttonNuovaPartita;
    
    private final Color BACKGROUND_COLOR = new Color(0, 100, 0); // Verde scuro
    private final Color TEXT_COLOR = Color.WHITE;
    private final Font MAIN_FONT = new Font("SansSerif", Font.BOLD, 16);
    private final Font TITLE_FONT = new Font("SansSerif", Font.BOLD, 24);
    
    public Main() {
        // Inizializzazione del gioco
        mazzo = new Mazzo();
        mazzo.mescola();
        giocatore = new Giocatore("Giocatore");
        banco = new Giocatore("Banco");
        
        // Configurazione della finestra
        setTitle("Blackjack");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        
        // Impostazione del layout principale
        setLayout(new BorderLayout());
        getContentPane().setBackground(BACKGROUND_COLOR);
        
        // Creazione dei pannelli
        createPanels();
        
        // Aggiunta dei controlli
        createControls();
        
        // Inizializzazione della partita
        nuovaPartita();
    }
    
    private void createPanels() {
        // Pannello del banco
        panelBanco = new JPanel();
        panelBanco.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panelBanco.setBackground(BACKGROUND_COLOR);
        panelBanco.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Color.WHITE), 
            "Banco", 
            javax.swing.border.TitledBorder.CENTER, 
            javax.swing.border.TitledBorder.TOP, 
            TITLE_FONT, 
            TEXT_COLOR));
        add(panelBanco, BorderLayout.NORTH);
        
        // Pannello del giocatore
        panelGiocatore = new JPanel();
        panelGiocatore.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panelGiocatore.setBackground(BACKGROUND_COLOR);
        panelGiocatore.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Color.WHITE), 
            "Giocatore", 
            javax.swing.border.TitledBorder.CENTER, 
            javax.swing.border.TitledBorder.TOP, 
            TITLE_FONT, 
            TEXT_COLOR));
        add(panelGiocatore, BorderLayout.CENTER);
        
        // Pannello informazioni
        panelInfo = new JPanel();
        panelInfo.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        panelInfo.setBackground(BACKGROUND_COLOR);
        add(panelInfo, BorderLayout.WEST);
        
        // Pannello controlli
        panelControlli = new JPanel();
        panelControlli.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        panelControlli.setBackground(BACKGROUND_COLOR);
        add(panelControlli, BorderLayout.SOUTH);
    }
    
    private void createControls() {
        // Etichette punteggi
        labelPunteggioGiocatore = new JLabel("Punteggio: 0");
        labelPunteggioGiocatore.setFont(MAIN_FONT);
        labelPunteggioGiocatore.setForeground(TEXT_COLOR);
        
        labelPunteggioBanco = new JLabel("Punteggio: 0");
        labelPunteggioBanco.setFont(MAIN_FONT);
        labelPunteggioBanco.setForeground(TEXT_COLOR);
        
        labelStatus = new JLabel("Benvenuto al Blackjack!");
        labelStatus.setFont(TITLE_FONT);
        labelStatus.setForeground(TEXT_COLOR);
        
        // Aggiunta etichette al pannello info
        panelInfo.add(labelStatus);
        panelInfo.add(new JSeparator(SwingConstants.HORIZONTAL));
        panelInfo.add(labelPunteggioGiocatore);
        panelInfo.add(labelPunteggioBanco);
        
        // Bottoni
        buttonCarta = new JButton("Carta");
        buttonCarta.setFont(MAIN_FONT);
        buttonCarta.addActionListener(e -> chiediCarta());
        
        buttonStai = new JButton("Stai");
        buttonStai.setFont(MAIN_FONT);
        buttonStai.addActionListener(e -> stai());
        
        buttonNuovaPartita = new JButton("Nuova Partita");
        buttonNuovaPartita.setFont(MAIN_FONT);
        buttonNuovaPartita.addActionListener(e -> nuovaPartita());
        
        // Aggiunta bottoni al pannello controlli
        panelControlli.add(buttonCarta);
        panelControlli.add(buttonStai);
        panelControlli.add(buttonNuovaPartita);
    }
    
    private void nuovaPartita() {
        // Reset del gioco
        giocatore.reset();
        banco.reset();
        
        // Distribuzione delle carte iniziali
        giocatore.aggiungiCarta(mazzo.pescaCarta());
        banco.aggiungiCarta(mazzo.pescaCarta());
        giocatore.aggiungiCarta(mazzo.pescaCarta());
        banco.aggiungiCarta(mazzo.pescaCarta());
        
        // Aggiornamento dell'interfaccia
        aggiornaInterfaccia();
        
        // Controllo blackjack iniziale
        if (giocatore.haBlackjack()) {
            labelStatus.setText("Blackjack! Hai vinto!");
            finePartita();
        } else {
            labelStatus.setText("Tocca a te!");
            buttonCarta.setEnabled(true);
            buttonStai.setEnabled(true);
        }
    }
    
    private void chiediCarta() {
        // Aggiunta di una carta al giocatore
        giocatore.aggiungiCarta(mazzo.pescaCarta());
        
        // Aggiornamento dell'interfaccia
        aggiornaInterfaccia();
        
        // Controllo se il giocatore ha sballato
        if (giocatore.hasSballato()) {
            labelStatus.setText("Hai sballato! Hai perso!");
            finePartita();
        }
    }
    
    private void stai() {
        // Turno del banco
        turnoBanco();
        
        // Controllo del vincitore
        determinaVincitore();
        
        // Fine della partita
        finePartita();
    }
    
    private void turnoBanco() {
        // Il banco pesca carte finché non raggiunge almeno 17
        while (banco.getPunteggio() < 17) {
            banco.aggiungiCarta(mazzo.pescaCarta());
        }
        
        // Aggiornamento dell'interfaccia
        aggiornaInterfaccia();
    }
    
    private void determinaVincitore() {
        int punteggioGiocatore = giocatore.getPunteggio();
        int punteggioBanco = banco.getPunteggio();
        
        if (banco.hasSballato()) {
            labelStatus.setText("Il banco ha sballato! Hai vinto!");
        } else if (punteggioGiocatore > punteggioBanco) {
            labelStatus.setText("Hai vinto!");
        } else if (punteggioGiocatore < punteggioBanco) {
            labelStatus.setText("Il banco vince!");
        } else {
            labelStatus.setText("Pareggio!");
        }
    }
    
    private void finePartita() {
        buttonCarta.setEnabled(false);
        buttonStai.setEnabled(false);
    }
    
    private void aggiornaInterfaccia() {
        // Aggiornamento dei punteggi
        labelPunteggioGiocatore.setText("Punteggio Giocatore: " + giocatore.getPunteggio());
        labelPunteggioBanco.setText("Punteggio Banco: " + banco.getPunteggio());
        
        // Aggiornamento delle carte visualizzate
        aggiornaPannelloCarte(panelGiocatore, giocatore.getMano());
        aggiornaPannelloCarte(panelBanco, banco.getMano());
    }
    
    private void aggiornaPannelloCarte(JPanel panel, List<Carta> carte) {
        panel.removeAll();
        
        for (Carta carta : carte) {
            JPanel cardPanel = createCardPanel(carta);
            panel.add(cardPanel);
        }
        
        panel.revalidate();
        panel.repaint();
    }
    
    private JPanel createCardPanel(Carta carta) {
        JPanel cardPanel = new JPanel();
        cardPanel.setLayout(new BorderLayout());
        cardPanel.setPreferredSize(new Dimension(100, 150));
        cardPanel.setBackground(Color.WHITE);
        cardPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        
        String seme = carta.getSeme();
        String valore = carta.getValore();
        
        // Colore del seme
        Color colore = (seme.equals("Cuori") || seme.equals("Quadri")) ? Color.RED : Color.BLACK;
        
        // Simbolo del seme
        String simbolo = "";
        switch (seme) {
            case "Cuori": simbolo = "♥"; break;
            case "Quadri": simbolo = "♦"; break;
            case "Fiori": simbolo = "♣"; break;
            case "Picche": simbolo = "♠"; break;
        }
        
        // Etichetta superiore
        JLabel labelTop = new JLabel(valore + " " + simbolo);
        labelTop.setForeground(colore);
        labelTop.setFont(new Font("SansSerif", Font.BOLD, 14));
        labelTop.setBorder(BorderFactory.createEmptyBorder(5, 5, 0, 0));
        
        // Etichetta centrale
        JLabel labelCenter = new JLabel(simbolo);
        labelCenter.setForeground(colore);
        labelCenter.setFont(new Font("SansSerif", Font.BOLD, 36));
        labelCenter.setHorizontalAlignment(JLabel.CENTER);
        
        // Etichetta inferiore
        JLabel labelBottom = new JLabel(valore + " " + simbolo);
        labelBottom.setForeground(colore);
        labelBottom.setFont(new Font("SansSerif", Font.BOLD, 14));
        labelBottom.setHorizontalAlignment(JLabel.RIGHT);
        labelBottom.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 5));
        
        cardPanel.add(labelTop, BorderLayout.NORTH);
        cardPanel.add(labelCenter, BorderLayout.CENTER);
        cardPanel.add(labelBottom, BorderLayout.SOUTH);
        
        return cardPanel;
    }
    
    public static void main(String[] args) {
        // Impostazione del look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // Avvio dell'applicazione
        SwingUtilities.invokeLater(() -> {
            Main game = new Main();
            game.setVisible(true);
        });
    }
}
