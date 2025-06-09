package by.agsr.monitor.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RangeDto {

    private Short from;

    @NotNull(message = "the upper bound cannot be null")
    private Short to;

}
