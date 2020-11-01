package com.mamamoney.challenge.strategies.sessions;

import com.mamamoney.challenge.models.domain.enums.SessionState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;

@Component
public class SessionStrategyFactory {
    private HashMap<SessionState, SessionStrategy> strategyMap;

    @Autowired
    private SelectCountryStrategy countr;

    @Autowired
    private SelectAmountStrategy amount;

    @Autowired
    private SelectConfirmStrategy confirm;

    @Autowired
    private SelectFinishedStrategy finished;


    @PostConstruct
    private void setup() {
        strategyMap = new HashMap<>();
        strategyMap.put(SessionState.SELECT_COUNTRY, countr);
        strategyMap.put(SessionState.SELECT_AMOUNT, amount);
        strategyMap.put(SessionState.SELECT_CONFIRM, confirm);
        strategyMap.put(SessionState.SELECT_FINISHED, finished);
    }

    public SessionStrategy getSessionStrategy (SessionState state) {
        return strategyMap.get(state);
    }
}
