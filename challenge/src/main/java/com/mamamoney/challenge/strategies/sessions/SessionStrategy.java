package com.mamamoney.challenge.strategies.sessions;

import com.mamamoney.challenge.exceptions.InvalidUserRequestException;
import com.mamamoney.challenge.models.ClientRequest;
import com.mamamoney.challenge.models.ClientResponse;
import com.mamamoney.challenge.models.domain.SessionData;
import com.mamamoney.challenge.repository.SessionDataRepo;

public interface SessionStrategy {
    public SessionData processSession(SessionData sessionData, ClientRequest request, SessionDataRepo repo) throws InvalidUserRequestException;

    public ClientResponse getSessionResponse(SessionData sessionData);
}
