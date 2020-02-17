package hr.fer.ppp.ferbook.chat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import hr.fer.ppp.ferbook.api.model.Chat;
import hr.fer.ppp.ferbook.api.model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ChatMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;

    @CreationTimestamp
    private Timestamp sentAt;

    @NotNull
    @ManyToOne
    private User chatUser;

    @NotNull
    @ManyToOne
    @JsonIgnore
    private Chat chat;

    @NotNull
    private String content;

    public ChatMessage(User user, @NotNull Chat chat, @NotNull String content) {
        chatUser = user;
        this.chat = chat;
        this.content = content;
    }
}
