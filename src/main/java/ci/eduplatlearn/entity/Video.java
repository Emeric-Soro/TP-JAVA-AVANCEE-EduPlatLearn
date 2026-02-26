package ci.eduplatlearn.entity;

import jakarta.persistence.*;
import jakarta.websocket.ClientEndpoint;

@Entity
@Table(name = "video")
public class Video extends Ressource {
    @Column(nullable = false)
    private String url;

    @Column(nullable = false)
    private Integer dureeSecondes;

    protected Video() {
    }

    public Video(String titre, Lecon lecon, String url, Integer dureeSecondes) {
        super(titre, lecon);
        this.url = url;
        this.dureeSecondes = dureeSecondes;
    }

    public String getUrl() {
        return url;
    }

    public Integer getDureeSecondes() {
        return dureeSecondes;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setDureeSecondes(Integer dureeSecondes) {
        this.dureeSecondes = dureeSecondes;
    }
}
