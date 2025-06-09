package by.agsr.monitor.model;

import by.agsr.monitor.dto.SensorDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
public class SensorResponse {

    private SensorDto sensor;

}
