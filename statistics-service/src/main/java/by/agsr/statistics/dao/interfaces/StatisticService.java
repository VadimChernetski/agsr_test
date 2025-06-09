package by.agsr.statistics.dao.interfaces;

import by.agsr.statistics.dto.InstalledSensorDto;
import by.agsr.statistics.dto.StatisticDto;

import java.time.LocalDateTime;
import java.util.List;

public interface StatisticService {

    void saveStatistic(List<InstalledSensorDto> sensors);

    List<StatisticDto> getStatistic(LocalDateTime start, LocalDateTime end);
}
