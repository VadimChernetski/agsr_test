package by.agsr.monitor.dao.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "measurement_unit", schema = "agsr")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MeasurementUnit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "unit")
    @Enumerated(EnumType.STRING)
    private by.agsr.common.MeasurementUnit unit;

}
