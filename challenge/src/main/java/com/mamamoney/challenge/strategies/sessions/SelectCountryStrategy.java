package com.mamamoney.challenge.strategies.sessions;

import com.mamamoney.challenge.exceptions.InvalidUserRequestException;
import com.mamamoney.challenge.models.ClientRequest;
import com.mamamoney.challenge.models.ClientResponse;
import com.mamamoney.challenge.models.domain.SessionData;
import com.mamamoney.challenge.models.domain.enums.SessionState;
import com.mamamoney.challenge.repository.SessionDataRepo;
import com.mamamoney.challenge.strategies.countries.CountryStrategy;
import com.mamamoney.challenge.strategies.countries.CountryStrategyFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SelectCountryStrategy implements SessionStrategy {

    @Autowired
    private CountryStrategyFactory countryFactory;

    private final String RESPONSE_MESSAGE_TEMPLATE = "Welcome to Mama Money!\nWhere would you like to send money today?";

    @Override
    public SessionData processSession(SessionData sessionData, ClientRequest request, SessionDataRepo sessionRepo) throws InvalidUserRequestException {
        sessionData.setNextState(SessionState.SELECT_AMOUNT);
        sessionRepo.saveSessionData(request.getMsisdn(), sessionData);

        return sessionData;
    }

    @Override
    public ClientResponse getSessionResponse(SessionData sessionData) {
        StringBuilder returnString = new StringBuilder(RESPONSE_MESSAGE_TEMPLATE);

        // build message for all given countries
        for (int i = 1; i < countryFactory.getCountryListSize() + 1; i++) {
            CountryStrategy option = countryFactory.getCountry(countryFactory.getCountryCode(i));
            returnString.append("\n"+ i + ") " + option.getFriendlyName());
        }

        // build response
        ClientResponse response = new ClientResponse();
        response.setSessionId(sessionData.getSessionId());
        response.setMessage(returnString.toString());

        return response;
    }


}
