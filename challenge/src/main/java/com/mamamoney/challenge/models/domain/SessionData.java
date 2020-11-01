package com.mamamoney.challenge.models.domain;

import com.mamamoney.challenge.models.domain.enums.SessionState;

public class SessionData {

    private String sessionId;
    private SessionState state;
    private SessionState nextState;
    private FinalRequest finalRequest;

    public SessionData() {
    }

    public SessionData cloneForRepository() {
        // create copy with the same values
        SessionData copy =  new SessionData();
        copy.setFinalRequest(this.finalRequest);
        copy.setSessionId(this.sessionId);
        // set to next state - for next retrieval request
        copy.setState(this.nextState);
        return copy;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public SessionState getState() {
        return state;
    }

    public void setState(SessionState state) {
        this.state = state;
    }

    public SessionState getNextState() {
        return nextState;
    }

    public void setNextState(SessionState nextState) {
        this.nextState = nextState;
    }

    public FinalRequest getFinalRequest() {
        return finalRequest;
    }

    public void setFinalRequest(FinalRequest finalRequest) {
        this.finalRequest = finalRequest;
    }

    @Override
    public String toString() {
        return "SessionData{" +
                "sessionId='" + sessionId + '\'' +
                ", state=" + state +
                ", nextState=" + nextState +
                ", finalRequest=" + finalRequest +
                '}';
    }
}
