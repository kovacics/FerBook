package hr.fer.ppp.ferbook.api.model;

import hr.fer.ppp.ferbook.chat.ChatMessage;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;

    @CreationTimestamp
    private Timestamp createdAt;

    private String name;

    private String pictureURL;

    @OneToMany(mappedBy = "chat")
    private List<ChatUser> chatUsers;

    @OneToMany(mappedBy = "chat")
    private List<ChatMessage> chatMessages;

    public Chat(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Chat chat = (Chat) o;
        return ID == chat.ID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID);
    }
}
