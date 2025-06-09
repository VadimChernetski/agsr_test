package by.agsr.monitor.exception;

import org.springframework.http.HttpStatus;

public class BadRequestException extends AgsrException {

    public BadRequestException(String message) {
        super(ExceptionCode.BAD_REQUEST,  HttpStatus.BAD_REQUEST, message);
    }

}
