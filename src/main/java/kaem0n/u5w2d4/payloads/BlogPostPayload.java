package kaem0n.u5w2d4.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BlogPostPayload {
    private String category;
    private String title;
    private String coverUrl;
    private String content;
    private int readingTime;
    private long authorId;
}
