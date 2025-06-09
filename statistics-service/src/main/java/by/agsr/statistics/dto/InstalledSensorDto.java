package by.agsr.statistics.dto;

import lombok.Data;

@Data
public class InstalledSensorDto {

    private String name;

    private String location;

    private String description;

    private SensorDto sensor;

}
