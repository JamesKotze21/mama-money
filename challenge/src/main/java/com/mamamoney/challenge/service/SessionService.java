package com.mamamoney.challenge.service;

import com.mamamoney.challenge.exceptions.InvalidUserRequestException;
import com.mamamoney.challenge.models.ClientRequest;
import com.mamamoney.challenge.models.ClientResponse;
import com.mamamoney.challenge.models.domain.SessionData;
import com.mamamoney.challenge.repository.SessionDataRepoMemoryMap;
import com.mamamoney.challenge.repository.SessionDataRepoMongoDB;
import com.mamamoney.challenge.strategies.sessions.SessionStrategy;
import com.mamamoney.challenge.strategies.sessions.SessionStrategyFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class SessionService {

    @Autowired
    private SessionDataRepoMemoryMap sessionRepo;

    @Autowired
    private SessionDataRepoMongoDB sessionRepoMongo;

    @Autowired
    private SessionStrategyFactory sessionStrategyFactory;

    public ResponseEntity<ClientResponse> receiveUssdRequest(ClientRequest request) throws InvalidUserRequestException {

        // find last stored sessionData
        SessionData sessionData = sessionRepo.findSessionData(request.getMsisdn(), request.getSessionId());

        // get session strategy
        SessionStrategy session = sessionStrategyFactory.getSessionStrategy(sessionData.getState());

        // process the session
        try {
            sessionData = session.processSession(sessionData, request, sessionRepo);
        } catch (InvalidUserRequestException ex) {
            // remove session data when invalid request
            sessionRepo.removeSessionData(request.getMsisdn());
            throw ex;
        }

        ClientResponse response = session.getSessionResponse(sessionData);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    public ResponseEntity<ClientResponse> receiveUssdRequestMongo(ClientRequest request) throws InvalidUserRequestException {
        // find last stored sessionData
        SessionData sessionData = sessionRepoMongo.findSessionData(request.getMsisdn(), request.getSessionId());

        // get session strategy
        SessionStrategy session = sessionStrategyFactory.getSessionStrategy(sessionData.getState());

        // process the session
        try {
            sessionData = session.processSession(sessionData, request, sessionRepoMongo);
        } catch (InvalidUserRequestException ex) {
            // remove session data when invalid request
            sessionRepoMongo.removeSessionData(request.getMsisdn());
            throw ex;
        }

        ClientResponse response = session.getSessionResponse(sessionData);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
