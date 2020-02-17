package hr.fer.ppp.ferbook.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Table(name = "person")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;

    @Column(unique = true)
    @NotNull
    private String username;

    @NotNull
    private String password;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @Column(unique = true)
    @NotNull
    private String email;

    private Date dateOfBirth;
    private String pictureUrl;
    private boolean admin;

    @Column(unique = true)
    private String confirmationToken;
    private Timestamp confirmedAt;

    @CreationTimestamp
    private Timestamp createdAt;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Comment> comments;


    public User(String username, String password, String firstName, String lastName, String email, String confirmationToken) {
        this.username = username;
        setPassword(password);
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.confirmationToken = confirmationToken;
    }

    public void setPassword(String password) {
        this.password = new BCryptPasswordEncoder().encode(password);
    }
}

