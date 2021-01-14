package com.talkdesk.exercise.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("file.prefix")
public class FileProperties {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    private String name;

    protected void init() {
        LOGGER.info(">Setting FileProperties Configurations");
        Constants.FileName.setValue(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
