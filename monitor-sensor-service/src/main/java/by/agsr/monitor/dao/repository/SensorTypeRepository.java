package by.agsr.monitor.dao.repository;

import by.agsr.monitor.dao.entity.SensorType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SensorTypeRepository extends JpaRepository<SensorType, Long> {

    Optional<SensorType> findByType(by.agsr.common.SensorType type);
}
