package ci.eduplatlearn.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "fichier")
public class Fichier extends Ressource {

    @Column(nullable = false)
    private String nomFichier;

    @Column(nullable = false)
    private String cheminStockage;

    protected Fichier() {
    }

    public Fichier(String titre, Lecon lecon, String nomFichier, String cheminStockage) {
        super(titre, lecon);
        this.nomFichier = nomFichier;
        this.cheminStockage = cheminStockage;
    }

    public String getNomFichier() {
        return nomFichier;
    }

    public String getCheminStockage() {
        return cheminStockage;
    }

    public void setNomFichier(String nomFichier) {
        this.nomFichier = nomFichier;
    }

    public void setCheminStockage(String cheminStockage) {
        this.cheminStockage = cheminStockage;
    }
}
