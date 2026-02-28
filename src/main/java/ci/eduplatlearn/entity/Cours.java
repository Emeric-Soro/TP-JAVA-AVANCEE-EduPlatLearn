package ci.eduplatlearn.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cours")
public class Cours {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titre;

    @Column(length = 2000)
    private String description;

    @Column(nullable = false)
    private String niveau;

    @Column(nullable = false)
    private Boolean publie = false;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    // Relation entre Cours --> Module (1-N) (inverse)
    @OneToMany(mappedBy = "cours")
    private List<Module> modules = new ArrayList<>();

    // Relation enseignant <--> cours (1-N) (inverse)
    @ManyToMany(mappedBy = "cours")
    private List<Enseignant> enseignants = new ArrayList<>();


    public Cours() {
    }

    public Cours(String titre, String description, String niveau) {
        this.titre = titre;
        this.description = description;
        this.niveau = niveau;
    }

    public Cours(Long id, String titre, String description, String niveau, Boolean publie) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.niveau = niveau;
        this.publie = publie;

    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = this.createdAt;
        if (this.publie == null) {
            this.publie = false;
        }
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public String getTitre() {
        return titre;
    }

    public String getDescription() {
        return description;
    }

    public String getNiveau() {
        return niveau;
    }

    public Boolean getPublie() {
        return publie;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public List<Module> getModules() {
        return modules;
    }

    public List<Enseignant> getEnseignants() {
        return enseignants;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }

    public void setPublie(Boolean publie) {
        this.publie = publie;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setModules(List<Module> modules) {
        this.modules = modules;
    }
//com
    public void setEnseignants(List<Enseignant> enseignants) {
        this.enseignants = enseignants;
    }
}
