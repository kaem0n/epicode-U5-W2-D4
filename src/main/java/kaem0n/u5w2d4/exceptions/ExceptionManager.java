package kaem0n.u5w2d4.exceptions;

import kaem0n.u5w2d4.payloads.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ExceptionManager {
    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponseDTO handleBadRequest(BadRequestException e) {
        if (e.getErrorList() != null) {
            String msg = e.getErrorList().stream().map(err -> err.getDefaultMessage())
                    .collect(Collectors.joining(" "));
            return new ErrorResponseDTO(msg, LocalDateTime.now());
        }
        else return new ErrorResponseDTO(e.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponseDTO handleNotFound(NotFoundException e) {
        return new ErrorResponseDTO(e.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponseDTO handleGenericError(Exception e) {
        e.printStackTrace();
        return new ErrorResponseDTO("Internal server error.", LocalDateTime.now());
    }
}
