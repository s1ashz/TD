package com.talkdesk.exercise.prefixes;

import java.util.Set;

public interface StoredPhoneNumberPrefixes {

    void setPrefixes(Set<String> prefixes);
    int getPrefixesSize();
    String getPhoneNumberPrefix(String phoneNumber);


}
