package by.agsr.statistics.service;

import by.agsr.statistics.dao.interfaces.StatisticService;
import by.agsr.statistics.model.InstalledSensorsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class FetchStatisticService {

    private final StatisticService statisticService;
    private final RestClient restClient;

    @Scheduled(cron = "0 0 2 * * ?", zone = "Europe/Minsk")
    public void fetchStatistic() {
        InstalledSensorsResponse response = restClient.get()
          .retrieve()
          .body(InstalledSensorsResponse.class);
        if (Objects.nonNull(response) && Objects.nonNull(response.getInstalledSensor())) {
            statisticService.saveStatistic(response.getInstalledSensor());
        }
    }
}
