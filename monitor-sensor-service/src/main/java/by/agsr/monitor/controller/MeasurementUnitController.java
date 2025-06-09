package by.agsr.monitor.controller;

import by.agsr.monitor.dao.entity.MeasurementUnit;
import by.agsr.monitor.dao.interfaces.MeasurementUnitService;
import by.agsr.monitor.model.DictionaryResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/measurement-unit")
@RestController
@RequiredArgsConstructor
@Tag(name = "measurement units controller")
public class MeasurementUnitController {

    private final MeasurementUnitService measurementUnitService;

    @Operation(
      description = "return all measurement units"
    )
    @GetMapping
    public DictionaryResponse<MeasurementUnit> getMeasurementUnits() {
        List<MeasurementUnit> all = measurementUnitService.getAll();
        return DictionaryResponse.of(all);
    }
}
