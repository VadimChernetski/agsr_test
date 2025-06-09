package by.agsr.monitor.controller;

import by.agsr.monitor.exception.AgsrException;
import by.agsr.monitor.exception.ExceptionCode;
import by.agsr.monitor.model.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(AgsrException.class)
    public ResponseEntity<ErrorResponse<String>> handleAgsrException(AgsrException exception) {
        return ResponseEntity
          .status(exception.getHttpStatus())
          .body(ErrorResponse.of(exception.getCode(), exception.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse<Map<String, List<String>>>> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        Map<String, List<String>> errors = exception.getBindingResult().getFieldErrors().stream()
          .collect(Collectors.toMap(
            FieldError::getField,
            error -> new ArrayList<>(List.of(Objects.isNull(error.getDefaultMessage()) ?
              "incorrect" : error.getDefaultMessage())),
            (list1, list2) -> {
                list1.addAll(list2);
                return list1;
            }
          ));
        return ResponseEntity
          .badRequest()
          .body(ErrorResponse.of(ExceptionCode.VALIDATION_FAILED, errors));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse<String>> handleException(Exception exception) {
        return ResponseEntity.badRequest()
          .body(ErrorResponse.of(ExceptionCode.BAD_REQUEST, "oops, something went wrong"));
    }
}
