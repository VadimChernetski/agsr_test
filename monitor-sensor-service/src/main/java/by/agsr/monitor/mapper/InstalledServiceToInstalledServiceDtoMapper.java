package by.agsr.monitor.mapper;

import by.agsr.monitor.dao.entity.InstalledSensor;
import by.agsr.monitor.dao.entity.Sensor;
import by.agsr.monitor.dto.InstalledSensorDto;
import by.agsr.monitor.dto.SensorDto;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.Objects;

@Mapper(componentModel = "spring")
public abstract class InstalledServiceToInstalledServiceDtoMapper {

    @Mapping(target = "sensor.range.to", source = "sensor.to")
    @Mapping(target = "sensor.range.from", source = "sensor.from")
    @Mapping(target = "sensor.sensorType", ignore = true)
    @Mapping(target = "sensor.measurementUnit", ignore = true)
    public abstract InstalledSensorDto toInstalledSensorDto(InstalledSensor sensor);

    @AfterMapping
    protected void afterMapping(@MappingTarget InstalledSensorDto sensorDto, InstalledSensor sensor) {
        if (Objects.nonNull(sensor.getSensor().getSensorType())) {
            sensorDto.getSensor().setSensorType(sensor.getSensor().getSensorType().getType());
        }
        if(Objects.nonNull(sensor.getSensor().getMeasurementUnit())) {
            sensorDto.getSensor().setMeasurementUnit(sensor.getSensor().getMeasurementUnit().getUnit());
        }
    }

}
