package by.agsr.monitor.dao.repository;

import by.agsr.monitor.dao.entity.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SensorRepository extends JpaRepository<Sensor, Long> {
}
