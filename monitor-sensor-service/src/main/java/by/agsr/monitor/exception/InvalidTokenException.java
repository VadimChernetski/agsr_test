package by.agsr.monitor.exception;

import org.springframework.http.HttpStatus;

public class InvalidTokenException extends AgsrException {

    public InvalidTokenException(String message) {
        super(ExceptionCode.TOKEN_INVALID, HttpStatus.FORBIDDEN, message);
    }
}
