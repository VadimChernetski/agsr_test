package by.agsr.monitor.dao.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "installed_sensor", schema = "agsr")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InstalledSensor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "sensor_id", nullable = false)
    private Sensor sensor;

    @Column(name = "location")
    private String location;

    @Column(name = "description")
    private String description;
}
