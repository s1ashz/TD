package com.talkdesk.exercise.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.talkdesk.exercise.configuration.Constants;
import com.talkdesk.exercise.rest.consumer.RestConsumer;
import com.talkdesk.exercise.rest.model.ChallengeResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class PhoneNumberSectorServiceImpl implements PhoneNumberSectorService {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private String CHALLENGE_ENDPOINT;
    private String CHALLENGE_SECTOR_ENDPOINT;
    private Gson gson;

    private RestConsumer restConsumer;

    public PhoneNumberSectorServiceImpl() {
        restConsumer = new RestConsumer();
        createChallengeEndPoint();
        instantiateGson();
    }

    private void createChallengeEndPoint() {
        CHALLENGE_ENDPOINT = Constants.ChallengeEndPoint.getValue();
        CHALLENGE_SECTOR_ENDPOINT = CHALLENGE_ENDPOINT + Constants.ChallengeSector.getValue();
    }

    private void instantiateGson() {
        GsonBuilder builder = new GsonBuilder();
        gson = builder.create();
    }

    @Override
    public ChallengeResponse getPhoneNumberSector(String phoneNumber) {
        LOGGER.info("\n>getPhoneNumberSector");
        ResponseEntity<ChallengeResponse> response = restConsumer.consumeAPI( CHALLENGE_SECTOR_ENDPOINT + phoneNumber );
        ChallengeResponse challengeResponse = validateResponse(phoneNumber, response );
        LOGGER.info("<getPhoneNumberSector\n");
        return challengeResponse;
    }

    private ChallengeResponse validateResponse(String phoneNumber, ResponseEntity<ChallengeResponse> response ) {
        ChallengeResponse challengeResponse = null;

        if ( response != null && response.getStatusCode().equals(HttpStatus.OK) ) {
            LOGGER.info("Response service: " + response.toString());
            challengeResponse = gson.fromJson(String.valueOf(response.getBody()), ChallengeResponse.class);
            LOGGER.info(challengeResponse.toString());
        } else {
            LOGGER.info("Invalid Phone Number: " + phoneNumber);
        }
        return challengeResponse;
    }
}
