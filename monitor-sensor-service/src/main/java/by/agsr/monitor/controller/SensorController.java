package by.agsr.monitor.controller;

import by.agsr.monitor.dao.interfaces.SensorService;
import by.agsr.monitor.dto.SensorDto;
import by.agsr.monitor.model.SensorListResponse;
import by.agsr.monitor.model.SensorRequest;
import by.agsr.monitor.model.SensorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/sensor")
@RestController
@RequiredArgsConstructor
@Tag(name = "sensor controller")
public class SensorController {

    private final SensorService sensorService;

    @Operation(
      description = "return sensor"
    )
    @GetMapping("/{id}")
    public SensorResponse getSensorById(@PathVariable("id") Long id) {
        SensorDto sensor = sensorService.getById(id);
        return SensorResponse.of(sensor);
    }

    @Operation(
      description = "return all sensors"
    )
    @GetMapping
    public SensorListResponse getSensors() {
        List<SensorDto> sensors = sensorService.getAll();
        return SensorListResponse.of(sensors);
    }

    @Operation(
      description = "create sensor"
    )
    @PostMapping
    public SensorResponse createSensor(@RequestBody @Valid SensorRequest request) {
        SensorDto sensor = sensorService.save(request.getSensor());
        return SensorResponse.of(sensor);
    }

    @Operation(
      description = "update sensor"
    )
    @PostMapping("/{id}")
    public SensorResponse updateSensor(@PathVariable("id") Long id, @RequestBody @Valid SensorRequest request) {
        SensorDto sensor = sensorService.update(id, request.getSensor());
        return SensorResponse.of(sensor);
    }

    @Operation(
      description = "delete sensor"
    )
    @DeleteMapping("/{id}")
    public void deleteSensor(@PathVariable("id") Long id) {
        sensorService.delete(id);
    }


}
