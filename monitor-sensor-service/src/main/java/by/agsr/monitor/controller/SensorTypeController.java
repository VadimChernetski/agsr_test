package by.agsr.monitor.controller;

import by.agsr.monitor.dao.entity.SensorType;
import by.agsr.monitor.dao.interfaces.SensorTypeService;
import by.agsr.monitor.model.DictionaryResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/sensor-type")
@RestController
@RequiredArgsConstructor
@Tag(name = "sensor types controller")
public class SensorTypeController {

    private final SensorTypeService sensorTypeService;

    @Operation(
      description = "return all sensor types"
    )
    @GetMapping
    public DictionaryResponse<SensorType> getSensorTypes() {
        List<SensorType> all = sensorTypeService.getAll();
        return DictionaryResponse.of(all);
    }
}
