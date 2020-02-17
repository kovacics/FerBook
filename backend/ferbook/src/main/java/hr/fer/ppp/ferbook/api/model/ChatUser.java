package hr.fer.ppp.ferbook.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ChatUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;

    @ManyToOne
    @JsonIgnore
    private Chat chat;

    @ManyToOne
    private User user;

    @CreationTimestamp
    private Timestamp joinedAt;

    public ChatUser(Chat chat, User user) {
        this.chat = chat;
        this.user = user;
    }
}
