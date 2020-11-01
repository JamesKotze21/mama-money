package com.mamamoney.challenge.strategies.sessions;

import com.mamamoney.challenge.exceptions.InvalidUserRequestException;
import com.mamamoney.challenge.models.ClientRequest;
import com.mamamoney.challenge.models.ClientResponse;
import com.mamamoney.challenge.models.domain.SessionData;
import com.mamamoney.challenge.models.domain.enums.SessionState;
import com.mamamoney.challenge.repository.SessionDataRepo;
import com.mamamoney.challenge.strategies.countries.CountryStrategy;
import com.mamamoney.challenge.strategies.countries.CountryStrategyFactory;
import com.mamamoney.challenge.validations.Validator;
import com.mamamoney.challenge.validations.IsInteger;
import com.mamamoney.challenge.validations.IsNotEmpty;
import com.mamamoney.challenge.validations.IsOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class SelectAmountStrategy implements SessionStrategy {

    @Autowired
    CountryStrategyFactory countryFactory;

    @Autowired
    Validator validator;

    private final String RESPONSE_MESSAGE_TEMPLATE = "How much money (in Rands) would you like to send to <CountryName>?";

    @Override
    public SessionData processSession(SessionData sessionData, ClientRequest request, SessionDataRepo sessionRepo) throws InvalidUserRequestException {

        validator.validate(request, Arrays.asList(new IsNotEmpty(), new IsInteger(), new IsOptions(countryFactory.getCountryOptions())));

        sessionData.getFinalRequest().setCountryCode(countryFactory.getCountryCode(Integer.parseInt(request.getUserEntry())));
        sessionData.setNextState(SessionState.SELECT_CONFIRM);
        sessionRepo.saveSessionData(request.getMsisdn(), sessionData);

        return sessionData;
    }

    @Override
    public ClientResponse getSessionResponse(SessionData sessionData) {
        // get selected country
        CountryStrategy country = countryFactory.getCountry(sessionData.getFinalRequest().getCountryCode());

        // build response
        ClientResponse response = new ClientResponse();
        response.setSessionId(sessionData.getSessionId());
        response.setMessage(RESPONSE_MESSAGE_TEMPLATE.replace("<CountryName>" , country.getFriendlyName()));

        return response;
    }
}