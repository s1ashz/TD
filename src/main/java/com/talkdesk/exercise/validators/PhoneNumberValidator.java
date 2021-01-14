package com.talkdesk.exercise.validators;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class PhoneNumberValidator {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    public List<String> validatePhoneNumbers( List<String> phoneNumbers ) {
        return validate( phoneNumbers );
    }

    private List<String> validate( List<String> phoneNumbers ) {
        LOGGER.info(">validating " + phoneNumbers.size() + " phone numbers" );
        return checkPhoneHasNoLettersAndReplaceUselessCharacters(phoneNumbers);
    }

    //This function is used to remove "+", "00" in the beginning of the phone number and removes invalid phone numbers (phone numbers with letters)
    private List<String> checkPhoneHasNoLettersAndReplaceUselessCharacters(List<String> phoneNumbers) {
        return phoneNumbers.stream()
                .filter(s -> !Pattern.matches(".*[a-zA-Z].*", s)  )
                .map( s -> s.replaceAll("\\+", "")
                        .replaceAll(" ", "")
                        .replaceFirst("^0+(?!$)", "" ))
                .collect(Collectors.toList());
    }

}
