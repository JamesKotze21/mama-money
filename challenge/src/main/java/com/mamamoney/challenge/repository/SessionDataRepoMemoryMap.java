package com.mamamoney.challenge.repository;

import com.mamamoney.challenge.models.domain.SessionData;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class SessionDataRepoMemoryMap extends SessionDataRepo {

    private static HashMap<String, SessionData> sessions = new HashMap<>();

    @Override
    public SessionData findSessionData(String msisdn, String sessionId) {
        if (sessions.containsKey(msisdn)) {
            SessionData foundSession = sessions.get(msisdn);
            // it is assumed that if a session ID does not match the previous one, then it will restart the flow
            return foundSession.getSessionId().equals(sessionId) ? foundSession : initNewSession(msisdn, sessionId);
        }

        return initNewSession(msisdn, sessionId);
    }

    @Override
    public void saveSessionData(String msisdn, SessionData sessionData) {
        if (sessions.containsKey(msisdn)) {
            sessions.replace(msisdn, sessionData.cloneForRepository());
        } else {
            sessions.put(msisdn, sessionData.cloneForRepository());
        }
    }

    @Override
    public void removeSessionData(String msisdn) {
        sessions.remove(msisdn);
    }
}
