package by.agsr.monitor.dao.impl;

import by.agsr.monitor.dao.entity.InstalledSensor;
import by.agsr.monitor.dao.entity.Sensor;
import by.agsr.monitor.dao.interfaces.InstalledSensorService;
import by.agsr.monitor.dao.repository.InstalledSensorRepository;
import by.agsr.monitor.dto.InstalledSensorDto;
import by.agsr.monitor.dto.WriteInstalledSensorDto;
import by.agsr.monitor.exception.BadRequestException;
import by.agsr.monitor.mapper.InstalledServiceToInstalledServiceDtoMapper;
import by.agsr.monitor.specification.InstalledSensorSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class InstalledSensorServiceImpl implements InstalledSensorService {

    private final InstalledSensorRepository installedSensorRepository;
    private final InstalledServiceToInstalledServiceDtoMapper installedServiceToInstalledServiceDtoMapper;


    @Override
    @Transactional(readOnly = true)
    public boolean isPresentInstalledSensorForSensor(Long sensorId) {
        return installedSensorRepository.installedSensorExistsForGivenSensorId(sensorId);
    }

    @Override
    @Transactional(readOnly = true)
    public InstalledSensorDto getById(Long sensorId) {
        return installedServiceToInstalledServiceDtoMapper.toInstalledSensorDto(findById(sensorId));
    }

    @Override
    @Transactional(readOnly = true)
    public List<InstalledSensorDto> getAll(String name, String model) {
        return installedSensorRepository.findAll(InstalledSensorSpecification.buildSpecification(name, model)).stream()
          .map(installedServiceToInstalledServiceDtoMapper::toInstalledSensorDto)
          .toList();

    }

    @Override
    @Transactional
    public List<InstalledSensorDto> getAll() {
        return installedSensorRepository.findAll().stream()
          .map(installedServiceToInstalledServiceDtoMapper::toInstalledSensorDto)
          .toList();
    }

    @Override
    @Transactional
    public InstalledSensorDto save(WriteInstalledSensorDto installedSensorDto, Sensor sensor) {
        InstalledSensor installedSensor = InstalledSensor.builder()
          .name(installedSensorDto.getName())
          .location(installedSensorDto.getLocation())
          .description(installedSensorDto.getDescription())
          .sensor(sensor)
          .build();
        return installedServiceToInstalledServiceDtoMapper
          .toInstalledSensorDto(installedSensorRepository.save(installedSensor));
    }

    @Override
    @Transactional
    public InstalledSensorDto update(Long id, WriteInstalledSensorDto installedSensorDto,Sensor sensor) {
        InstalledSensor installedSensor = findById(id);
        boolean isChanged = false;
        if (!Objects.equals(installedSensorDto.getName(), installedSensor.getName())) {
            isChanged = true;
            installedSensor.setName(installedSensorDto.getName());
        }
        if (!Objects.equals(installedSensorDto.getLocation(), installedSensor.getLocation())) {
            isChanged = true;
            installedSensor.setLocation(installedSensorDto.getLocation());
        }
        if (!Objects.equals(installedSensorDto.getDescription(), installedSensor.getDescription())) {
            isChanged = true;
            installedSensor.setDescription(installedSensorDto.getDescription());
        }
        if (!Objects.equals(installedSensorDto.getSensorId(), sensor.getId())) {
            isChanged = true;
            installedSensor.setSensor(sensor);
        }
        if (isChanged) {
            installedSensor = installedSensorRepository.save(installedSensor);
        }
        return installedServiceToInstalledServiceDtoMapper
          .toInstalledSensorDto(installedSensor);
    }

    @Override
    @Transactional
    public void delete(Long sensorId) {
        InstalledSensor installedSensor = findById(sensorId);
        installedSensorRepository.delete(installedSensor);
    }

    private InstalledSensor findById(Long id) {
        return installedSensorRepository.findById(id)
          .orElseThrow(() -> new BadRequestException(String.format("installed sensor with id=%d not found", id)));
    }
}
