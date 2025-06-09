package by.agsr.monitor.dao.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "sensor_type", schema = "agsr")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SensorType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private by.agsr.common.SensorType type;

}
