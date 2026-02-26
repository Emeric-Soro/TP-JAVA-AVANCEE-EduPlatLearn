package ci.eduplatlearn.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "texte")
public class Texte extends Ressource {
    @Lob
    @Column(nullable = false)
    private String contenu;

    protected Texte() {
    }

    public Texte(String titre, Lecon lecon, String contenu) {
        super(titre,lecon);
        this.contenu = contenu;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }
}
