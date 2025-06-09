package by.agsr.monitor.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public abstract class AgsrException extends RuntimeException {

    private final ExceptionCode code;

    private final HttpStatus httpStatus;

    AgsrException(ExceptionCode code, HttpStatus status, String message) {
        super(message);

        this.code = code;
        this.httpStatus = status;
    }

}
