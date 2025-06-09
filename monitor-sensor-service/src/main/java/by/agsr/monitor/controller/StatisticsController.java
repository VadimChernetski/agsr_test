package by.agsr.monitor.controller;

import by.agsr.monitor.dao.interfaces.InstalledSensorService;
import by.agsr.monitor.dto.InstalledSensorDto;
import by.agsr.monitor.model.InstalledSensorListResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/statistics")
@RestController
@RequiredArgsConstructor
@Tag(name = "statistics units controller, has specific static token (should be stored in db, but for test it's from application.yaml)")
public class StatisticsController {

    private final InstalledSensorService installedSensorService;

    @Operation(
      description = "return all installed sensors"
    )
    @GetMapping
    public InstalledSensorListResponse getAllSensors() {
        List<InstalledSensorDto> sensors = installedSensorService.getAll();
        return InstalledSensorListResponse.of(sensors);
    }
}
