package by.agsr.statistics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class AgsrStatisticsApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(AgsrStatisticsApplication.class, args);
    }
}
