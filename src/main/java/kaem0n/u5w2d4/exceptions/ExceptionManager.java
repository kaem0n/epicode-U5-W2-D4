package kaem0n.u5w2d4.exceptions;

import kaem0n.u5w2d4.payloads.ErrorPayload;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ExceptionManager {
    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorPayload handleBadRequest(BadRequestException e) {
        return new ErrorPayload(e.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorPayload handleNotFound(NotFoundException e) {
        return new ErrorPayload(e.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorPayload handleGenericError(Exception e) {
        e.printStackTrace();
        return new ErrorPayload("Internal server error.", LocalDateTime.now());
    }
}
