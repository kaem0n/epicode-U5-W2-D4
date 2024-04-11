package kaem0n.u5w2d4.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "authors")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    @Column(name = "author_id")
    private long id;
    private String name;
    private String surname;
    private String email;
    private LocalDate birthday;
    private String avatar;
    @OneToMany(mappedBy = "author")
    @JsonIgnore
    private List<BlogPost> posts;

    public Author(String name, String surname, String email, LocalDate birthday) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.birthday = birthday;
    }
}
