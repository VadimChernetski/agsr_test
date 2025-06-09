package by.agsr.monitor.model;

import by.agsr.monitor.dto.SensorDto;
import jakarta.validation.Valid;
import lombok.Data;

@Data
public class SensorRequest {

    @Valid
    private SensorDto sensor;
}
