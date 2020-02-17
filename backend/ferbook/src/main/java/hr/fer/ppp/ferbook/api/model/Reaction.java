package hr.fer.ppp.ferbook.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Reaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    @NotNull
    private ReactionEnum reactionText;

    @ManyToOne
    private User user;

    @ManyToOne
    @JsonIgnore
    private Activity activity;

    public Reaction(ReactionEnum reactionText, User user, Activity activity) {
        this.reactionText = reactionText;
        this.user = user;
        this.activity = activity;
    }
}
