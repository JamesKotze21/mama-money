package com.mamamoney.challenge.models;

public class ClientRequest {
    private String sessionId;
    private String msisdn;
    private String userEntry;

    public ClientRequest() {
    }

    public ClientRequest(String sessionId, String msisdn, String userEntry) {
        this.sessionId = sessionId;
        this.msisdn = msisdn;
        this.userEntry = userEntry;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public String getUserEntry() {
        return userEntry;
    }

    public void setUserEntry(String userEntry) {
        this.userEntry = userEntry;
    }

    @Override
    public String toString() {
        return "UserRequest{" +
                "sessionId='" + sessionId + '\'' +
                ", msisdn='" + msisdn + '\'' +
                ", userEntry='" + userEntry + '\'' +
                '}';
    }
}
