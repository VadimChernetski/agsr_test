package by.agsr.monitor.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class WriteInstalledSensorDto {

    @NotNull(message = "name can not be null")
    @NotEmpty(message = "name can not be empty")
    @Size(max = 30, min = 3, message = "name must be greater than or equal to 3 and less than or equal to 30")
    private String name;
    @Size(max = 40, message = "location must be less than or equal to 40")
    private String location;

    @Size(max = 40, message = "location must be less than or equal to 200")
    private String description;

    @NotNull
    private Long sensorId;

}
