package com.mamamoney.challenge.repository;

import com.mamamoney.challenge.models.domain.SessionData;
import com.mamamoney.challenge.models.domain.UserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class SessionDataRepoMongoDB extends SessionDataRepo {

    @Autowired
    UserRepoMongo mongoRepo;

    @Override
    public SessionData findSessionData(String msisdn, String sessionId) {
        Optional<UserData> user = mongoRepo.findById(msisdn);
        if (user.isPresent()) {
            // it is assumed that if a session ID does not match the previous one, then it will restart the flow
            return user.get().getSessionData().getSessionId().equals(sessionId) ? user.get().getSessionData() : initNewSession(msisdn, sessionId);
        }
        return initNewSession(msisdn, sessionId);
    }

    @Override
    public void saveSessionData(String msisdn, SessionData sessionData) {
        mongoRepo.save(new UserData(msisdn, sessionData.cloneForRepository()));
    }

    @Override
    public void removeSessionData(String msisdn) {
        mongoRepo.deleteById(msisdn);
    }
}
