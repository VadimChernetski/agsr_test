package by.agsr.monitor.dao.repository;

import by.agsr.monitor.dao.entity.InstalledSensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;


public interface InstalledSensorRepository extends JpaRepository<InstalledSensor, Long>, JpaSpecificationExecutor<InstalledSensor> {

    @Query("SELECT CASE WHEN EXISTS (SELECT s FROM InstalledSensor s WHERE s.sensor.id = ?1) THEN true ELSE false END")
    boolean installedSensorExistsForGivenSensorId(Long sensorId);

}
