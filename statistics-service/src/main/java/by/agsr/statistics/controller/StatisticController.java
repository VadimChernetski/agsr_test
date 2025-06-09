package by.agsr.statistics.controller;

import by.agsr.statistics.dao.interfaces.StatisticService;
import by.agsr.statistics.dto.StatisticDto;
import by.agsr.statistics.model.StatisticResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RequestMapping("/statistic")
@RestController
@RequiredArgsConstructor
@Tag(name = "refresh token controller")
public class StatisticController {

    private final StatisticService statisticService;

    @Operation(
      description = "return statistics filtered by date, date should be in format 2025-06-12T03:41:38+00:00"
    )
    @GetMapping
    public StatisticResponse getStatistic(@RequestParam(required = false, name = "start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime start,
                                          @RequestParam(required = false, name = "end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime end) {
        List<StatisticDto> statistics = statisticService.getStatistic(start, end);
        return StatisticResponse.of(statistics);
    }
}
