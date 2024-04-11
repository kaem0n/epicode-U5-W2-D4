package kaem0n.u5w2d4.payloads;

import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.URL;

public record NewBlogPostDTO(@NotEmpty(message = "'Category' field is mandatory.")
                             @Size(min = 3, max = 20, message = "'Category' field character limit between 3 and 20 characters.")
                             String category,
                             @NotEmpty(message = "'Title' field is mandatory.")
                             @Size(min = 3, max = 50, message = "'Title' field character limit between 3 and 50 characters.")
                             String title,
                             @URL(message = "Invalid URL format.")
                             @NotEmpty(message = "'Cover URL' field is mandatory.")
                             String coverUrl,
                             @NotEmpty(message = "'Content' field is mandatory.")
                             String content,
                             @NotNull(message = "'Reading Time' field is mandatory.")
                             @Min(value = 1, message = "'Reading Time' field must be > 1")
                             int readingTime,
                             @NotNull(message = "'Author ID' field is mandatory.")
                             @Min(value = 1, message = "'Author ID' field must be > 1")
                             long authorId) {}
