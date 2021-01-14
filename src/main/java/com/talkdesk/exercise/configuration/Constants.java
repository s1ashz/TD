package com.talkdesk.exercise.configuration;

public enum Constants {

    ChallengeEndPoint(""),
    ChallengeSector(""),
    FileName("");

    private String value;

    public String getValue() {
        return value;
    }

    protected void setValue(String value) {
        this.value = value;
    }

    Constants(String value) {
        this.value = value;
    }

}
