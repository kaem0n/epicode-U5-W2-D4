package kaem0n.u5w2d4.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "blogposts")
public class BlogPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    @Column(name = "blogpost_id")
    private long id;
    private String category;
    private String title;
    @Column(name = "cover_url")
    private String coverUrl;
    private String content;
    @Column(name = "reading_time")
    private int readingTime;
    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    public BlogPost(String category, String title, String content, String coverUrl, int readingTime, Author author) {
        this.category = category;
        this.title = title;
        this.content = content;
        this.coverUrl = coverUrl;
        this.readingTime = readingTime;
        this.author = author;
    }
}
