package by.agsr.monitor.dao.impl;

import by.agsr.monitor.dao.entity.MeasurementUnit;
import by.agsr.monitor.dao.entity.Sensor;
import by.agsr.monitor.dao.entity.SensorType;
import by.agsr.monitor.dao.interfaces.InstalledSensorService;
import by.agsr.monitor.dao.interfaces.MeasurementUnitService;
import by.agsr.monitor.dao.interfaces.SensorService;
import by.agsr.monitor.dao.interfaces.SensorTypeService;
import by.agsr.monitor.dao.repository.SensorRepository;
import by.agsr.monitor.dto.SensorDto;
import by.agsr.monitor.exception.BadRequestException;
import by.agsr.monitor.mapper.ServiceToServiceDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SensorServiceImpl implements SensorService {

    private final InstalledSensorService installedSensorService;
    private final MeasurementUnitService measurementUnitService;
    private final SensorTypeService sensorTypeService;
    private final SensorRepository sensorRepository;
    private final ServiceToServiceDtoMapper serviceToServiceDtoMapper;

    @Override
    @Transactional
    public SensorDto save(SensorDto sensorDto) {
        MeasurementUnit unit = measurementUnitService.getByUnit(sensorDto.getMeasurementUnit());
        SensorType type = sensorTypeService.getByType(sensorDto.getSensorType());
        Sensor sensor = Sensor.builder()
          .sensorType(type)
          .measurementUnit(unit)
          .model(sensorDto.getModel())
          .to(sensorDto.getRange().getTo())
          .from(sensorDto.getRange().getFrom())
          .build();
        return serviceToServiceDtoMapper.toSensorDto(sensorRepository.save(sensor));
    }

    @Override
    @Transactional
    public SensorDto update(Long id, SensorDto sensorDto) {
        Sensor sensor = getSensorById(id);
        boolean presentInstalledSensorForSensor = installedSensorService.isPresentInstalledSensorForSensor(id);
        if (presentInstalledSensorForSensor) {
            throw new BadRequestException(String.format("Sensor with id=%d already in use", id));
        }
        boolean isChanged = false;
        if (!Objects.equals(sensor.getSensorType().getType(), sensorDto.getSensorType())) {
            isChanged = true;
            SensorType type = sensorTypeService.getByType(sensorDto.getSensorType());
            sensor.setSensorType(type);
        }
        if (!Objects.equals(sensor.getMeasurementUnit().getUnit(), sensorDto.getMeasurementUnit())) {
            isChanged = true;
            MeasurementUnit unit = measurementUnitService.getByUnit(sensorDto.getMeasurementUnit());
            sensor.setMeasurementUnit(unit);
        }
        if (!Objects.equals(sensor.getModel(), sensorDto.getModel())) {
            isChanged = true;
            sensor.setModel(sensorDto.getModel());
        }
        if (!Objects.equals(sensor.getFrom(), sensorDto.getRange().getFrom())) {
            isChanged = true;
            sensor.setFrom(sensorDto.getRange().getFrom());
        }

        if (!Objects.equals(sensor.getTo(), sensorDto.getRange().getTo())) {
            isChanged = true;
            sensor.setTo(sensorDto.getRange().getTo());
        }
        if (isChanged) {
            sensor = sensorRepository.save(sensor);
        }
        return serviceToServiceDtoMapper.toSensorDto(sensor);
    }

    @Override
    @Transactional(readOnly = true)
    public SensorDto getById(Long id) {
        Sensor sensor = getSensorById(id);
        return serviceToServiceDtoMapper.toSensorDto(sensor);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SensorDto> getAll() {
        return sensorRepository.findAll().stream()
          .map(serviceToServiceDtoMapper::toSensorDto)
          .toList();
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Sensor sensor = getSensorById(id);
        sensorRepository.delete(sensor);
    }

    @Override
    @Transactional(readOnly = true)
    public Sensor getSensorById(Long id) {
        return sensorRepository.findById(id)
          .orElseThrow(() -> new BadRequestException(String.format("Sensor with id=%d not found", id)));
    }
}
