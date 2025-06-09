package by.agsr.monitor.dao.interfaces;

import by.agsr.monitor.dao.entity.MeasurementUnit;

import java.util.List;

public interface MeasurementUnitService {

    List<MeasurementUnit> getAll();

    MeasurementUnit getByUnit(by.agsr.common.MeasurementUnit unit);

}
