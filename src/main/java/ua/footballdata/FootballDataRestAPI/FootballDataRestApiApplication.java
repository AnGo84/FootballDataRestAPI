package ua.footballdata.FootballDataRestAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"ua.footballdata"})
public class FootballDataRestApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(FootballDataRestApiApplication.class, args);
    }

}

