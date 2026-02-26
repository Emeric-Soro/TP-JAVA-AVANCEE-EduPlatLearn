package ci.eduplatlearn.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
@Table(name = "module")
public class Module {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titre;

    @Column(length = 2000)
    private String description;

    @Column(nullable = false)
    private Integer ordre;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    // Relation entre Cours --> Module (1-N) (propriétaire)
    @ManyToOne(optional = false)
    @JoinColumn(name = "cours_id", nullable = false)
    private Cours cours;

    // Relation entre Module --> Leçon (1-N) (inverse)
    @OneToMany(mappedBy = "module")
    private List<Lecon> lecons = new ArrayList<>();

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = this.createdAt;
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public Module() {
    }

    public Module(String titre, String description, Integer ordre) {
        this.titre = titre;
        this.description = description;
        this.ordre = ordre;
    }


    public String getTitre() {
        return titre;
    }

    public String getDescription() {
        return description;
    }

    public Integer getOrdre() {
        return ordre;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public Cours getCours() {
        return cours;
    }

    public List<Lecon> getLecons() {
        return lecons;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setOrdre(Integer ordre) {
        this.ordre = ordre;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setCours(Cours cours) {
        this.cours = cours;
    }

    public void setLecons(List<Lecon> lecons) {
        this.lecons = lecons;
    }

    public Long getId() {
            return id;
    }
}
