package com.talkdesk.exercise.service;

import com.talkdesk.exercise.rest.response.AggregatorResponseBody;

import java.util.List;

public interface PhoneNumberAggregatorService {

    AggregatorResponseBody aggregatePhoneNumbers(List<String> phoneNumbers );

}
