package by.agsr.monitor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class AgsrTestApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext run = SpringApplication.run(AgsrTestApplication.class, args);
	}

}
