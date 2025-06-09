package by.agsr.monitor.model;

import by.agsr.monitor.exception.ExceptionCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
public class ErrorResponse<T> {

    private ExceptionCode code;
    private T message;

}
