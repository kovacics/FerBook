package hr.fer.ppp.ferbook.api.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;

    @ManyToOne
    private User user;

    @NotNull
    private String context;

    @ManyToOne
    private User receiver;

    @CreationTimestamp
    private Timestamp createdAt;

    @OneToMany(mappedBy = "activity")
    private List<Reaction> reactions;

    @OneToMany(mappedBy = "activity")
    @OrderBy("createdAt DESC ")
    private List<Comment> comments;

    @OneToMany(mappedBy = "activity")
    private List<Picture> pictures;

    public Activity(User user, String context, User receiver, List<Picture> pictures) {
        this.user = user;
        this.context = context;
        this.receiver = receiver;
        this.pictures = pictures;
    }
}












