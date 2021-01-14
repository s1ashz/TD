package com.talkdesk.exercise.rest.response;

import java.util.HashMap;
import java.util.Map;

public class AggregatorResponseBody extends AppResponseBody {

    private Map<String, Prefix> prefixes;
    private Map<String, Map<String, Integer>> body = new HashMap<>();

    public AggregatorResponseBody(Map<String, Prefix> prefixes) {
        this.prefixes = prefixes;
        createBody();
    }

    public void createBody() {
        for( Map.Entry<String, Prefix> entry : prefixes.entrySet() ) {
            Prefix prefix = entry.getValue();
            body.put( prefix.getPrefixValue(), prefix.getSectorMap() );
        }
    }

    @Override
    public Map<String, Map<String, Integer>> getBody() {
        return body;
    }

}
