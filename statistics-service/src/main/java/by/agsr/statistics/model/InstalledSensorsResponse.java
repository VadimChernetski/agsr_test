package by.agsr.statistics.model;

import by.agsr.statistics.dto.InstalledSensorDto;
import lombok.Data;

import java.util.List;

@Data
public class InstalledSensorsResponse {

    private List<InstalledSensorDto> installedSensor;
}
