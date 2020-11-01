package com.mamamoney.challenge.repository;

import com.mamamoney.challenge.models.domain.FinalRequest;
import com.mamamoney.challenge.models.domain.SessionData;
import com.mamamoney.challenge.models.domain.enums.SessionState;
import org.springframework.stereotype.Component;

@Component
public abstract class SessionDataRepo {

    private final SessionState INITIAL_SELECTION_STATE = SessionState.SELECT_COUNTRY;

    public abstract SessionData findSessionData(String msisdn, String sessionId);

    public abstract void saveSessionData(String msisdn, SessionData sessionData);

    public abstract void removeSessionData(String msisdn);

    public SessionData initNewSession(String msisdn, String sessionId) {
        SessionData sessionData = new SessionData();
        sessionData.setState(INITIAL_SELECTION_STATE);
        sessionData.setSessionId(sessionId);
        sessionData.setFinalRequest(new FinalRequest());
        return sessionData;
    }
}
