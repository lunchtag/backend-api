package nl.lunchtag.resource.Lunchtag.logic.Exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public abstract class BaseException extends RuntimeException {
    private final HttpStatus httpStatus;
    private final String errorMessage;


    protected BaseException(HttpStatus httpStatus, String errorMessage) {
        this.httpStatus = httpStatus;
        this.errorMessage = errorMessage;
    }
}
