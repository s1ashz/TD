package com.talkdesk.exercise.configuration;

import com.talkdesk.exercise.prefixes.StoredPhoneNumberPrefixes;
import com.talkdesk.exercise.prefixes.PrefixesFileReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class PostConstructBean {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    private RestProperties configProperties;
    private FileProperties fileProperties;
    private PrefixesFileReader prefixesFileReader;

    @Autowired
    public PostConstructBean(RestProperties configProperties, FileProperties fileProperties, StoredPhoneNumberPrefixes phoneNumberPrefixes) {
        this.configProperties = configProperties;
        this.fileProperties = fileProperties;
        prefixesFileReader = new PrefixesFileReader(phoneNumberPrefixes);
    }

    @PostConstruct
    public void init() {
        LOGGER.info(">Initializing the application");
        configProperties.init();
        fileProperties.init();
        prefixesFileReader.readFromPrefixesFile();
        LOGGER.info("<Application Initialized...");
    }

}
