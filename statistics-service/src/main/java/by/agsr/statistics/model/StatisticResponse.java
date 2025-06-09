package by.agsr.statistics.model;

import by.agsr.statistics.dto.StatisticDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
public class StatisticResponse {

    private List<StatisticDto> statistics;

}
