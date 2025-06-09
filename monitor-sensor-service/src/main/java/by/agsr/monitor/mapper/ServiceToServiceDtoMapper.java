package by.agsr.monitor.mapper;

import by.agsr.monitor.dao.entity.Sensor;
import by.agsr.monitor.dto.SensorDto;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.Objects;

@Mapper(componentModel = "spring")
public interface ServiceToServiceDtoMapper {

    @Mapping(target = "range.to", source = "to")
    @Mapping(target = "range.from", source = "from")
    @Mapping(target = "sensorType", ignore = true)
    @Mapping(target = "measurementUnit", ignore = true)
    SensorDto toSensorDto(Sensor sensor);

    @AfterMapping
    default void afterMapping(@MappingTarget SensorDto sensorDto, Sensor sensor) {
        if (Objects.nonNull(sensor.getSensorType())) {
            sensorDto.setSensorType(sensor.getSensorType().getType());
        }
        if(Objects.nonNull(sensor.getMeasurementUnit())) {
            sensorDto.setMeasurementUnit(sensor.getMeasurementUnit().getUnit());
        }
    }

}
