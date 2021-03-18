package com.coronainfo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.IOException;
import java.net.URISyntaxException;

@SpringBootApplication
@EnableScheduling
public class CoronavirusTrackerApplication {

    public static void main(String[] args) throws InterruptedException, IOException, URISyntaxException {
        // SpringApplication.run(CoronavirusTrackerApplication.class, args);
        ApplicationContext applicationContext = SpringApplication.run(CoronavirusTrackerApplication.class, args);
        CoronavirusDataService service = applicationContext.getBean(CoronavirusDataService.class);
        service.fetchVirusData();
    }
}