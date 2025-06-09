package by.agsr.monitor.dao.impl;

import by.agsr.monitor.dao.entity.MeasurementUnit;
import by.agsr.monitor.dao.interfaces.MeasurementUnitService;
import by.agsr.monitor.dao.repository.MeasurementUnitRepository;
import by.agsr.monitor.exception.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MeasurementUnitServiceImpl implements MeasurementUnitService {

    private final MeasurementUnitRepository measurementUnitRepository;

    @Override
    @Transactional(readOnly = true)
    public List<MeasurementUnit> getAll() {
        return measurementUnitRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public MeasurementUnit getByUnit(by.agsr.common.MeasurementUnit unit) {
        return measurementUnitRepository.findByUnit(unit)
          .orElseThrow(() -> new BadRequestException(String.format("Measurement unit with unit '%s' not found", unit)));
    }
}
