package rodrigues.medicament.Modele;

public class Medicament {

    private int numDepotLegal;
    private String nom;
    private String contreindic;
    private String prixEchantillon;
    private String effets;
    private int famille;

    public Medicament(int numDepotLegal, String nom, String contreindic, String prixEchantillon, String effets, int famille) {
        this.numDepotLegal = numDepotLegal;
        this.nom = nom;
        this.contreindic = contreindic;
        this.prixEchantillon = prixEchantillon;
        this.effets = effets;
        this.famille = famille;
    }
    public Medicament(String nom, String contreindic, String prixEchantillon, String effets, int famille) {
        this.numDepotLegal = -1;
        this.nom = nom;
        this.contreindic = contreindic;
        this.prixEchantillon = prixEchantillon;
        this.effets = effets;
        this.famille = famille;
    }

    public int getNumDepotLegal() {
        return numDepotLegal;
    }

    public void setNumDepotLegal(int numDepot) {
        this.numDepotLegal = numDepotLegal;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getContreindic() {
        return contreindic;
    }

    public void setContreindic(String contreindic) {
        this.contreindic = contreindic;
    }

    public String getPrixEchantillon() {
        return prixEchantillon;
    }

    public void setPrixEchantillon(String prixEchantillon) {
        this.prixEchantillon = prixEchantillon;
    }

    public String getEffets() {
        return effets;
    }

    public void setEffets(String effets) {
        this.effets = effets;
    }

    public int getFamille() {
        return famille;
    }

    public void setFamille(int famille) {
        this.famille = famille;
    }

    @Override
    public String toString() {
        return "Medicament{" +
                "numDepot=" + numDepotLegal +
                ", nom='" + nom + '\'' +
                ", contreindic='" + contreindic + '\'' +
                ", prixEchantillon=" + prixEchantillon +
                ", effets='" + effets + '\'' +
                ", famille='" + famille + '\'' +
                '}';
    }
}
