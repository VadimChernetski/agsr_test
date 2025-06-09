package by.agsr.statistics.dao.impl;

import by.agsr.common.SensorType;
import by.agsr.statistics.DateTimeUtil;
import by.agsr.statistics.dao.entity.Statistic;
import by.agsr.statistics.dao.interfaces.StatisticService;
import by.agsr.statistics.dao.repository.StatisticRepository;
import by.agsr.statistics.dto.InstalledSensorDto;
import by.agsr.statistics.dto.StatisticDto;
import by.agsr.statistics.mapper.StatisticToStatisticDtoMapper;
import by.agsr.statistics.specification.StatisticSpecifications;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

@Service
@RequiredArgsConstructor
public class StatisticServiceImpl implements StatisticService {

    private final StatisticRepository statisticRepository;
    private final StatisticToStatisticDtoMapper statisticToStatisticDtoMapper;

    @Override
    @Transactional
    public void saveStatistic(List<InstalledSensorDto> sensors) {
         Statistic statistic = sensors.stream()
          .collect(new Collector<InstalledSensorDto, Statistic, Statistic>() {
              @Override
              public Supplier<Statistic> supplier() {
                  return () -> Statistic.builder()
                    .humiditySensorsNumber(0L)
                    .pressureSensorsNumber(0L)
                    .temperatureSensorsNumber(0L)
                    .voltageSensorsNumber(0L)
                    .date(DateTimeUtil.getDateTime())
                    .build();
              }

              @Override
              public BiConsumer<Statistic, InstalledSensorDto> accumulator() {
                  return (Statistic statistic, InstalledSensorDto sensor) -> {
                      if (Objects.equals(sensor.getSensor().getSensorType(), SensorType.HUMIDITY)) {
                          statistic.setHumiditySensorsNumber(statistic.getHumiditySensorsNumber() + 1);
                      }
                      if (Objects.equals(sensor.getSensor().getSensorType(), SensorType.PRESSURE)) {
                          statistic.setPressureSensorsNumber(statistic.getPressureSensorsNumber() + 1);
                      }
                      if (Objects.equals(sensor.getSensor().getSensorType(), SensorType.VOLTAGE)) {
                          statistic.setVoltageSensorsNumber(statistic.getVoltageSensorsNumber() + 1);
                      }
                      if (Objects.equals(sensor.getSensor().getSensorType(), SensorType.TEMPERATURE)) {
                          statistic.setTemperatureSensorsNumber(statistic.getTemperatureSensorsNumber() + 1);
                      }
                  };
              }

              @Override
              public BinaryOperator<Statistic> combiner() {
                  return (stat1, stat2) ->
                      Statistic.builder()
                        .humiditySensorsNumber(stat1.getHumiditySensorsNumber() + stat2.getHumiditySensorsNumber())
                        .pressureSensorsNumber(stat1.getPressureSensorsNumber() + stat2.getPressureSensorsNumber())
                        .temperatureSensorsNumber(stat1.getTemperatureSensorsNumber() + stat2.getTemperatureSensorsNumber())
                        .voltageSensorsNumber(stat1.getVoltageSensorsNumber() + stat2.getVoltageSensorsNumber())
                        .date(stat1.getDate()) // Используем дату из первого объекта или можно взять максимальную
                        .build();
              }

              @Override
              public Function<Statistic, Statistic> finisher() {
                  return Function.identity();
              }

              @Override
              public Set<Characteristics> characteristics() {
                  return Set.of();
              }
          });
        statisticRepository.save(statistic);
    }

    @Override
    public List<StatisticDto> getStatistic(LocalDateTime start, LocalDateTime end) {
        return statisticRepository.findAll(StatisticSpecifications.hasDateBetween(start, end)).stream()
          .map(statisticToStatisticDtoMapper::toStatisticDto)
          .toList();
    }
}
