package by.agsr.statistics.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class StatisticDto {

    private Long pressureSensorsNumber;

    private Long temperatureSensorsNumber;

    private Long voltageSensorsNumber;

    private Long humiditySensorsNumber;

    private LocalDateTime date;

}
