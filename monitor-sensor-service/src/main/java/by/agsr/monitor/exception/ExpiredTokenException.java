package by.agsr.monitor.exception;

import org.springframework.http.HttpStatus;

public class ExpiredTokenException extends AgsrException {

    public ExpiredTokenException(String message) {
        super(ExceptionCode.TOKEN_EXPIRED, HttpStatus.FORBIDDEN, message);
    }
}
