package by.agsr.monitor.exception;

import org.springframework.http.HttpStatus;

public class BadCredentialException extends AgsrException {

    public BadCredentialException(String message) {
        super(ExceptionCode.BAD_CREDENTIALS, HttpStatus.UNAUTHORIZED, message);
    }
}
