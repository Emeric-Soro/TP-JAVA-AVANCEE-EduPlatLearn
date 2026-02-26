package ci.eduplatlearn.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "enseignant")
public class Enseignant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private String prenom;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(length = 1000)
    private String bio;

    @Column(nullable = false, updatable = false)
    private String createdAt;

    @Column(nullable = false, updatable = false)
    private String updatedAt;

    // Relaition enseignant <--> cours (1-N) (propriétaire)
    @ManyToMany
    @JoinTable(
            name = "enseignant_cours",
            joinColumns = @JoinColumn(name = "enseignant_id"),
            inverseJoinColumns = @JoinColumn(name = "cours_id")
    )
    private java.util.List<Cours> cours = new java.util.ArrayList<>();

    protected Enseignant() {
    }

    public Enseignant(String prenom, String nom, String email) {
        this.prenom = prenom;
        this.nom = nom;
        this.email = email;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getEmail() {
        return email;
    }

    public String getBio() {
        return bio;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public List<Cours> getCours() {
        return cours;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setCours(List<Cours> cours) {
        this.cours = cours;
    }
}
