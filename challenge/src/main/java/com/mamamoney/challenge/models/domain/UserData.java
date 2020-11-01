package com.mamamoney.challenge.models.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class UserData {

    @Id
    private String msisdn;

    private SessionData sessionData;

    public UserData() {
    }

    public UserData(String msisdn, SessionData sessionData) {
        this.msisdn = msisdn;
        this.sessionData = sessionData;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public SessionData getSessionData() {
        return sessionData;
    }

    public void setSessionData(SessionData sessionData) {
        this.sessionData = sessionData;
    }

    @Override
    public String toString() {
        return "UserData{" +
                "msisdn='" + msisdn + '\'' +
                ", sessionData=" + sessionData +
                '}';
    }
}
