package hr.fer.ppp.ferbook.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;

    @ManyToOne
    private User user;

    @ManyToOne
    @JsonIgnore
    @JoinColumn
    private Activity activity;

    @NotNull
    private String content;

    @CreationTimestamp
    private Timestamp createdAt;
}
