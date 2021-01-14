package com.talkdesk.exercise.prefixes;

import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class PhoneNumberPrefixesImpl implements StoredPhoneNumberPrefixes {

    private Set<String> storedPhoneNumberPrefixes = new HashSet<>();

    @Override
    public void setPrefixes(Set<String> prefixes) {
        this.storedPhoneNumberPrefixes = prefixes;
    }

    @Override
    public int getPrefixesSize() {
        return storedPhoneNumberPrefixes.size();
    }

    @Override
    public String getPhoneNumberPrefix(String phoneNumber) {
        return searchPrefixInList( phoneNumber );
    }

    private String searchPrefixInList(String prefix) {
        while ( numberNotEmpty(prefix) ) {
            if ( checkPrefixExists(prefix) ) {
                break;
            } else {
                prefix = removeLastLetterOfString(prefix);
            }
        }
        return prefix;
    }

    private boolean checkPrefixExists(String number) {
        return storedPhoneNumberPrefixes.contains(number);
    }

    private boolean numberNotEmpty(String number) {
        return number.length() != 0;
    }

    private String removeLastLetterOfString(String number) {
        return number.substring(0, number.length() - 1);
    }

}
