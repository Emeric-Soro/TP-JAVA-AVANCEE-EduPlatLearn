package ci.eduplatlearn.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "lecon")
public class Lecon {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titre;

    @Column(length = 2000)
    private String resume;

    @Column(nullable = false)
    private Integer ordre;

    @Column(nullable = false)
    private Integer dureeMinutes;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    // Relation entre Module --> Leçon (1-N) (propriétaire)
    @ManyToOne(optional = false)
    @JoinColumn(name = "module_id", nullable = false)
    private Module module;

    // Relation entre leçon <--> ressource (1-1) (inverse)
    @OneToOne(mappedBy = "lecon")
    private Ressource ressource;

    protected Lecon() {
    }

    public Lecon(String titre, String resume, Integer ordre, Integer dureeMinutes) {
        this.titre = titre;
        this.resume = resume;
        this.ordre = ordre;
        this.dureeMinutes = dureeMinutes;
    }


    public String getTitre() {
        return titre;
    }

    public String getResume() {
        return resume;
    }

    public Integer getOrdre() {
        return ordre;
    }

    public Integer getDureeMinutes() {
        return dureeMinutes;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public Module getModule() {
        return module;
    }

    public Ressource getRessource() {
        return ressource;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public void setOrdre(Integer ordre) {
        this.ordre = ordre;
    }

    public void setDureeMinutes(Integer dureeMinutes) {
        this.dureeMinutes = dureeMinutes;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setModule(Module module) {
        this.module = module;
    }

    public void setRessource(Ressource ressource) {
        this.ressource = ressource;
    }
}
