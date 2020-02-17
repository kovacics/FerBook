package hr.fer.ppp.ferbook.api.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Friendship {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;

    @ManyToOne
    private User user;

    @ManyToOne
    private User friend;

    private Timestamp acceptedInvitationAt;

    public Friendship(User user, User friend) {
        this.user = user;
        this.friend = friend;
    }
}
