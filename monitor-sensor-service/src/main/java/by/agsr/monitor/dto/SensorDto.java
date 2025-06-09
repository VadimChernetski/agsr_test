package by.agsr.monitor.dto;

import by.agsr.common.MeasurementUnit;
import by.agsr.common.SensorType;
import by.agsr.monitor.configuration.validator.ValidRange;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SensorDto {

    private Long id;

    @NotNull(message = "model can not be null")
    @NotEmpty(message = "model can not be empty")
    private String model;

    @Valid
    @ValidRange(message = "to can not be less then from")
    private RangeDto range;

    @NotNull(message = "sensor type can not be null")
    private SensorType sensorType;

    private MeasurementUnit measurementUnit;
}
