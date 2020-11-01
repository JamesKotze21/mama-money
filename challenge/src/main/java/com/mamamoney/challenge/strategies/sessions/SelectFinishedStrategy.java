package com.mamamoney.challenge.strategies.sessions;

import com.mamamoney.challenge.models.ClientRequest;
import com.mamamoney.challenge.models.ClientResponse;
import com.mamamoney.challenge.models.domain.SessionData;
import com.mamamoney.challenge.repository.SessionDataRepo;
import org.springframework.stereotype.Component;

@Component
public class SelectFinishedStrategy implements SessionStrategy {

    private final String RESPONSE_MESSAGE_TEMPLATE = "Thank you for using Mama Money!";

    @Override
    public SessionData processSession(SessionData sessionData, ClientRequest request, SessionDataRepo sessionRepo) {
        // remove from data store
        sessionRepo.removeSessionData(request.getMsisdn());
        return sessionData;
    }

    @Override
    public ClientResponse getSessionResponse(SessionData sessionData) {
        // build response
        ClientResponse response = new ClientResponse();
        response.setSessionId(sessionData.getSessionId());
        response.setMessage(RESPONSE_MESSAGE_TEMPLATE);
        return response;
    }
}
