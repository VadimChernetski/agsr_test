package by.agsr.monitor.dao.repository;

import by.agsr.monitor.dao.entity.MeasurementUnit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MeasurementUnitRepository extends JpaRepository<MeasurementUnit, Long> {

    Optional<MeasurementUnit> findByUnit(by.agsr.common.MeasurementUnit unit);

}
