package by.agsr.monitor.dao.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "sensor", schema = "agsr")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Sensor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "model", nullable = false, length = 15)
    private String model;

    @Column(name = "min")
    private Short from;

    @Column(name = "max", nullable = false)
    private Short to;

    @ManyToOne
    @JoinColumn(name = "type_id", nullable = false)
    private SensorType sensorType;

    @ManyToOne
    @JoinColumn(name = "measurement_unit_id", nullable = false)
    private MeasurementUnit measurementUnit;
}
