package com.talkdesk.exercise.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("challenge.rest")
public class RestProperties {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    private String endpoint;
    private String sector;

    protected void init() {
        LOGGER.info(">Setting RestProperties Configurations");
        Constants.ChallengeEndPoint.setValue(endpoint);
        Constants.ChallengeSector.setValue(sector);
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }
}
