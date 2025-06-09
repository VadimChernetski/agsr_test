package by.agsr.monitor.model;

import by.agsr.monitor.dto.WriteInstalledSensorDto;
import jakarta.validation.Valid;
import lombok.Data;

@Data
public class WriteInstalledSensorRequest {

    @Valid
    private WriteInstalledSensorDto installedSensor;

}
