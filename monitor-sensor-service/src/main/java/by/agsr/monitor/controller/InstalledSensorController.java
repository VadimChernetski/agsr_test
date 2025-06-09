package by.agsr.monitor.controller;

import by.agsr.monitor.dao.entity.Sensor;
import by.agsr.monitor.dao.interfaces.InstalledSensorService;
import by.agsr.monitor.dao.interfaces.SensorService;
import by.agsr.monitor.dto.InstalledSensorDto;
import by.agsr.monitor.dto.SensorDto;
import by.agsr.monitor.dto.WriteInstalledSensorDto;
import by.agsr.monitor.model.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/installed-sensor")
@RestController
@RequiredArgsConstructor
@Tag(name = "measurement units controller")
public class InstalledSensorController {

    private final InstalledSensorService installedSensorService;
    private final SensorService sensorService;

    @Operation(
      description = "return installed sensor"
    )
    @GetMapping("/{id}")
    @Tag(name = "Installed sensors controller")
    public InstalledSensorResponse getInstalledSensorById(@PathVariable("id") Long id) {
        InstalledSensorDto sensor = installedSensorService.getById(id);
        return InstalledSensorResponse.of(sensor);
    }

    @Operation(
      description = "return filtered installed sensor"
    )
    @GetMapping
    public InstalledSensorListResponse getSensors(@RequestParam(required = false, name = "name") String name,
                                                  @RequestParam(required = false, name = "model") String model) {
        List<InstalledSensorDto> sensors = installedSensorService.getAll(name, model);
        return InstalledSensorListResponse.of(sensors);
    }

    @Operation(
      description = "create installed sensor"
    )
    @PostMapping
    public InstalledSensorResponse createSensor(@RequestBody @Valid WriteInstalledSensorRequest request) {
        Sensor sensor = sensorService.getSensorById(request.getInstalledSensor().getSensorId());
        InstalledSensorDto installedSensor = installedSensorService.save(request.getInstalledSensor(), sensor);
        return InstalledSensorResponse.of(installedSensor);
    }

    @Operation(
      description = "update installed sensor"
    )
    @PostMapping("/{id}")
    public InstalledSensorResponse updateSensor(@PathVariable("id") Long id,
                                                @RequestBody @Valid WriteInstalledSensorRequest request) {
        Sensor sensor = sensorService.getSensorById(request.getInstalledSensor().getSensorId());
        InstalledSensorDto installedSensor = installedSensorService.update(id, request.getInstalledSensor(), sensor);
        return InstalledSensorResponse.of(installedSensor);
    }

    @Operation(
      description = "delete installed sensor"
    )
    @DeleteMapping("/{id}")
    public void deleteSensor(@PathVariable("id") Long id) {
        sensorService.delete(id);
    }


}
