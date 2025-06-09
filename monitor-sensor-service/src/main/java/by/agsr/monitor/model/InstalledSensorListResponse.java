package by.agsr.monitor.model;

import by.agsr.monitor.dto.InstalledSensorDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
public class InstalledSensorListResponse {

    private List<InstalledSensorDto> installedSensor;

}
