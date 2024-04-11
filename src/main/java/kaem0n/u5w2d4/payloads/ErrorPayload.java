package kaem0n.u5w2d4.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ErrorPayload {
    private String msg;
    private LocalDateTime timestamp;
}
