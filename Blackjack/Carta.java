public class Carta {
    private String seme;
    private String valore;
    private int valorePunti;

    public Carta(String seme, String valore, int valorePunti) {
        this.seme = seme;
        this.valore = valore;
        this.valorePunti = valorePunti;
    }

    public String getSeme() {
        return seme;
    }

    public String getValore() {
        return valore;
    }

    public int getValorePunti() {
        return valorePunti;
    }

    @Override
    public String toString() {
        return valore + " di " + seme;
    }
}