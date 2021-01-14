package com.talkdesk.exercise.rest.model;

public class ChallengeResponse {

    private String number;
    private String sector;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ChallengeResponse{");
        sb.append("number='").append(number).append('\'');
        sb.append(", sector='").append(sector).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
