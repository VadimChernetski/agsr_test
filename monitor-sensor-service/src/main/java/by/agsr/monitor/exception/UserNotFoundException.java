package by.agsr.monitor.exception;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends AgsrException {

    public UserNotFoundException(String message) {
        super(ExceptionCode.USER_NOT_FOUND, HttpStatus.NOT_FOUND, message);
    }
}
