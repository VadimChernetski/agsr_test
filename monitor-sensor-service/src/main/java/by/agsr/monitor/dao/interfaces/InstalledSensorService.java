package by.agsr.monitor.dao.interfaces;

import by.agsr.monitor.dao.entity.Sensor;
import by.agsr.monitor.dto.InstalledSensorDto;
import by.agsr.monitor.dto.WriteInstalledSensorDto;

import java.util.List;

public interface InstalledSensorService {

    boolean isPresentInstalledSensorForSensor(Long sensorId);

    InstalledSensorDto getById(Long sensorId);

    List<InstalledSensorDto> getAll(String name, String model);

    List<InstalledSensorDto> getAll();

    InstalledSensorDto save(WriteInstalledSensorDto installedSensorDto, Sensor sensor);

    InstalledSensorDto update(Long id, WriteInstalledSensorDto installedSensorDto,Sensor sensor);

    void delete(Long sensorId);


}
