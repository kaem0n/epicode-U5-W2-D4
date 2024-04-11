package kaem0n.u5w2d4.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record NewAuthorDTO(@NotEmpty(message = "'Name' field is mandatory.")
                           @Size(min = 3, max = 20, message = "'Name' field length must be between 3 and 20 characters.")
                           String name,
                           @NotEmpty(message = "'Surname' field is mandatory.")
                           @Size(min = 3, max = 20, message = "'Surname' field length must be between 3 and 20 characters.")
                           String surname,
                           @NotEmpty(message = "'Email' field is mandatory.")
                           @Email(message = "Invalid email format.")
                           String email,
                           @NotNull(message = "'Birthday' field is mandatory.")
                           LocalDate birthday) {
}
