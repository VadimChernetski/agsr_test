package by.agsr.statistics.dto;

import by.agsr.common.MeasurementUnit;
import by.agsr.common.SensorType;
import lombok.Data;

@Data
public class SensorDto {

    private String model;

    private RangeDto range;

    private SensorType sensorType;

    private MeasurementUnit measurementUnit;

}
