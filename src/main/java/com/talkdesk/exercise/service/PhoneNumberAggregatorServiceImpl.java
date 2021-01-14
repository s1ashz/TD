package com.talkdesk.exercise.service;

import com.talkdesk.exercise.prefixes.StoredPhoneNumberPrefixes;
import com.talkdesk.exercise.rest.model.ChallengeResponse;
import com.talkdesk.exercise.rest.response.Prefix;
import com.talkdesk.exercise.rest.response.AggregatorResponseBody;
import com.talkdesk.exercise.validators.PhoneNumberValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PhoneNumberAggregatorServiceImpl implements PhoneNumberAggregatorService {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private final String PHONE_NUMBER_AGGREGATOR_SERVICE = "Phone Number Aggregator Service ";
    private PhoneNumberValidator validator;

    private PhoneNumberSectorService phoneNumberSectorService;
    private StoredPhoneNumberPrefixes storedPhoneNumberPrefixes;

    @Autowired
    public PhoneNumberAggregatorServiceImpl(PhoneNumberSectorService challengeRestService, StoredPhoneNumberPrefixes phoneNumberPrefixes ) {
        this.phoneNumberSectorService = challengeRestService;
        this.storedPhoneNumberPrefixes = phoneNumberPrefixes;
        validator = new PhoneNumberValidator();
    }

    @Override
    public AggregatorResponseBody aggregatePhoneNumbers(List<String> phoneNumbers) {
        LOGGER.info(PHONE_NUMBER_AGGREGATOR_SERVICE + ">aggregatePhoneNumbers");

        phoneNumbers = validator.validatePhoneNumbers( phoneNumbers );
        Map<String, Prefix> phoneNumberPrefixes = getPrefixAndSectorForPhoneNumberList( phoneNumbers );

        return new AggregatorResponseBody(phoneNumberPrefixes);
    }

    private Map<String, Prefix> getPrefixAndSectorForPhoneNumberList( List<String> phoneNumbers ) {
        Map<String, Prefix> existingPrefixes = new HashMap<>();
        for ( String phoneNumb : phoneNumbers) {
            String prefixValue = storedPhoneNumberPrefixes.getPhoneNumberPrefix(phoneNumb);
            ChallengeResponse challengeResponse = phoneNumberSectorService.getPhoneNumberSector(phoneNumb);

            if ( challengeResponse != null ) {
                Prefix prefix = getPrefixFromMap(existingPrefixes, prefixValue);
                prefix.addSector(challengeResponse.getSector());
                existingPrefixes.put(prefix.getPrefixValue(), prefix);
            }

        }
        return existingPrefixes;
    }

    private Prefix getPrefixFromMap(Map<String, Prefix> existingPrefixes, String prefixValue) {
        Prefix prefix;
        if ( existingPrefixes.containsKey(prefixValue) ) {
            prefix = existingPrefixes.get(prefixValue);
        } else {
            prefix = new Prefix(prefixValue);
        }
        return prefix;
    }

}
