package by.agsr.monitor.dao.interfaces;

import by.agsr.monitor.dao.entity.Sensor;
import by.agsr.monitor.dto.SensorDto;

import java.util.List;

public interface SensorService {

    SensorDto save(SensorDto sensorDto);

    SensorDto update(Long id, SensorDto sensorDto);

    SensorDto getById(Long id);

    Sensor getSensorById(Long id);

    List<SensorDto> getAll();

    void delete(Long id);

}
