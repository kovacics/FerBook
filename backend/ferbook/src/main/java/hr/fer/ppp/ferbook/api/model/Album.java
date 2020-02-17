package hr.fer.ppp.ferbook.api.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;

    @ManyToOne
    private User user;

    private String description;

    private String albumName;

    @OneToMany(mappedBy = "album", cascade = CascadeType.ALL)
    private List<Picture> pictures;

    public Album(User user, String description, String albumName, List<Picture> pictures) {
        this.user = user;
        this.description = description;
        this.albumName = albumName;
        this.pictures = pictures;
    }
}












