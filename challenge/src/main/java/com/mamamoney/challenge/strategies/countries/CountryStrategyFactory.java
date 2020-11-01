package com.mamamoney.challenge.strategies.countries;

import com.mamamoney.challenge.models.domain.enums.CountryCode;
import org.apache.commons.compress.utils.Lists;
import org.apache.commons.compress.utils.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;

@Component
public class CountryStrategyFactory {

    private HashMap<CountryCode, CountryStrategy> strategyMap;
    private static HashMap<Integer, CountryCode> codeMap;

    @Autowired
    private KenyaStrategy kenya;
    @Autowired
    private MalawiStrategy malawi;

    @PostConstruct
    private void setup() {
        codeMap = new HashMap<>();
        codeMap.put(1, CountryCode.KWS);
        codeMap.put(2, CountryCode.MWK);

        strategyMap = new HashMap<>();
        strategyMap.put(CountryCode.KWS, kenya);
        strategyMap.put(CountryCode.MWK, malawi);
    }

    public CountryStrategy getCountry(CountryCode code) {
        return strategyMap.get(code);
    }

    public CountryCode getCountryCode(int index) {
        return codeMap.get(index);
    }

    public int getCountryListSize() {
        return codeMap.size();
    }

    public List<Integer> getCountryOptions() {
        return new ArrayList<>(codeMap.keySet());
    }
}
