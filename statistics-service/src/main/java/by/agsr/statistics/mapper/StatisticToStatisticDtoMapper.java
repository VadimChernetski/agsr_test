package by.agsr.statistics.mapper;

import by.agsr.statistics.dao.entity.Statistic;
import by.agsr.statistics.dto.StatisticDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StatisticToStatisticDtoMapper {

    StatisticDto toStatisticDto(Statistic statistic);

}
