package ru.kondrashov.personservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class PersonServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PersonServiceApplication.class, args);
    }

}
