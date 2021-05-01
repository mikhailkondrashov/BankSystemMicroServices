package ru.kondrashov.commonservice;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.integration.IntegrationAutoConfiguration;

@SpringBootApplication(exclude = IntegrationAutoConfiguration.class)
public class CommonServiceApplication {

    static final Logger LOGGER = LogManager.getRootLogger();

    public static void main(String[] args) {
        SpringApplication.run(CommonServiceApplication.class, args);
        LOGGER.info("Context is up");
    }

}
