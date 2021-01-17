package com.talkdesk.exercise.rest.controller;

import com.talkdesk.exercise.configuration.RestEndpoints;
import com.talkdesk.exercise.rest.response.AggregatorResponseBody;
import com.talkdesk.exercise.service.PhoneNumberAggregatorService;
import com.talkdesk.exercise.service.PhoneNumberAggregatorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AggregatorController {

    private PhoneNumberAggregatorService phoneNumberAggregatorService;

    @Autowired
    public AggregatorController(PhoneNumberAggregatorServiceImpl phoneNumberAggregatorService) {
        this.phoneNumberAggregatorService = phoneNumberAggregatorService;
    }

    @RequestMapping(value = RestEndpoints.INDEX, method = RequestMethod.GET)
    public String get() {
        return "Phone Number Aggregator";
    }

    @RequestMapping(value = RestEndpoints.AGGREGATE, method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity aggregatePhoneNumbers(@RequestBody(required = true) List<String> phoneNumbers) {
        AggregatorResponseBody body = phoneNumberAggregatorService.aggregatePhoneNumbers(phoneNumbers);
        return new ResponseEntity(body.getBody(), HttpStatus.OK);
    }

}
