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
public class Picture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;

    @ManyToOne
    @JsonIgnore
    private User user;

    @Column(unique = true)
    @NotNull
    private String pictureUrl;

    private String description;

    @ManyToOne
    @JsonIgnore
    private Album album;

    @ManyToOne
    @JoinColumn
    @JsonIgnore
    private Activity activity;

    @CreationTimestamp
    private Timestamp createdAt;
}
