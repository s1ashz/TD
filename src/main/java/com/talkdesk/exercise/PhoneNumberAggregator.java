package com.talkdesk.exercise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;


@SpringBootApplication
public class PhoneNumberAggregator {

    public static void main(String[] args) {
        SpringApplication.run(PhoneNumberAggregator.class, args);
    }

}
