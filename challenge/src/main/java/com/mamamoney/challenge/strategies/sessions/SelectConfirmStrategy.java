package com.mamamoney.challenge.strategies.sessions;

import com.mamamoney.challenge.exceptions.InvalidUserRequestException;
import com.mamamoney.challenge.models.ClientRequest;
import com.mamamoney.challenge.models.ClientResponse;
import com.mamamoney.challenge.models.domain.SessionData;
import com.mamamoney.challenge.models.domain.enums.CurrencyCode;
import com.mamamoney.challenge.models.domain.enums.SessionState;
import com.mamamoney.challenge.repository.SessionDataRepo;
import com.mamamoney.challenge.strategies.countries.CountryStrategy;
import com.mamamoney.challenge.strategies.countries.CountryStrategyFactory;
import com.mamamoney.challenge.validations.Validator;
import com.mamamoney.challenge.validations.IsMonetary;
import com.mamamoney.challenge.validations.IsNotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;


@Component
public class SelectConfirmStrategy implements SessionStrategy {

    @Autowired
    CountryStrategyFactory countryFactory;

    @Autowired
    Validator validator;

    private final String RESPONSE_MESSAGE_TEMPLATE = "Your person you are sending to will receive <Amount> <ForeignCurrencyCode>\n1) OK";

    @Override
    public SessionData processSession(SessionData sessionData, ClientRequest request, SessionDataRepo sessionRepo) throws InvalidUserRequestException {

        validator.validate(request, Arrays.asList(new IsNotEmpty(), new IsMonetary()));

        sessionData.getFinalRequest().setAmount(Float.parseFloat(request.getUserEntry()));
        sessionData.setNextState(SessionState.SELECT_FINISHED);
        sessionRepo.saveSessionData(request.getMsisdn(), sessionData);

        return sessionData;
    }

    @Override
    public ClientResponse getSessionResponse(SessionData sessionData) {
        // get country
        CountryStrategy country = countryFactory.getCountry(sessionData.getFinalRequest().getCountryCode());

        // build message values
        float amount = country.convertCurrency(sessionData.getFinalRequest().getAmount());
        CurrencyCode currencyCode = country.getCurrencyCode();

        // build response
        ClientResponse response = new ClientResponse();
        response.setSessionId(sessionData.getSessionId());
        response.setMessage(RESPONSE_MESSAGE_TEMPLATE
                    .replace("<Amount>" , Float.toString(amount))
                    .replace("<ForeignCurrencyCode>", currencyCode.toString()));
        return response;
    }
}
