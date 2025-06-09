package by.agsr.monitor.dao.impl;

import by.agsr.monitor.dao.entity.SensorType;
import by.agsr.monitor.dao.interfaces.SensorTypeService;
import by.agsr.monitor.dao.repository.SensorTypeRepository;
import by.agsr.monitor.exception.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SensorTypeServiceImpl implements SensorTypeService {

    private final SensorTypeRepository SensorTypeRepository;

    @Override
    @Transactional(readOnly = true)
    public List<SensorType> getAll() {
        return SensorTypeRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public SensorType getByType(by.agsr.common.SensorType type) {
        return SensorTypeRepository.findByType(type)
          .orElseThrow(() -> new BadRequestException(String.format("Sensor type with type '%s' not found", type)));
    }
}
