package by.agsr.monitor.dao.interfaces;

import by.agsr.monitor.dao.entity.MeasurementUnit;
import by.agsr.monitor.dao.entity.SensorType;

import java.util.List;

public interface SensorTypeService {

    List<SensorType> getAll();

    SensorType getByType(by.agsr.common.SensorType type);

}
