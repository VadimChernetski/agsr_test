package by.agsr.monitor.model;

import by.agsr.monitor.dto.InstalledSensorDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
public class InstalledSensorResponse {

    private InstalledSensorDto installedSensor;

}
