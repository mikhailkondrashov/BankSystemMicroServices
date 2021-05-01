package ru.kondrashov.personservice;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class PersonServiceApplication {

    private static final Logger logger = LogManager.getRootLogger();

    public static void main(String[] args) {
        SpringApplication.run(PersonServiceApplication.class, args);
        logger.info("Context is up");
    }

}
