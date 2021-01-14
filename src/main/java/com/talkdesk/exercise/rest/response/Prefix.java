package com.talkdesk.exercise.rest.response;

import java.util.HashMap;
import java.util.Map;

public class Prefix {

    private String prefixValue;
    private Map<String, Integer> sectorMap;

    public Prefix(String prefixValue) {
        this.prefixValue = prefixValue;
        sectorMap = new HashMap<>();
    }

    public String getPrefixValue() {
        return prefixValue;
    }

    public void setPrefixValue(String prefixValue) {
        this.prefixValue = prefixValue;
    }

    public void addSector(String sector) {
        if ( sectorMap.containsKey(sector) ) {
            sectorMap.put(sector, sectorMap.get(sector)+1);
        } else {
            sectorMap.put(sector, 1);
        }
    }

    public Map<String, Integer> getSectorMap() {
        return sectorMap;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Prefix{");
        sb.append("prefixValue='").append(prefixValue).append('\'');
        sb.append(", sectorMap=").append(sectorMap);
        sb.append('}');
        return sb.toString();
    }
}
