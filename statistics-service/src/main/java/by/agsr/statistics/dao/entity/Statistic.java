package by.agsr.statistics.dao.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "statistic", schema = "agsr")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Statistic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "pressure_sensors_number")
    private Long pressureSensorsNumber;


    @Column(name = "temperature_sensors_number")
    private Long temperatureSensorsNumber;


    @Column(name = "voltage_sensors_number")
    private Long voltageSensorsNumber;


    @Column(name = "humidity_sensors_number")
    private Long humiditySensorsNumber;

    @Column(name = "date")
    private LocalDateTime date;

}
