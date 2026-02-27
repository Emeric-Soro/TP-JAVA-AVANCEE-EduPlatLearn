package ci.eduplatlearn.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "ressource")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "type_ressource", discriminatorType = DiscriminatorType.STRING)
public abstract class Ressource {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    protected String titre;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    // Relation entre leçon <--> ressource (1-1) (propriétaire)
    @OneToOne(optional = false)
    @JoinColumn(name = "lecon_id", nullable = false, unique = true)
    private Lecon lecon;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = this.createdAt;
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    protected Ressource() {
    }

    public Ressource(String titre, Lecon lecon) {
        this.titre = titre;
        this.lecon = lecon;
    }

    public Long getId() {
        return id;
    }

    public String getTitre() {
        return titre;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public Lecon getLecon() {
        return lecon;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setLecon(Lecon lecon) {
        this.lecon = lecon;
    }
}
