package com.talkdesk.exercise.rest.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

public class RestConsumer {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    private RestTemplate restTemplate;

    public RestConsumer() {
        restTemplate = new RestTemplate();
    }

    public <T> ResponseEntity<T> consumeAPI(String url) {
        LOGGER.info(">consuming GET in endpoint: " + url);
        ResponseEntity<T> response = null;
        try {
            response = (ResponseEntity<T>) restTemplate.getForEntity(url, String.class);
        } catch ( HttpClientErrorException e ) {
            e.printStackTrace();
        }
        return response;
    }

}
